package org.mspring.mlog.entity;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * ArticleTagId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
@Indexed(index = "articleTag")
public class ArticleTagPK implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 4314077331584698743L;

    private Article article;
    private Tag tag;

    /**
     * 
     */
    public ArticleTagPK() {
        // TODO Auto-generated constructor stub
    }

    // Property accessors

    /**
     * @param article
     * @param tag
     */
    public ArticleTagPK(Article article, Tag tag) {
        super();
        this.article = article;
        this.tag = tag;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Article.class)
    @JoinColumn(name = "article_id")
    @IndexedEmbedded
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Tag.class)
    @JoinColumn(name = "tag_id")
    @IndexedEmbedded
    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof ArticleTagPK))
            return false;
        ArticleTagPK castOther = (ArticleTagPK) other;

        return ((this.getArticle() == castOther.getArticle()) || (this.getArticle() != null && castOther.getArticle() != null && this.getArticle().equals(castOther.getArticle()))) && ((this.getTag() == castOther.getTag()) || (this.getTag() != null && castOther.getTag() != null && this.getTag().equals(castOther.getTag())));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (getArticle() == null ? 0 : this.getArticle().hashCode());
        result = 37 * result + (getTag() == null ? 0 : this.getTag().hashCode());
        return result;
    }

}