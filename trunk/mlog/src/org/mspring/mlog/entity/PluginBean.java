package org.mspring.mlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Plugin entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "plugin")
public class PluginBean implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = -8382105252827605706L;

    private Long id;
    private String name;
    private String author;
    private String version;
    private String email;
    private String homePage;
    private String description;
    private String pluginclass;

    // Constructors

    /** default constructor */
    public PluginBean() {
    }

    /** full constructor */
    public PluginBean(String name, String author, String version, String email, String homePage, String description, String pluginclass) {
        this.name = name;
        this.author = author;
        this.version = version;
        this.email = email;
        this.homePage = homePage;
        this.description = description;
        this.pluginclass = pluginclass;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "author", length = 200)
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "version", length = 100)
    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column(name = "email", length = 200)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "homePage", length = 300)
    public String getHomePage() {
        return this.homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    @Column(name = "description", columnDefinition = "text")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "pluginclass", length = 500)
    public String getPluginclass() {
        return this.pluginclass;
    }

    public void setPluginclass(String pluginclass) {
        this.pluginclass = pluginclass;
    }

}