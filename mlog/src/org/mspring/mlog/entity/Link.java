package org.mspring.mlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Link entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "link")
public class Link implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 6642431468319935235L;

    private Long id;
    private String name;
    private String url;
    private String description;
    private String target;
    private String imageurl;
    private boolean display;
    private LinkType type;

    // Constructors

    /** default constructor */
    public Link() {
    }

    /** minimal constructor */
    public Link(String name, LinkType type) {
        this.name = name;
        this.type = type;
    }

    /** full constructor */
    public Link(String name, String url, String description, String target, String imageurl, boolean display, LinkType type) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.target = target;
        this.imageurl = imageurl;
        this.display = display;
        this.type = type;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "link_id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "link_name", nullable = false, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "link_url", length = 500)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "link_description", columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "link_target", length = 200)
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Column(name = "link_imageurl", length = 3000)
    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Column(name = "link_display")
    public boolean getDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = LinkType.class)
    @JoinColumn(name = "link_type")
    public LinkType getType() {
        return type;
    }

    public void setType(LinkType type) {
        this.type = type;
    }

}