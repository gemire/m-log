/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.mspring.mlog.web.formatter.stereotype.TagFormat;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
@Entity
@Table(name = "post")
@Indexed(index = "post")
public class Post implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -425742660561620768L;
    public static final String POST_STATUS_PUBLISH = "publish"; // 已发布的
    public static final String POST_STATUS_TRASH = "trash"; // 废弃的

    public static final String COMMENT_STATUS_OPEN = "open"; // 允许评论
    public static final String COMMENT_STATUS_CLOSE = "close"; // 关闭评论

    private Long id;
    private String title;
    private Catalog catalog;
    private List<Tag> tags;
    private String summary;
    private String content;
    private Date createTime;
    private Date modifyTime;
    private User author;
    private String status;
    private String password;
    private String commentStatus;
    private Long commentCount;
    private String url;

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
    @Column(name = "title", unique = true, nullable = false, length = 200)
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
     * @return the catalog
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = Catalog.class)
    @JoinColumn(name = "catalog")
    @IndexedEmbedded(depth = 1, targetElement = Catalog.class)
    public Catalog getCatalog() {
        return catalog;
    }

    /**
     * @param catalog
     *            the catalog to set
     */
    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    /**
     * @return the summary
     */
    @Column(name = "summary", length = 4000)
    @Field(index = Index.TOKENIZED, store = Store.NO)
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
    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = User.class)
    @JoinColumn(name = "post_user")
    @IndexedEmbedded
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
    @ManyToMany(targetEntity = Tag.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinTable(name = "post_tag", joinColumns = { @JoinColumn(name = "post_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    @TagFormat
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * @param tags
     *            the tags to set
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    /**
     * @return the url
     */
    @Column(name = "url", nullable = false, unique = true, length = 500)
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

}
