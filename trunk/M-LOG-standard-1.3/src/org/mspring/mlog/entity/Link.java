/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.mspring.mlog.service.cache.CacheService;

/**
 * @author Gao Youbo
 * @since 2012-7-27
 * @Description
 * @TODO
 */
@Entity
@Table(name = "links")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Link implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7873304315678342275L;

    private Long id;
    private String name;
    private String url;
    private String target;
    private String description;
    private Long order;
    private Boolean visable;
    private LinkType type;

    /**
     * 
     */
    public Link() {
        // TODO Auto-generated constructor stub
    }

    public Link(Long id) {
        super();
        this.id = id;
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
     * @return the url
     */
    @Column(name = "url", nullable = false, length = 100)
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the target
     */
    @Column(name = "target", length = 50)
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     *            the target to set
     */
    public void setTarget(String target) {
        this.target = target;
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

    /**
     * @return the type
     */
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = CacheService.CacheName.LAZY_CACHE_NAME)
    @ManyToOne(fetch = FetchType.LAZY, optional = true, targetEntity = LinkType.class)
    @JoinColumn(name = "type")
    public LinkType getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(LinkType type) {
        this.type = type;
    }

    public static class Target {
        public static final String _BLANK = "_blank"; // 新窗口或新标签。
        public static final String _TOP = "_top"; // 不包含框架的当前窗口或标签。
        public static final String _NONE = "_none"; // 同一窗口或标签。

        public static final Map<String, String> getTargetMap() {
            Map<String, String> map = new HashMap<String, String>();
            map.put(_BLANK, "新窗口或新标签");
            map.put(_TOP, "不包含框架的当前窗口或标签");
            map.put(_NONE, "同一窗口或标签");
            return map;
        }
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
