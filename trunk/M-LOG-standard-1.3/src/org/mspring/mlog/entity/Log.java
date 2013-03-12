/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;
import java.util.Date;

import org.mspring.mlog.entity.security.User;

/**
 * @author Gao Youbo
 * @since 2013-3-11
 * @description
 * @TODO
 */
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

}
