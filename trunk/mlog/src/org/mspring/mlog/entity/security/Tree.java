package org.mspring.mlog.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tree entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tree")
public class Tree implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 8139654305132883133L;

    private String id;
    private String text;
    private Boolean leaf;
    private Boolean expanded;
    private String url;
    private String qtip;
    private String parent;
    private Boolean deleted;
    private Integer type;
    private String icon;

    // Constructors

    /** default constructor */
    public Tree() {
    }

    /** full constructor */
    public Tree(String text, Boolean leaf, Boolean expanded, String url, String qtip, String parent, Boolean deleted, Integer type, String icon) {
        this.text = text;
        this.leaf = leaf;
        this.expanded = expanded;
        this.url = url;
        this.qtip = qtip;
        this.parent = parent;
        this.deleted = deleted;
        this.type = type;
        this.icon = icon;
    }

    // Property accessors
    @Id
    // @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 100)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "text", length = 200)
    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "leaf")
    public Boolean getLeaf() {
        return this.leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    @Column(name = "expanded")
    public Boolean getExpanded() {
        return this.expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    @Column(name = "url", length = 200)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "qtip", length = 200)
    public String getQtip() {
        return this.qtip;
    }

    public void setQtip(String qtip) {
        this.qtip = qtip;
    }

    @Column(name = "parent", length = 100)
    public String getParent() {
        return this.parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Column(name = "deleted")
    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Column(name = "type")
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "icon", length = 200)
    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}