/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gao Youbo
 * @since 2012-12-5
 * @Description
 * @TODO
 */
public class WidgetConfig implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7165505794423169776L;

    private String name;
    private String path;
    private boolean cache = true;
    private long idle;

    private String title;
    private String description;

    private Map preferences = new HashMap();

    /**
     * @return the name
     */
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
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     *            the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the cache
     */
    public boolean isCache() {
        return cache;
    }

    /**
     * @param cache
     *            the cache to set
     */
    public void setCache(boolean cache) {
        this.cache = cache;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
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
     * @return the preferences
     */
    public Map getPreferences() {
        return preferences;
    }

    /**
     * @param preferences
     *            the preferences to set
     */
    public void setPreferences(Map preferences) {
        this.preferences = preferences;
    }

    /**
     * @return the idle
     */
    public long getIdle() {
        return idle;
    }

    /**
     * @param idle
     *            the idle to set
     */
    public void setIdle(long idle) {
        this.idle = idle;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("name:").append(name);
        sb.append(" | ");
        sb.append("path:").append(path);
        sb.append(" | ");
        sb.append("cache:").append(cache);
        sb.append(" | ");
        sb.append("idle:").append(idle);
        sb.append("}");
        //
        return sb.toString();
    }

}
