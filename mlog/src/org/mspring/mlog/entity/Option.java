package org.mspring.mlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Option entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "option")
public class Option implements java.io.Serializable {

    private static final long serialVersionUID = -7480506367669672086L;

    private String key;
    private String value;

    // Constructors

    /** default constructor */
    public Option() {
    }

    /**
     * @param optionKey
     * @param optionValue
     */
    public Option(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    /** full constructor */
    public Option(String value) {
        this.value = value;
    }

    // Property accessors
    @Id
    @Column(name = "option_key", unique = true, nullable = false, length = 100)
    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Column(name = "option_value", length = 500)
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}