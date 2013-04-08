/**
 * 
 */
package org.mspring.mlog.entity.security;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Gao Youbo
 * @since 2013-1-11
 * @Description
 * @TODO
 */
@Entity
@Table(name = "resource")
public class Resource implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5857565819297023911L;
    private Long id;
    private String name;
    private String url;
    private String type;

    /**
     * 
     */
    public Resource() {
        // TODO Auto-generated constructor stub
    }

    public Resource(Long id) {
        super();
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 30)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", length = 300)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "url", length = 300)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "type", length = 30)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class Type {
        public static final String TREE = "tree";
        public static final String NORMAL = "normal";
        public static final List<String> Types = Arrays.asList(new String[] { TREE, NORMAL });

        public static final Map<String, String> getCommentStatusMap() {
            Map<String, String> map = new HashMap<String, String>();
            map.put(TREE, TREE);
            map.put(NORMAL, NORMAL);
            return map;
        }
    }

}
