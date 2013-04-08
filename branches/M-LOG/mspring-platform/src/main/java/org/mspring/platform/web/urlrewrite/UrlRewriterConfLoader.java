/**
 * 
 */
package org.mspring.platform.web.urlrewrite;

import org.tuckey.web.filters.urlrewrite.Conf;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public abstract interface UrlRewriterConfLoader {
    public abstract void loadUrlRewriterConf(Conf paramConf);
}
