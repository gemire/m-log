/**
 * 
 */
package org.mspring.mlog.api.duoshuo.vo;

import java.io.Serializable;

/**
 * @author Gao Youbo
 * @since 2013-5-3
 * @description
 * @TODO 文章
 * 
 */
public class DSPost implements Serializable {

    /********************** 必填属性 **********************/
    private String thread_key; // 文章在原站点中的id

    private String title; // 文章标题

    private String url; // 文章的URL地址

    /********************** 可选属性 **********************/
    private String content; // 文章内容

    private String author_key; // 文章作者在原站点中的用户id

    private String excerpt; // 文章摘要

    private String comment_status; // [open/close]文章是否开启评论

    private String likes; // 文章被【喜欢】的次数，该属性导入意义不大，会在被喜欢之后重新统计

    private String views; // 文章被查看的次数

    public String getThread_key() {
        return thread_key;
    }

    public void setThread_key(String thread_key) {
        this.thread_key = thread_key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor_key() {
        return author_key;
    }

    public void setAuthor_key(String author_key) {
        this.author_key = author_key;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getComment_status() {
        return comment_status;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

}
