/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.mspring.mlog.entity.security.User;

/**
 * @author Gao Youbo
 * @since 2013-2-19
 * @description
 * @TODO
 */
@Entity
@Table(name = "attachment")
public class Attachment implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8262841998174621076L;

    private Long id;
    private String path;
    private Long size;
    private Date uploadTime;
    private User user;

    /**
     * 
     */
    public Attachment() {
        // TODO Auto-generated constructor stub
    }

    public Attachment(Long id) {
        super();
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 30)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "path", unique = true, nullable = false, length = 200)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "size", length = 30)
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "upload_time", length = 100)
    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
    @JoinColumn(name = "user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
