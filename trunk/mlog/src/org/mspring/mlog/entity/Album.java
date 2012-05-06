package org.mspring.mlog.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Album entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "album")
public class Album implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4795148337000706227L;

    public final static Integer TYPE_PUBLIC = new Integer(0); // 完全公开
    public final static Integer TYPE_VERIFIED = new Integer(1);// 需要密码访问
    public final static Integer TYPE_PRIVATE = new Integer(2); // 私人相册

    // Fields

    private Long id;
    private String name;
    private String description;
    private Integer photoCount;
    private Integer sortOrder;
    private Date createTime;
    private Integer cover;
    private String verifycode;
    private Integer type;

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

    /** full constructor */
    public Album(String name, String description, Integer photoCount, Integer sortOrder, Date createTime, Integer cover, String verifycode, Integer type) {
        this.name = name;
        this.description = description;
        this.photoCount = photoCount;
        this.sortOrder = sortOrder;
        this.createTime = createTime;
        this.cover = cover;
        this.verifycode = verifycode;
        this.type = type;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
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

    @Column(name = "cover")
    public Integer getCover() {
        return this.cover;
    }

    public void setCover(Integer cover) {
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
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}