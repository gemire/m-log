/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

/**
 * @author Gao Youbo
 * @since 2012-7-27
 * @Description
 * @TODO
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 4508095889920768217L;

    @Expose
    private Long id;
    @Expose
    private String author;
    @Expose
    private String email;
    @Expose
    private String url;
    @Expose
    private String content;
    @Expose
    private Date createTime;
    @Expose
    private String postIp;
    @Expose
    private String agent;
    private Post post;
    @Expose
    private String status;
    @Expose
    private Comment parent;
    private Set<Comment> comments;

    /**
     * 
     */
    public Comment() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param id
     */
    public Comment(Long id) {
        super();
        this.id = id;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 30)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the author
     */
    @Column(name = "author", nullable = false, length = 100)
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the email
     */
    @Column(name = "email", length = 100)
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the url
     */
    @Column(name = "url", length = 200)
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the content
     */
    @Column(name = "content", nullable = false, length = 5000)
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the createTime
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, length = 50)
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the postIp
     */
    @Column(name = "post_id", length = 50)
    public String getPostIp() {
        return postIp;
    }

    /**
     * @param postIp
     *            the postIp to set
     */
    public void setPostIp(String postIp) {
        this.postIp = postIp;
    }

    /**
     * @return the agent
     */
    @Column(name = "agent", length = 1000)
    public String getAgent() {
        return agent;
    }

    /**
     * @param agent
     *            the agent to set
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }

    /**
     * @return the post
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = Post.class)
    @JoinColumn(name = "post")
    public Post getPost() {
        return post;
    }

    /**
     * @param post
     *            the post to set
     */
    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * @return the status
     */
    @Column(name = "status", nullable = false, length = 30)
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the parent
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = Comment.class)
    @JoinColumn(name = "parent_id", nullable = true)
    public Comment getParent() {
        return parent;
    }

    /**
     * @param parent
     *            the parent to set
     */
    public void setParent(Comment parent) {
        this.parent = parent;
    }

    /**
     * @return the comments
     */
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Comment.class, mappedBy = "parent")
    public Set<Comment> getComments() {
        return comments;
    }

    /**
     * @param comments
     *            the comments to set
     */
    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public static final class Status {
        public static final String APPROVED = "approved";
        public static final String WAIT_FOR_APPROVE = "wait_for_approve";
        public static final String SPAM = "spam";
        public static final String RECYCLE = "recycle";
        public static final List<String> COMMENT_STATUS = Arrays.asList(new String[] { APPROVED, WAIT_FOR_APPROVE, SPAM, RECYCLE });

        public static final Map<String, String> getCommentStatusMap() {
            Map<String, String> commentStatus = new HashMap<String, String>();
            commentStatus.put(APPROVED, "审核通过");
            commentStatus.put(WAIT_FOR_APPROVE, "等待审核");
            commentStatus.put(SPAM, "垃圾评论");
            commentStatus.put(RECYCLE, "回收站");
            return commentStatus;
        }
    }

}
