/**
 * 
 */
package org.mspring.mlog.dao.impl;

import org.mspring.mlog.dao.OptionDao;
import org.mspring.mlog.entity.Option;
import org.mspring.platform.dao.support.AbstractHibernateDao
;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class OptionDaoImpl extends AbstractHibernateDao<Option> implements OptionDao {

    /**
     * @param entityClass
     */
    protected OptionDaoImpl() {
        super(Option.class);
        // TODO Auto-generated constructor stub
    }

}
