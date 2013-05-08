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
 * @since 2012-7-25
 * @Description
 * @TODO
 */
@Embeddable
public class PostTagPK implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -893280819372052490L;
    private Post post;
    private Tag tag;

    /**
     * 
     */
    public PostTagPK() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param post
     * @param tag
     */
    public PostTagPK(Post post, Tag tag) {
        super();
        this.post = post;
        this.tag = tag;
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
     * @return the tag
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Tag.class)
    @JoinColumn(name = "tag_id")
    public Tag getTag() {
        return tag;
    }

    /**
     * @param tag
     *            the tag to set
     */
    public void setTag(Tag tag) {
        this.tag = tag;
    }

}
