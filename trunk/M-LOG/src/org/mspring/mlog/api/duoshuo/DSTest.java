/**
 * 
 */
package org.mspring.mlog.api.duoshuo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.mspring.mlog.api.duoshuo.vo.DSComment;
import org.mspring.mlog.api.duoshuo.vo.DSCommentRequestParams;
import org.mspring.mlog.api.duoshuo.vo.DSPost;
import org.mspring.mlog.api.duoshuo.vo.DSPostRequestParams;
import org.mspring.mlog.api.duoshuo.vo.DSRequestParams;
import org.mspring.platform.utils.JSONUtils;

/**
 * @author Gao Youbo
 * @since 2013-5-3
 * @description
 * @TODO
 */
public class DSTest {
    public static void main(String[] args) {
         String post = syncPost();
         String comment = syncComment();
        
         System.out.println(post);
         System.out.println(comment);
        
         System.out.println(DSConst.POST_SYNC_URL + DSConst.DATA_TYPE_JSON);
         String post_result = post(post, DSConst.POST_SYNC_URL +
         DSConst.DATA_TYPE_JSON);
         System.out.println(post_result);

        // System.out.println(DSConst.COMMENT_SYNC_URL +
        // DSConst.DATA_TYPE_JSON);
        // String comment_result = post(comment, DSConst.COMMENT_SYNC_URL +
        // DSConst.DATA_TYPE_JSON);
        // System.out.println(comment_result);
    }

    public static String syncPost() {
        List<DSPost> threads = new ArrayList<DSPost>();
        DSPost dsPost = new DSPost();
        dsPost.setThread_key("1");
        dsPost.setTitle("多说测试文章");
        dsPost.setUrl("http://www.mspring.org/post/1.html");
        threads.add(dsPost);

        DSPostRequestParams dsPostRequestParams = new DSPostRequestParams();
        dsPostRequestParams.setSecret(DSConfig.SECRET);
        dsPostRequestParams.setShort_name(DSConfig.SHORT_NAME);
        dsPostRequestParams.setThreads(threads);

        return JSONUtils.toJson(dsPostRequestParams);
    }

    public static String syncComment() {
        List<DSComment> posts = new ArrayList<DSComment>();
        DSComment dsComment = new DSComment();
        dsComment.setPost_key("1");
        dsComment.setThread_key("1");
        dsComment.setMessage("评论内容XXX");
        posts.add(dsComment);

        DSCommentRequestParams dsCommentRequestParams = new DSCommentRequestParams();
        dsCommentRequestParams.setSecret(DSConfig.SECRET);
        dsCommentRequestParams.setShort_name(DSConfig.SHORT_NAME);
        dsCommentRequestParams.setPosts(posts);

        return JSONUtils.toJson(dsCommentRequestParams);
    }

    public static String post(String data, String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            // con.setRequestProperty("Pragma:", "no-cache");
            // con.setRequestProperty("Cache-Control", "no-cache");
            // con.setRequestProperty("Content-Type", "text/json");

            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write(new String(data.getBytes("ISO-8859-1")));
            out.flush();
            out.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer result = new StringBuffer();
            String line = "";
            for (line = br.readLine(); line != null; line = br.readLine()) {
                result.append(line);
            }
            return result.toString();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
}
