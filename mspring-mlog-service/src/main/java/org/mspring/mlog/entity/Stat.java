/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.mspring.platform.utils.DateUtils;

/**
 * @author Gao Youbo
 * @since 2012-8-14
 * @Description
 * @TODO 统计实体类
 */
@Entity
@Table(name = "stat")
public class Stat implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4690591462487929669L;

    private Long id;
    private String type;
    private String value;
    private Date date;
    private Date timezone;

    /**
     * 
     */
    public Stat() {
        this.date = DateUtils.parse(DateUtils.format(DateUtils.YYYY_MM_DD), DateUtils.YYYY_MM_DD);
        this.timezone = new Date();
    }

    /**
     * @param value
     */
    public Stat(String value) {
        this();
        this.value = value;
    }

    /**
     * @param type
     * @param value
     */
    public Stat(String type, String value) {
        this();
        this.type = type;
        this.value = value;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 30, unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    @Column(name = "type", length = 30, unique = false, nullable = false)
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the value
     */
    @Column(name = "value", length = 30, unique = false)
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the date
     */
    @Column(name = "date", length = 30, nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the timezone
     */
    @Column(name = "timezone", length = 30, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTimezone() {
        return timezone;
    }

    /**
     * @param timezone
     *            the timezone to set
     */
    public void setTimezone(Date timezone) {
        this.timezone = timezone;
    }

    public static final class Type {
        public static final String POST_COUNT = "post_count";
        public static final String COMMENT_COUNT = "comment_count";
        public static final String CLICK_COUNT = "click_count";
        public static final String USER_AGENT = "user_agent";
        public static final String USER_IP = "user_ip";
    }

}
