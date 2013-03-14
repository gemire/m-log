/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
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
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Catalog implements Serializable {

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

}
