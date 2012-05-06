package org.mspring.mlog.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name = "article")
@Indexed(index = "article")
public class Article implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = -3692994277659071883L;

    private Long id;
    private String url;
    private String title;
    private String author;
    private boolean publish;
    private String password;
    private boolean commented;
    private String intro;
    private String content;
    private String ip;
    private Date createTime;
    private Date modifyTime;
    private Long commentNums;
    private Long viewNums;
    private boolean isTop;
    private boolean isDeleted;
    private Set<Category> categories;
    private Set<Tag> tags;

    // Constructors

    /** default constructor */
    public Article() {
    }

    /**
     * @param id
     */
    public Article(Long id) {
        super();
        this.id = id;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "article_id", unique = true, nullable = false)
    @DocumentId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "article_url")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "article_title")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "article_author")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public String getAuthor() {
        return author;
    }

    @Field(index = Index.TOKENIZED, store = Store.YES)
    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "article_intro", columnDefinition = "text")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Column(name = "article_content", columnDefinition = "text")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "article_ip", length = 15)
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "article_createtime")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "article_modifytime")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Column(name = "article_commentnums")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public Long getCommentNums() {
        return commentNums;
    }

    public void setCommentNums(Long commentNums) {
        this.commentNums = commentNums;
    }

    @Column(name = "article_viewnums")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public Long getViewNums() {
        return viewNums;
    }

    public void setViewNums(Long viewNums) {
        this.viewNums = viewNums;
    }

    @Column(name = "article_publish")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    @Column(name = "article_password", length = 200)
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "article_commented")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public boolean isCommented() {
        return commented;
    }

    public void setCommented(boolean commented) {
        this.commented = commented;
    }

    @Column(name = "article_istop")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean isTop) {
        this.isTop = isTop;
    }

    @Column(name = "article_isdeleted")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @ManyToMany(targetEntity = Category.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinTable(name = "article_category", joinColumns = { @JoinColumn(name = "article_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
    @IndexedEmbedded
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @ManyToMany(targetEntity = Tag.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinTable(name = "article_tag", joinColumns = { @JoinColumn(name = "article_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    @IndexedEmbedded
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

}