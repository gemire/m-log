//package org.mspring.mlog.web.security;
//
//import org.mspring.mlog.entity.security.Resource;
//import org.mspring.mlog.entity.security.Role;
//import org.springframework.security.core.GrantedAuthority;
//
//public class GrantedAuthorityImpl implements GrantedAuthority {
//
//    /**
//     * 
//     */
//    private static final long serialVersionUID = 7791226321080174533L;
//
//    private Role role;
//    private Resource resource;
//
//    public GrantedAuthorityImpl(Role role, Resource resource) {
//        // TODO Auto-generated constructor stub
//        this.role = role;
//        this.resource = resource;
//    }
//
//    @Override
//    public String getAuthority() {
//        // TODO Auto-generated method stub
//        return resource.getName();
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    public Resource getResource() {
//        return resource;
//    }
//
//    public void setResource(Resource resource) {
//        this.resource = resource;
//    }
//    
//    
//    public String toString() {
//        return String.format("[role=%s, resource=%s]", role.getName(), resource.getUrl());
//    }
//
//}
