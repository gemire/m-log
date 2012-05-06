package org.mspring.mlog.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.google.gson.annotations.Expose;

/**
 * Category entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "category")
@Indexed(index = "category")
public class Category implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = -1085059115476593272L;

    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private Integer order;
    @Expose
    private String intro;
    @Expose
    private Date createTime;
    @Expose
    private Date modifyTime;

    private Set<Article> articles;

    // Constructors

    /** default constructor */
    public Category() {
    }

    public Category(Long id) {
        this.id = id;
    }

    /** full constructor */
    public Category(Long id, String name, Integer order, String intro, Date createTime, Date modifyTime) {
        super();
        this.id = id;
        this.name = name;
        this.order = order;
        this.intro = intro;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "category_id", unique = true, nullable = false)
    @Field(index = Index.UN_TOKENIZED, store = Store.YES)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "category_name", length = 50)
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "category_order")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Column(name = "category_intro", length = 3000)
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "category_createtime", length = 10)
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "category_modifytime", length = 10)
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @ManyToMany(cascade = { CascadeType.MERGE }, mappedBy = "categories", targetEntity = Article.class)
    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

}