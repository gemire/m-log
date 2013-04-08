/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.mspring.mlog.service.cache.CacheService;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2012-7-12
 * @Description
 * @TODO
 */
@Entity
@Table(name = "catalog")
@Embeddable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Catalog implements Serializable, Comparable<Catalog> {

    /**
     * 
     */
    private static final long serialVersionUID = -8509725298839555206L;

    private Long id;
    private String name;
    private Date createTime;
    private Date modifyTime;
    private Long order;
    private String description;
    private Catalog parent; // 父分类

    private int deep = 1;
    private boolean hasChild = false; // 是否有自分类

    /**
     * 
     */
    public Catalog() {
        // TODO Auto-generated constructor stub
    }

    public Catalog(Long id) {
        super();
        this.id = id;
    }

    public Catalog(Long id, String name, Long parent) {
        super();
        this.id = id;
        this.name = name;
        this.parent = new Catalog(parent);
    }

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
     * @return the name
     */
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the createTime
     */
    @Column(name = "create_time", length = 100, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the modifyTime
     */
    @Column(name = "modify_time", length = 100)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     *            the modifyTime to set
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * @return the order
     */
    @Column(name = "order", length = 30)
    public Long getOrder() {
        return order;
    }

    /**
     * @param order
     *            the order to set
     */
    public void setOrder(Long order) {
        this.order = order;
    }

    /**
     * @return the description
     */
    @Column(name = "description", columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = CacheService.CacheName.LAZY_CACHE_NAME)
    @ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = Catalog.class)
    @JoinColumn(name = "parent")
    public Catalog getParent() {
        return parent;
    }

    public void setParent(Catalog parent) {
        this.parent = parent;
    }

    @Transient
    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    @Transient
    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (obj != null && obj instanceof Tag) {
            Catalog c = (Catalog) obj;
            if (StringUtils.isNotBlank(c.getName()) && c.getName().equals(this.getName())) {
                return true;
            }
            if (c.getId() != null && c.getId().equals(this.getId())) {
                return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Catalog c2) {
        // TODO Auto-generated method stub
        // int value = 0;
        // Long o_1 = this.order;
        // Long o_2 = c2.getOrder();
        // if (o_2 == null || o_1 == null) {
        // return 0;
        // }
        // value = o_1 > o_2 ? -1 : (o_1.equals(o_2) ? 0 : 1);
        // return value;

        int flag = 0;
        if (c2.getOrder() == null && this.getOrder() != null) {
            flag = -1;
        } else if (c2.getOrder() != null && this.getOrder() == null) {
            flag = 1;
        } else if (c2.getOrder() != null && this.getOrder() != null) {
            flag = this.getOrder() < c2.getOrder() ? -1 : 1;
        } else if (c2.getOrder() == null && this.getOrder() == null) {
            flag = this.getId() < c2.getId() ? 1 : -1;
        }
        return flag;
    }

}
