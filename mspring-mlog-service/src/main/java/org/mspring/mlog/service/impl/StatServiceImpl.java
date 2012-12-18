/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.entity.Stat;
import org.mspring.mlog.service.StatService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.ValidatorUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-8-14
 * @Description
 * @TODO
 */
@Service
@Transactional
public class StatServiceImpl extends AbstractServiceSupport implements StatService {

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.StatService#getPostCount()
     */
    @Override
    public String getPostCount() {
        // TODO Auto-generated method stub
        Stat stat = getSingle(Stat.Type.POST_COUNT);
        if (stat != null && StringUtils.isNotBlank(stat.getValue())) {
            return stat.getValue();
        }
        return "0";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.StatService#getCommentCount()
     */
    @Override
    public String getCommentCount() {
        // TODO Auto-generated method stub
        Stat stat = getSingle(Stat.Type.COMMENT_COUNT);
        if (stat != null && StringUtils.isNotBlank(stat.getValue())) {
            return stat.getValue();
        }
        return "0";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.StatService#getClickCount()
     */
    @Override
    public String getClickCount() {
        // TODO Auto-generated method stub
        Stat stat = getSingle(Stat.Type.CLICK_COUNT);
        if (stat != null && StringUtils.isNotBlank(stat.getValue())) {
            return stat.getValue();
        }
        return "0";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.StatService#updatePostCount()
     */
    @Override
    public void updatePostCount() {
        // TODO Auto-generated method stub
        Object obj = findUnique("select count(*) from Post post where post.status = ?", Post.Status.PUBLISH);
        if (obj != null && ValidatorUtils.isNumber(obj.toString())) {
            Stat stat = new Stat(Stat.Type.POST_COUNT, obj.toString());
            create(stat);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.StatService#updateCommentCount()
     */
    @Override
    public void updateCommentCount() {
        // TODO Auto-generated method stub
        Object obj = findUnique("select count(*) from Comment comment where comment.status = ?", Comment.Status.APPROVED);
        if (obj != null && ValidatorUtils.isNumber(obj.toString())) {
            Stat stat = new Stat(Stat.Type.COMMENT_COUNT, obj.toString());
            create(stat);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.StatService#updateClickCount()
     */
    @Override
    public void updateClickCount() {
        // TODO Auto-generated method stub
        String click = getClickCount();
        if (StringUtils.isBlank(click)) {
            click = "0";
        }
        Integer value = Integer.parseInt(click) + 1;
        Stat stat = new Stat(Stat.Type.CLICK_COUNT, value.toString());
        create(stat);
    }

    private Stat getSingle(final String type) {
        return this.getHibernateTemplate().execute(new HibernateCallback<Stat>() {
            @Override
            public Stat doInHibernate(Session session) throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                Query query = session.createQuery("select s from Stat s where s.type = ? order by id desc");
                query.setParameter(0, type);
                query.setMaxResults(1);
                List<Stat> list = query.list();
                if (list != null && list.size() > 0) {
                    return list.get(0);
                }
                return null;
            }
        });
    }

}
