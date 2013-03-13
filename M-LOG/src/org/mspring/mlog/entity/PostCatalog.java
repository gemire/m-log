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
 * @since 2012-8-6
 * @Description
 * @TODO
 */
@Entity
@Table(name = "post_catalog")
public class PostCatalog implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -9200208998380307974L;

    private PostCatalogPK PK;

    /**
     * 
     */
    public PostCatalog() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param pK
     */
    public PostCatalog(PostCatalogPK pK) {
        super();
        PK = pK;
    }

    /**
     * @return the pK
     */
    @EmbeddedId
    @AttributeOverrides({ @AttributeOverride(name = "postId", column = @Column(name = "post_id", nullable = false)), @AttributeOverride(name = "catalogId", column = @Column(name = "catalog_id", nullable = false)) })
    public PostCatalogPK getPK() {
        return PK;
    }

    /**
     * @param pK
     *            the pK to set
     */
    public void setPK(PostCatalogPK pK) {
        PK = pK;
    }

}
