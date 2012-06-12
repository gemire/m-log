/**
 * 
 */
package org.mspring.mlog.dao.impl;

import org.mspring.mlog.dao.PluginBeanDao;
import org.mspring.mlog.entity.PluginBean;
import org.mspring.platform.persistence.hibernate.dao.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class PluginBeanDaoImpl extends AbstractHibernateDao<PluginBean> implements PluginBeanDao {

    /**
     * @param entityClass
     */
    protected PluginBeanDaoImpl() {
        super(PluginBean.class);
        // TODO Auto-generated constructor stub
    }

}
