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
 * @since 2012-7-25
 * @Description
 * @TODO
 */
@Entity
@Table(name = "post_tag")
public class PostTag implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1125734762805268018L;
    private PostTagPK PK;

    /**
     * 
     */
    public PostTag() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param pK
     */
    public PostTag(PostTagPK pK) {
        super();
        PK = pK;
    }

    /**
     * @return the pK
     */
    @EmbeddedId
    @AttributeOverrides({ @AttributeOverride(name = "postId", column = @Column(name = "post_id", nullable = false)), @AttributeOverride(name = "tagId", column = @Column(name = "tag_id", nullable = false)) })
    public PostTagPK getPK() {
        return PK;
    }

    /**
     * @param pK
     *            the pK to set
     */
    public void setPK(PostTagPK pK) {
        PK = pK;
    }

}
