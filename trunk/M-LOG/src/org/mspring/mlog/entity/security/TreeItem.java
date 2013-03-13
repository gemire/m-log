/**
 * 
 */
package org.mspring.mlog.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
    private String id; // 编号
    private String name; // 名字
    private String parent; // 父节点
    private Long order; // 排序编号，已废弃
    private String call; // 链接地址
    private String type; // 类型
    private Boolean deleted; // 是否删除
    private String icon; // 图标
    private Boolean open; // 是否打开
    private String target; // target

    private Boolean checked; // 是否选中

    /**
     * 
     */
    public TreeItem() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param id
     */
    public TreeItem(String id) {
        super();
        this.id = id;
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
    public TreeItem(String id, String name, String parent, String call, String type, Boolean open) {
        super();
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.call = call;
        this.type = type;
        this.open = open;

        this.deleted = false;
        this.target = "main";
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

    @Column(name = "type", nullable = false, length = 20)
    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    @Transient
    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public static final class Type {
        public static String TREE_FOLDER = "tree_folder"; // 树形菜单文件夹
        public static String TREE_ITEM = "tree_item"; // 树形菜单节点
        public static String TAB = "tab"; // tab
    }
}
