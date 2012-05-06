/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * @author Gao Youbo
 * @since Apr 17, 2012
 */
@Embeddable
@Indexed(index = "articleCategory")
public class ArticleCategoryPK implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -4714288785762238640L;

    private Article article;
    private Category category;

    public ArticleCategoryPK() {

    }

    /**
     * @param article
     * @param category
     */
    public ArticleCategoryPK(Article article, Category category) {
        super();
        this.article = article;
        this.category = category;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    @IndexedEmbedded
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof ArticleCategoryPK))
            return false;
        ArticleCategoryPK castOther = (ArticleCategoryPK) other;

        return ((this.getArticle() == castOther.getArticle()) || (this.getArticle() != null && castOther.getArticle() != null && this.getArticle().equals(castOther.getArticle()))) && ((this.getCategory() == castOther.getCategory()) || (this.getCategory() != null && castOther.getCategory() != null && this.getCategory().equals(castOther.getCategory())));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (getArticle() == null ? 0 : this.getArticle().hashCode());
        result = 37 * result + (getCategory() == null ? 0 : this.getCategory().hashCode());
        return result;
    }

}
