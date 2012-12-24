/**
 * 
 */
package org.mspring.mlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author Gao Youbo
 * @since 2012-7-16
 * @Description
 * @TODO
 */
@Entity
@Table(name = "tree_item")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class TreeItem {
    private String id;
    private String name;
    private String parent;
    private Long order;
    private String call;
    private Long type;
    private Boolean deleted;
    private String icon;
    private Boolean open;
    private String target;

    /**
     * 
     */
    public TreeItem() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param id
     * @param name
     * @param parent
     * @param call
     * @param deleted
     * @param open
     * @param target
     */
    public TreeItem(String id, String name, String parent, String call, Boolean deleted, Boolean open, String target) {
        super();
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.call = call;
        this.deleted = deleted;
        this.open = open;
        this.target = target;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false, length = 100)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name", length = 200, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "parent", length = 200)
    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Column(name = "order", length = 200)
    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    @Column(name = "call", length = 500)
    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    @Column(name = "type", length = 2)
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Column(name = "deleted", length = 1)
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Column(name = "icon", length = 100)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "open", length = 1)
    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    @Column(name = "target", length = 100)
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
