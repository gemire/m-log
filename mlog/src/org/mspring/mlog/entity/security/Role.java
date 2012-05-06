package org.mspring.mlog.entity.security;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Role entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roles")
public class Role implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1531136386638326716L;
    private Integer roleId;
    private String roleName;
    private String roleAilas;
    private String roleDesc;
    private boolean enabled;
    private boolean isSys;

    // Constructors

    /** default constructor */
    public Role() {
    }

    /** full constructor */
    public Role(String roleName, String roleDesc, boolean enabled, boolean isSys) {
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.enabled = enabled;
        this.isSys = isSys;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "role_id", unique = true, nullable = false, length = 30)
    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Column(name = "role_name", length = 100)
    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(name = "role_alias", length = 100)
    public String getRoleAilas() {
        return roleAilas;
    }

    public void setRoleAilas(String roleAilas) {
        this.roleAilas = roleAilas;
    }

    @Column(name = "role_desc", length = 100)
    public String getRoleDesc() {
        return this.roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Column(name = "enabled")
    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "is_sys")
    public boolean isSys() {
        return isSys;
    }

    public void setSys(boolean isSys) {
        this.isSys = isSys;
    }

}