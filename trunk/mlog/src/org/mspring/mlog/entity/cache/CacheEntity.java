/**
 * 
 */
package org.mspring.mlog.entity.cache;

import java.io.Serializable;
import java.util.Date;

import net.sf.ehcache.Element;

import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.entity.Article;
import org.mspring.mlog.web.cache.CacheTokens;
import org.mspring.platform.utils.DateUtils;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since Apr 16, 2012
 */
public class CacheEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2700940238069523550L;

    private String key;
    private long hitCount;
    private long serializedSize;
    private String creationTime;
    private Article article;

    public static CacheEntity elementToCacheEntity(Element element) {
        if (element != null && null != element.getKey() && !StringUtils.isBlank(element.getKey().toString()) && element.getKey().toString().startsWith(CacheTokens.ARTICLE_CACHE_PREFIX)) {
            CacheEntity entity = new CacheEntity();
            entity.setKey(element.getKey() + "");
            entity.setHitCount(element.getHitCount());
            entity.setSerializedSize(element.getSerializedSize());
            entity.setCreationTime(DateUtils.format(new Date(element.getCreationTime()), DateUtils.YYYY_MM_DD_HH_MM_SS));
            Long articleId = new Long(element.getKey().toString().replaceAll(CacheTokens.ARTICLE_CACHE_PREFIX, ""));
            entity.setArticle(ServiceFactory.getArticleService().findArticleById(articleId));
            return entity;
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getHitCount() {
        return hitCount;
    }

    public void setHitCount(long hitCount) {
        this.hitCount = hitCount;
    }

    public long getSerializedSize() {
        return serializedSize;
    }

    public void setSerializedSize(long serializedSize) {
        this.serializedSize = serializedSize;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
