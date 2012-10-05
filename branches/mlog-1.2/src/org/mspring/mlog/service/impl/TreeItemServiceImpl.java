/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.List;

import org.mspring.mlog.entity.TreeItem;
import org.mspring.mlog.service.TreeItemService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-7-16
 * @Description
 * @TODO
 */
@Service
@Transactional
public class TreeItemServiceImpl extends AbstractServiceSupport implements TreeItemService {

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TreeItemService#findAllItems()
     */
    @Override
    public List<TreeItem> findAllItems() {
        // TODO Auto-generated method stub
        String queryString = "select t from TreeItem t where t.deleted = false order by t.id";
        return find(queryString);
    }

}
