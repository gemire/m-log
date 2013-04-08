/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.mspring.mlog.entity.security.User;

/**
 * @author Gao Youbo
 * @since 2013-3-11
 * @description
 * @TODO
 */
@Entity
@Table(name = "log")
public class Log implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1898948906446301779L;

    private Long id;
    private Date actionTime;
    private User user;
    private String action;
    private String description;
    private String ip;
    private String agent;
    private String className;
    private String methodName;
    private String arguments;

    /**
     * 
     */
    public Log() {
        // TODO Auto-generated constructor stub
    }

    
    public Log(Long id) {
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

    @Column(name = "action_time", length = 100)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = User.class)
    @JoinColumn(name = "user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "action", columnDefinition = "text")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Column(name = "description", columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "ip", length = 20)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "agent", length = 200)
    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    @Column(name = "class_name", length = 300)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Column(name = "method_name", length = 300)
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Column(name = "arguments", columnDefinition = "text")
    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

}
