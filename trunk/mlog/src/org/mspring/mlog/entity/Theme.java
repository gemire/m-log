/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;

/**
 * @author Gao Youbo
 * @since Feb 8, 2012
 */
public class Theme implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3084103499390300869L;
    private String name;
    private String version;
    private String blogversion;
    private String description;

    private String folderName;

    /**
     * 
     */
    public Theme() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     */
    public Theme(String name, String version, String blogversion, String description) {
        // TODO Auto-generated constructor stub
        this.name = name;
        this.version = version;
        this.blogversion = blogversion;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBlogversion() {
        return blogversion;
    }

    public void setBlogversion(String blogversion) {
        this.blogversion = blogversion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
