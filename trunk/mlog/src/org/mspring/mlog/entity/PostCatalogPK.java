/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Gao Youbo
 * @since 2012-8-6
 * @Description
 * @TODO
 */
@Embeddable
public class PostCatalogPK implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1191581056842495920L;

    private Post post;
    private Catalog catalog;

    /**
     * 
     */
    public PostCatalogPK() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param post
     * @param catalog
     */
    public PostCatalogPK(Post post, Catalog catalog) {
        super();
        this.post = post;
        this.catalog = catalog;
    }

    /**
     * @return the post
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Post.class)
    @JoinColumn(name = "post_id")
    public Post getPost() {
        return post;
    }

    /**
     * @param post
     *            the post to set
     */
    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * @return the catalog
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Catalog.class)
    @JoinColumn(name = "catalog_id")
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

}
