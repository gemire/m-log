package org.mspring.mlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LinkType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "linktype")
public class LinkType implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3512127351242084265L;

    private Long id;
    private String name;
    private String description;

    // Constructors

    /** default constructor */
    public LinkType() {
    }

    /** full constructor */
    /**
     * @param id
     * @param name
     * @param description
     */
    public LinkType(Long id, String name, String description) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "linktype_id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "linktype_name", length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "link_description", columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}