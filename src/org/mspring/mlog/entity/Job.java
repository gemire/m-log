/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Gao Youbo
 * @since 2013-1-8
 * @Description
 * @TODO
 */
@Entity
@Table(name = "job")
public class Job implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1493057421372870898L;

    private Long id;
    private String name;
    private String execType = ExecType.SIMPLE;
    private String expression;
    private Date lastExec;
    private Boolean enabled = true;
    private String jobClass;
    
    /**
     * 
     */
    public Job() {
        // TODO Auto-generated constructor stub
    }
    
    

    public Job(Long id) {
        super();
        this.id = id;
    }



    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 30)
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
     * @return the name
     */
    @Column(name = "name", unique = true, nullable = false, length = 200)
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
     * @return the execType
     */
    @Column(name = "exec_type", nullable = false, length = 20)
    public String getExecType() {
        return execType;
    }

    /**
     * @param execType
     *            the execType to set
     */
    public void setExecType(String execType) {
        this.execType = execType;
    }

    /**
     * @return the expression
     */
    @Column(name = "expression", nullable = false, length = 30)
    public String getExpression() {
        return expression;
    }

    /**
     * @param expression
     *            the expression to set
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * @return the lastExec
     */
    @Column(name = "last_exec", length = 100)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastExec() {
        return lastExec;
    }

    /**
     * @param lastExec
     *            the lastExec to set
     */
    public void setLastExec(Date lastExec) {
        this.lastExec = lastExec;
    }

    /**
     * @return the enabled
     */
    @Column(name = "enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the jobClass
     */
    @Column(name = "job_class", length = 200)
    public String getJobClass() {
        return jobClass;
    }

    /**
     * @param jobClass
     *            the jobClass to set
     */
    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public static class ExecType {
        public static final String SIMPLE = "simple";
        public static final String CRON = "cron";

        public static final Map<String, String> getExecTypeMap() {
            Map<String, String> map = new HashMap<String, String>();
            map.put(SIMPLE, "时间间隔");
            map.put(CRON, "Cron表达式");
            return map;
        }
    }

}
