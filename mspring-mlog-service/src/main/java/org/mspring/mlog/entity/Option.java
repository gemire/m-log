/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
@Entity
@Table(name = "options")
public class Option implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1165305903938660552L;
    private String name;
    private String value;

    /**
     * 
     */
    public Option() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param name
     * @param value
     */
    public Option(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    /**
     * @return the name
     */
    @Id
    @Column(name = "name", length = 100)
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
     * @return the value
     */
    @Column(name = "value", length = 3000)
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

}
