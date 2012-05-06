package org.mspring.mlog.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.google.gson.annotations.Expose;

/**
 * Tag entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tag")
@Indexed(index = "tag")
public class Tag implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 8678449035971089227L;

    @Expose
    private Long id;
    @Expose
    private String name;
    private String intro;
    private Integer count;
    private Set<Article> articles;

    // Constructors

    /** default constructor */
    public Tag() {
    }

    /**
     * @param id
     */
    public Tag(Long id) {
        super();
        this.id = id;
    }

    /**
     * @param name
     */
    public Tag(String name) {
        super();
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "tag_id", unique = true, nullable = false)
    @DocumentId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "tag_name")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "tag_intro", columnDefinition = "text")
    @Field(index = Index.TOKENIZED, store = Store.YES)
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Column(name = "tag_count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @ManyToMany(cascade = { CascadeType.MERGE }, mappedBy = "tags", targetEntity = Article.class)
    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

}