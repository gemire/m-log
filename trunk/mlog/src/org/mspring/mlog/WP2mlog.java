/**
 * 
 */
package org.mspring.mlog;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.entity.User;
import org.mspring.platform.utils.DateUtils;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author Gao Youbo
 * @since 2012-8-14
 * @Description
 * @TODO
 */
public class WP2mlog {
    public void wp2mlog() throws Exception {
        Document doc = XMLUtils.parse(new File("D:/wp2mlog.xml"));
        List<Node> postNodes = XMLUtils.parseForNodeList(doc, "/root/post");
        List<Post> postList = new ArrayList<Post>();
        Post post = null;
        for (Node node : postNodes) {
            String title = XMLUtils.parseForString(node, "post_title");
            if (StringUtils.isNotBlank(title)) {
                Post temp_post = ServiceFactory.getPostService().getPostByTitle(title.trim());
                if (temp_post != null) {
                    System.out.println(title + "   存在...");
                    return;
                }
                post = new Post();
                String content = XMLUtils.parseForString(node, "post_content");
                String status = XMLUtils.parseForString(node, "comment_status");
                String password = XMLUtils.parseForString(node, "post_password");
                String modify_time = XMLUtils.parseForString(node, "post_modified");
                String post_time = XMLUtils.parseForString(node, "post_date");
                
                post.setTitle(title);
                post.setContent(content);
                if ("open".equals(status)) {
                    post.setCommentStatus(Post.CommentStatus.OPEN);
                }
                if (StringUtils.isNotBlank(password)) {
                    post.setPassword(password);
                }
                post.setModifyTime(DateUtils.parse(modify_time, DateUtils.YYYY_MM_DD_HH_MM_SS));
                post.setCreateTime(DateUtils.parse(post_time, DateUtils.YYYY_MM_DD_HH_MM_SS));
                
                List<Node> catalogNodes = XMLUtils.parseForNodeList(node, "catalogs");
                if (catalogNodes == null || catalogNodes.size() == 0) {
                    Set<Catalog> catalogs = new HashSet<Catalog>();
                    Catalog catalog = new Catalog();
                    catalog.setId(new Long(1));
                    catalogs.add(catalog);
                    post.setCatalogs(catalogs);
                }
                else {
                    Set<Catalog> catalogs = new HashSet<Catalog>();
                    for (Node catalogNode : catalogNodes) {
                        String catalogName = XMLUtils.parseForString(catalogNode, "name");
                        if (StringUtils.isNotBlank(catalogName)) {
                            Catalog catalog = ServiceFactory.getCatalogService().getCatalogByName(catalogName.trim());
                            if (catalog == null) {
                                catalog = new Catalog();
                                catalog.setCreateTime(new Date());
                                catalog.setName(catalogName.trim());
                                catalog = ServiceFactory.getCatalogService().createCatalog(catalog);
                            }
                            catalogs.add(catalog);
                        }
                    }
                    post.setCatalogs(catalogs);
                }
                
                
                List<Node> tagNodes = XMLUtils.parseForNodeList(node, "catalogs");
                if (tagNodes == null || tagNodes.size() == 0) {
                }
                else {
                    Set<Tag> tags = new HashSet<Tag>();
                    for (Node tagNode : tagNodes) {
                        String tagName = XMLUtils.parseForString(tagNode, "name");
                        if (StringUtils.isNotBlank(tagName)) {
                            Tag tag = ServiceFactory.getTagService().findUniqueByName(tagName.trim());
                            if (tag == null) {
                                tag = new Tag();
                                tag.setCreateTime(new Date());
                                tag.setName(tagName.trim());
                                tag = ServiceFactory.getTagService().createTag(tag);
                            }
                            tags.add(tag);
                        }
                    }
                    post.setTags(tags);
                }
                
                User user = new User();
                user.setId(new Long(1));
                post.setAuthor(user);
                postList.add(post);
            }
        }
        
        Collections.sort(postList, new Comparator<Post>() {

            @Override
            public int compare(Post o1, Post o2) {
                // TODO Auto-generated method stub
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }
        });
        
        for (Post p : postList) {
            try {
                ServiceFactory.getPostService().createPost(p);
            }
            catch (Exception e) {
                // TODO: handle exception
                continue;
            }
        }
    }
}
