/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author Gao Youbo
 * @since 2012-12-1
 * @Description
 * @TODO
 */
@Entity
@Table(name = "link_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LinkType implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8396812083286816709L;

    private Long id;
    private String name;
    private Long order;
    private String description;
    private Boolean visable;

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
    @Column(name = "description", length = 2000)
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

    /**
     * @return the visable
     */
    @Column(name = "visable")
    public Boolean getVisable() {
        return visable;
    }

    /**
     * @param visable
     *            the visable to set
     */
    public void setVisable(Boolean visable) {
        this.visable = visable;
    }

    public static class Visable {
        public static final String VISABLE = "true";
        public static final String HIDDEN = "false";

        public static final Map<String, String> getVisableMap() {
            Map<String, String> map = new HashMap<String, String>();
            map.put(VISABLE, "显示");
            map.put(HIDDEN, "不显示");
            return map;
        }
    }

}
