/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Gao Youbo
 * @since 2012-9-7
 * @Description
 * @TODO
 */
@Entity
@Table(name = "postmeta")
public class PostMeta implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6245756097689735247L;

    private Long id;
    private Long postId;
    private String key;
    private String value;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 30)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the postId
     */
    @Column(name = "post_id", unique = false, nullable = false, length = 30)
    public Long getPostId() {
        return postId;
    }

    /**
     * @param postId
     *            the postId to set
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    /**
     * @return the key
     */
    @Column(name = "key", unique = false, nullable = false, length = 200)
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the value
     */
    @Column(name = "value", columnDefinition = "text")
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}
