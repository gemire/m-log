package org.mspring.mlog.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Comment entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comment")
public class Comment implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5316670058554652527L;

    /**
     * 待审核
     */
    public static final Integer STATUS_CHECK = 0;

    /**
     * 审核通过
     */
    public static final Integer STATUS_PASS = 1;

    /**
     * 驳回
     */
    public static final Integer STATUS_REJECT = 2;

    /**
     * 垃圾评论
     */
    public static final Integer STATUS_DUST = 3;

    // Fields
    private Long id;
    private Article article;
    private String author;
    private String content;
    private String email;
    private String homePage;
    private Date createTime;
    private String ip;
    private String agent;
    private Integer status;

    // Constructors

    /** default constructor */
    public Comment() {
    }

    /** full constructor */
    public Comment(Article article, String author, String content, String email, String homePage, Date createTime, String ip, String agent) {
        this.article = article;
        this.author = author;
        this.content = content;
        this.email = email;
        this.homePage = homePage;
        this.createTime = createTime;
        this.ip = ip;
        this.agent = agent;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "comment_id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Article.class)
    @JoinColumn(name = "comment_article")
    public Article getArticle() {
        return this.article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Column(name = "comment_author", length = 100)
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "comment_content", columnDefinition = "text")
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "comment_email", length = 50)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "comment_homepage")
    public String getHomePage() {
        return this.homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "comment_createtime", length = 50)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "comment_ip", length = 15)
    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "agent", length = 200)
    public String getAgent() {
        return this.agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    @Column(name = "comment_status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}