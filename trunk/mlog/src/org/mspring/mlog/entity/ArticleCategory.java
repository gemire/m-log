/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Gao Youbo
 * @since Apr 18, 2012
 */
@Entity
@Table(name = "article_category")
public class ArticleCategory implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3166970465618478234L;
    
    private ArticleCategoryPK PK;

    /**
     * 
     */
    public ArticleCategory() {
        super();
    }

    /**
     * @param pk
     */
    public ArticleCategory(ArticleCategoryPK pk) {
        super();
        PK = pk;
    }

    @EmbeddedId
    @AttributeOverrides( { @AttributeOverride(name = "articleId", column = @Column(name = "article_id", nullable = false)), @AttributeOverride(name = "categoryId", column = @Column(name = "category_id", nullable = false)) })
    public ArticleCategoryPK getPK() {
        return PK;
    }

    public void setPK(ArticleCategoryPK pk) {
        PK = pk;
    }
    
}
