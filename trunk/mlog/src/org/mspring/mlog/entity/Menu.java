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
 * Menu entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "menu")
public class Menu implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 6208086256621726053L;

    private Long id;
    private String name;
    private Integer type;
    private Long parent;
    private Integer order;
    private String description;
    private Date createTime;
    private String url;
    private Long category;

    // Constructors

    /** default constructor */
    public Menu() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "menu_id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "menu_name", nullable = false, length = 225)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "menu_type", nullable = false)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "menu_parent", nullable = true)
    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    @Column(name = "menu_order")
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Column(name = "menu_description", columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "menu_createtime", length = 30)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "menu_url", length = 1000)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "menu_category", nullable = true)
    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

}