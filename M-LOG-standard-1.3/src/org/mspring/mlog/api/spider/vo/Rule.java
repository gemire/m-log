/**
 * 
 */
package org.mspring.mlog.api.spider.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Gao Youbo
 * @since 2013-3-8
 * @description
 * @TODO
 */
// @Entity
// @Table(name = "spider_rule")
public class Rule implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 553314127806022119L;

    private Long id;
    private String name;
    private String description;
    private String url;
    private String listRule;
    private String titleRule;
    private String contentRule;
    private Boolean enabled;

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id", unique = true, nullable = false, length = 30)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // @Column(name = "description", length = 800)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // @Column(name = "url", nullable = false, length = 200)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // @Column(name = "list_rule", nullable = false, length = 200)
    public String getListRule() {
        return listRule;
    }

    public void setListRule(String listRule) {
        this.listRule = listRule;
    }

    // @Column(name = "title_rule", nullable = false, length = 200)
    public String getTitleRule() {
        return titleRule;
    }

    public void setTitleRule(String titleRule) {
        this.titleRule = titleRule;
    }

    // @Column(name = "content_rule", nullable = false, length = 200)
    public String getContentRule() {
        return contentRule;
    }

    public void setContentRule(String contentRule) {
        this.contentRule = contentRule;
    }

    // @Column(name = "enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
