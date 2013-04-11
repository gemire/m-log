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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.cache.CacheService;
import org.mspring.mlog.support.formater.stereotype.CatalogFormat;
import org.mspring.mlog.support.formater.stereotype.TagFormat;

import com.google.gson.annotations.Expose;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
@Entity
@Table(name = "post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Indexed(index = "post")
public class Post implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -425742660561620768L;

    @Expose
    private Long id;
    @Expose
    private String title;
    
    private Set<Catalog> catalogs;
    private Set<Tag> tags;
    private String summary;
    private String content;
    private Date createTime;
    private Date modifyTime;
    private User author;
    private String status;
    private String password;
    private String commentStatus;
    private Long commentCount;
    private String url;//来源链接
    private String postIp;
    private Long viewCount;
    private Boolean isTop;
    private Set<Comment> comments;
    private String site;//来源站点

    /**
     * 
     */
    public Post() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param id
     */
    public Post(Long id) {
        super();
        this.id = id;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 30)
    @DocumentId
    @Field(name = "id", index = Index.TOKENIZED, store = Store.YES)
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
     * @return the title
     */
    @Column(name = "title", unique = false, nullable = false, length = 200)
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the summary
     */
    @Column(name = "summary", columnDefinition = "text")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     *            the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the content
     */
    @Column(name = "content", nullable = false, columnDefinition = "text")
    @Field(index = Index.TOKENIZED, store = Store.NO)
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
    @Column(name = "create_time", length = 100)
    @Temporal(TemporalType.TIMESTAMP)
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
     * @return the modifyTime
     */
    @Column(name = "modify_time", length = 100)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     *            the modifyTime to set
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * @return the author
     */
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = CacheService.CacheName.LAZY_CACHE_NAME)
    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = User.class)
    @JoinColumn(name = "post_user")
    public User getAuthor() {
        return author;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * @return the status
     */
    @Column(name = "post_status", nullable = false, length = 20)
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
     * @return the password
     */
    @Column(name = "password", length = 30)
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the commentStatus
     */
    @Column(name = "comment_status", nullable = false, length = 20)
    public String getCommentStatus() {
        return commentStatus;
    }

    /**
     * @param commentStatus
     *            the commentStatus to set
     */
    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    /**
     * @return the commentCount
     */
    @Column(name = "comment_count", length = 20)
    public Long getCommentCount() {
        return commentCount;
    }

    /**
     * @param commentCount
     *            the commentCount to set
     */
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * @return the tags
     */
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = CacheService.CacheName.LAZY_CACHE_NAME)
    @ManyToMany(targetEntity = Tag.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinTable(name = "post_tag", joinColumns = { @JoinColumn(name = "post_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    @TagFormat
    public Set<Tag> getTags() {
        return tags;
    }

    /**
     * @param tags
     *            the tags to set
     */
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    /**
     * @return the catalogs
     */
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = CacheService.CacheName.LAZY_CACHE_NAME)
    @ManyToMany(targetEntity = Catalog.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinTable(name = "post_catalog", joinColumns = { @JoinColumn(name = "post_id") }, inverseJoinColumns = { @JoinColumn(name = "catalog_id") })
    @CatalogFormat
    public Set<Catalog> getCatalogs() {
        return catalogs;
    }

    /**
     * @param catalogs
     *            the catalogs to set
     */
    public void setCatalogs(Set<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    /**
     * @return the url
     */
    @Column(name = "url", length = 300)
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        if (url != null) { // 剔除链接中的空格
            this.url = url.trim();
        }
    }
    
    /**
     * 
     * @return site
     */
    public String getSite() {
		return site;
	}
    
    /**
     *  
     * @param site
     */
    @Column(name="site",length=300)
    public void setSite(String site) {
		this.site = site;
	}

    /**
     * @return the postIp
     */
    @Column(name = "post_ip", length = 50)
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
     * @return the viewCount
     */
    @Column(name = "view_count", length = 20)
    public Long getViewCount() {
        return viewCount;
    }

    /**
     * @param viewCount
     *            the viewCount to set
     */
    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * @return the isTop
     */
    @Column(name = "is_top", length = 2)
    public Boolean getIsTop() {
        return isTop;
    }

    /**
     * @param isTop
     *            the isTop to set
     */
    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    /**
     * @return the comments
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = Comment.class, mappedBy = "post")
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

    public static class Status {
        public static final String PUBLISH = "publish"; // 已发布的
        public static final String DRAFT = "draft"; // 草稿
        public static final String TRASH = "trash"; // 废弃的
        public static final List<String> STATUS = Arrays.asList(new String[] { PUBLISH, DRAFT, TRASH });

        public static final Map<String, String> getStatusMap() {
            Map<String, String> map = new HashMap<String, String>();
            map.put(PUBLISH, "已发布");
            map.put(DRAFT, "草稿");
            map.put(TRASH, "回收站");
            return map;
        }
    }

    public static class CommentStatus {
        public static final String OPEN = "open"; // 允许评论
        public static final String CLOSE = "close"; // 关闭评论
        public static final List<String> COMMENT_STATUS = Arrays.asList(new String[] { OPEN, CLOSE });

        public static final Map<String, String> getCommentStatusMap() {
            Map<String, String> map = new HashMap<String, String>();
            map.put(OPEN, "开启");
            map.put(CLOSE, "关闭");
            return map;
        }
    }

}
