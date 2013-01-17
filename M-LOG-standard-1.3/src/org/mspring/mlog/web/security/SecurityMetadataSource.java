/**
 * 
 */
package org.mspring.mlog.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mspring.mlog.entity.security.Resource;
import org.mspring.mlog.entity.security.TreeItem;
import org.mspring.mlog.service.security.ResourceService;
import org.mspring.mlog.service.security.TreeItemService;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

/**
 * @author Gao Youbo
 * @since 2013-1-11
 * @Description
 * @TODO
 */
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private TreeItemService treeItemService;

    /**
     * 加载所有资源与权限的关系
     */
    private void loadResourceDefine() {
        if (resourceMap == null) {
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            List<Resource> resources = resourceService.findAllResources();
            for (Resource resource : resources) {
                String name = "RES_" + resource.getId() + "_" + resource.getName();
                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                ConfigAttribute configAttribute = new SecurityConfig(name);
                configAttributes.add(configAttribute);
                resourceMap.put(resource.getUrl(), configAttributes);
            }

//            List<TreeItem> items = treeItemService.findTreeItemResource();
//            for (TreeItem item : items) {
//                if (StringUtils.isBlank(item.getName()) || StringUtils.isBlank(item.getCall())) {
//                    continue;
//                }
//                String name = "ITEM_" + item.getId() + "_" + item.getName();
//                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
//                ConfigAttribute configAttribute = new SecurityConfig(name);
//                configAttributes.add(configAttribute);
//                resourceMap.put(item.getCall(), configAttributes);
//            }
        }
    }

    /**
     * 返回所请求资源所需要的权限
     */
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        loadResourceDefine();

        String url = ((FilterInvocation) object).getRequestUrl();
        int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            url = url.substring(0, firstQuestionMarkIndex);
        }
        HttpServletRequest request = ((FilterInvocation) object).getRequest();

        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            RequestMatcher urlMatcher = new AntPathRequestMatcher(resURL);
            if (urlMatcher.matches(request)) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return true;
    }

}