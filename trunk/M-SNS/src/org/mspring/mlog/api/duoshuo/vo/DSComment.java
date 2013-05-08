/**
 * 
 */
package org.mspring.mlog.api.duoshuo.vo;

import java.io.Serializable;

/**
 * @author Gao Youbo
 * @since 2013-5-3
 * @description 多说Comment
 * @TODO
 */
public class DSComment implements Serializable {
    /********************** 必填属性 **********************/
    private String post_key; // 这条评论在当前站点的ID

    private String thread_key; // 这条评论所对应文章在当前站点的ID

    private String message; // 评论内容

    /********************** 可选属性 **********************/
    private String post_id; // 对于从多说导出的数据，再次导入多说，会根据此id进行匹配，自动忽略post_key参数

    private String parent_key; // 父级评论在当前站点的ID

    private String author_key; // 评论者在当前站点的ID

    private String author_name; // 评论者的用户名

    private String author_email; // 评论者的邮箱，如果没有设置头像会根据这个信息从gravatar获取头像

    private String author_url; // 评论者的URL，点击评论者头像或者名字会跳转到改URL

    private String ip; // 评论者的IP

    private String agent; // 评论者User Agent信息，通常包括浏览器版本、引擎、设备等信息

    private String likes; // 这条评论被【赞】的次数，该属性导入意义不大，会在被喜欢之后重新统计

    private String reports; // 对这条评论点了【举报】的次数

    private String created_at; // 评论发表时间。请注意时间的格式为yyyy-mm-dd
                               // hh:MM:ss。如2012/12/12 12:12:12和 2012-1-12
                               // 12:12:12的格式，都不会被成功识别，第二个时间是因为月份应为01

    private String status; // 评论状态。支持：approved：已通过，pending：待审核，deleted：已删除。不建议将已删除数据导入多说，会在到期30天之后自动从数据库中清除。

    public String getPost_key() {
        return post_key;
    }

    public void setPost_key(String post_key) {
        this.post_key = post_key;
    }

    public String getThread_key() {
        return thread_key;
    }

    public void setThread_key(String thread_key) {
        this.thread_key = thread_key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getParent_key() {
        return parent_key;
    }

    public void setParent_key(String parent_key) {
        this.parent_key = parent_key;
    }

    public String getAuthor_key() {
        return author_key;
    }

    public void setAuthor_key(String author_key) {
        this.author_key = author_key;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_email() {
        return author_email;
    }

    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    public String getAuthor_url() {
        return author_url;
    }

    public void setAuthor_url(String author_url) {
        this.author_url = author_url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getReports() {
        return reports;
    }

    public void setReports(String reports) {
        this.reports = reports;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
