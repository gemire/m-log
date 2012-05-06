package org.mspring.mlog.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "article_tag")
public class ArticleTag implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3572400176826736025L;
    private ArticleTagPK PK;

    // Constructors

    /** default constructor */
    public ArticleTag() {
    }

    /** full constructor */
    public ArticleTag(ArticleTagPK PK) {
        this.PK = PK;
    }

    // Property accessors

    @EmbeddedId
    @AttributeOverrides( { @AttributeOverride(name = "articleId", column = @Column(name = "article_id", nullable = false)), @AttributeOverride(name = "tagId", column = @Column(name = "tag_id", nullable = false)) })
    public ArticleTagPK getPK() {
        return PK;
    }

    public void setPK(ArticleTagPK pk) {
        PK = pk;
    }

}