/**
 * 
 */
package org.mspring.mlog.api.t.service;

import java.util.List;

import net.sf.ezmorph.bean.MorphDynaBean;

/**
 * @author Gao Youbo
 * @since 2012-8-29
 * @Description
 * @TODO
 */
public interface TService {
    public String add(String content);

    public List<MorphDynaBean> list();
}
