package org.mspring.mlog.entity.security;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * User entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "user_name"))
public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5712877859864616079L;

    private Integer id;
    private Integer level;
    private String name;
    private String password;
    private Integer gender;
    private String email;
    private String msn;
    private String qq;
    private String homepage;
    private Date lastvisit;
    private Integer status;
    private String intro;
    private String ip;

    // Constructors

    /**
     * @param id
     * @param level
     * @param nme
     * @param password
     * @param gender
     * @param email
     * @param msn
     * @param qq
     * @param homepage
     * @param lastvisit
     * @param status
     * @param intro
     * @param ip
     */
    public User(Integer id, Integer level, String name, String password, Integer gender, String email, String msn, String qq, String homepage, Date lastvisit, Integer status, String intro, String ip) {
        super();
        this.id = id;
        this.level = level;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.msn = msn;
        this.qq = qq;
        this.homepage = homepage;
        this.lastvisit = lastvisit;
        this.status = status;
        this.intro = intro;
        this.ip = ip;
    }

    /** default constructor */
    public User() {
    }

    /** full constructor */

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "user_level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Column(name = "user_name", unique = true, length = 20)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "user_password", length = 32)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "user_gender")
    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Column(name = "user_email", length = 50)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "user_homepage")
    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @Column(name = "user_msn", length = 50)
    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    @Column(name = "user_qq", length = 50)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_lastvisit", length = 19)
    public Date getLastvisit() {
        return lastvisit;
    }

    public void setLastvisit(Date lastvisit) {
        this.lastvisit = lastvisit;
    }

    @Column(name = "user_status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "user_intro", columnDefinition = "text")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Column(name = "user_ip", length = 15)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}