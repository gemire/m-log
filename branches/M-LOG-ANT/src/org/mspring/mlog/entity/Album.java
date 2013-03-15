/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

/**
 * @author Gao Youbo
 * @since 2012-10-15
 * @Description
 * @TODO
 */
@Entity
@Table(name = "album")
public class Album implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3450664814533184168L;

    // Fields

    private Long id;
    private String name;
    private String description;
    private Integer photoCount;
    private Integer sortOrder;
    private Date createTime;
    private Date modifyTime;
    private Photo cover;
    private String verifycode;
    private String type;

    // Constructors

    /** default constructor */
    public Album() {
    }

    /**
     * @param id
     */
    public Album(Long id) {
        super();
        this.id = id;
    }

    /** minimal constructor */
    public Album(String name) {
        this.name = name;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", columnDefinition = "text")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "photoCount")
    public Integer getPhotoCount() {
        return this.photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    @Column(name = "sortOrder")
    public Integer getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createTime", length = 30)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modifyTime", length = 30)
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = Photo.class)
    @JoinColumn(name = "cover", nullable = true)
    public Photo getCover() {
        return cover;
    }

    public void setCover(Photo cover) {
        this.cover = cover;
    }

    @Column(name = "verifycode", length = 100)
    public String getVerifycode() {
        return this.verifycode;
    }

    public void setVerifycode(String verifycode) {
        this.verifycode = verifycode;
    }

    @Column(name = "type")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class Type {
        public final static String PUBLIC = "public"; // 完全公开
        public final static String VERIFIED = "verified";// 需要密码访问
        public final static String PRIVATE = "private"; // 私人相册

        public static final Map<String, String> getType() {
            Map<String, String> map = new HashMap<String, String>();
            map.put(PUBLIC, "完全公开");
            map.put(PRIVATE, "私人相册");
            map.put(VERIFIED, "需要密码访问");
            return map;
        }
    }
}
