/**
 * 
 */
package org.mspring.msns.api.spider.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Gao Youbo
 * @since 2013-5-3
 * @description
 * @TODO
 */
@Entity
@Table(name = "spider_post")
public class SpiderPost implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6770775468259198139L;

    private Long id;
    private Rule rule;
    private String title;
    private String content;
    private Date createTime;
    private Boolean posted;
    private Boolean postTime;

    /**
     * 
     */
    public SpiderPost() {
        // TODO Auto-generated constructor stub
    }

    public SpiderPost(Long id) {
        super();
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 30)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = Rule.class)
    @JoinColumn(name = "rule")
    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    @Column(name = "title", unique = false, nullable = false, length = 200)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content", nullable = false, columnDefinition = "text")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "create_time", length = 100)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "posted")
    public Boolean getPosted() {
        return posted;
    }

    public void setPosted(Boolean posted) {
        this.posted = posted;
    }

    @Column(name = "post_time", length = 100)
    public Boolean getPostTime() {
        return postTime;
    }

    public void setPostTime(Boolean postTime) {
        this.postTime = postTime;
    }
}
