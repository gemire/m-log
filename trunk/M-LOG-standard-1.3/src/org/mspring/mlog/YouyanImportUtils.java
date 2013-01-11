/**
 * 
 */
package org.mspring.mlog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.entity.Post;
import org.mspring.platform.utils.DateUtils;
import org.mspring.platform.utils.JSONUtils;
import org.mspring.platform.utils.StringUtils;

import com.google.gson.reflect.TypeToken;

/**
 * @author Gao Youbo
 * @since 2013-1-10
 * @Description
 * @TODO
 */
public class YouyanImportUtils {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream is = new FileInputStream(new File("D:/1.json"));

        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // 将输入流转移到内存输出流中
        try {
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<Map<String, Object>> list = (List<Map<String, Object>>) JSONUtils.fromJson(jsonStr, new TypeToken<List<Map<String, Object>>>() {
        });
        for (Map<String, Object> map : list) {
            Comment comment = getComment(map);

            // System.out.println(map.get("title"));
            try {
                comment = ServiceFactory.getCommentService().createComment(comment);
            }
            catch (Exception e) {
                // TODO: handle exception
                continue;
            }

            List<Map<String, Object>> childs = (List<Map<String, Object>>) map.get("child");
            if (childs.size() > 0) {
                for (Map<String, Object> child : childs) {
                    Comment childComment = getComment(child);
                    childComment.setReplyComment(comment.getId());
                    childComment.setReplyCommentContent(comment.getContent());
                    childComment.setReplyUser(comment.getAuthor());
                    childComment.setReplyUserEmail(comment.getReplyUserEmail());
                    childComment.setReplyUserUrl(comment.getUrl());
                    childComment.setPost(comment.getPost());

                    try {
                        childComment = ServiceFactory.getCommentService().createComment(childComment);
                    }
                    catch (Exception e) {
                        // TODO: handle exception
                        continue;
                    }
                }
            }
        }
    }

    private static Comment getComment(Map<String, Object> map) {
        String content = map.get("content") == null ? null : map.get("content").toString();
        String time = map.get("time") == null ? null : map.get("time").toString();
        String uname = map.get("uname") == null ? null : map.get("uname").toString();
        String email = map.get("email") == null ? null : map.get("email").toString();
        String uhome = map.get("uhome") == null ? null : map.get("uhome").toString();
        String status = map.get("status") == null ? null : map.get("status").toString();
        String ip = map.get("ip") == null ? null : map.get("ip").toString();

        String title = map.get("title") == null ? null : map.get("title").toString();
        String url = map.get("url") == null ? null : map.get("url").toString();

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateTime(DateUtils.parse(time));
        comment.setAuthor(uname);
        comment.setEmail(email);
        comment.setUrl(uhome);
        comment.setPostIp(ip);

        if (status.equals("0")) { // 正常
            comment.setStatus(Comment.Status.APPROVED);
        }
        else if (status.equals("1")) { // 待验证
            comment.setStatus(Comment.Status.WAIT_FOR_APPROVE);
        }
        else if (status.equals("2")) { // 垃圾
            comment.setStatus(Comment.Status.SPAM);
        }
        else if (status.equals("3")) { // 已删除
            comment.setStatus(Comment.Status.RECYCLE);
        }

        Post post = null;
        if (StringUtils.isNotBlank(title)) {
            title = title.replaceAll(" - 慕春博客", "");
            post = ServiceFactory.getPostService().getPostByTitle(title);

        }

        if (post == null && StringUtils.isNotBlank(url)) {
            url = url.replaceAll("http://www.mspring.org", "");
            post = ServiceFactory.getPostService().getPostByUrl(url);
        }

        if (post != null) {
            comment.setPost(post);
        }

        return comment;
    }
}
