/**
 * 
 */
package org.mspring.mlog.event.ping;

import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.entity.Article;
import org.mspring.platform.utils.StringUtils;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Gao Youbo
 * @since May 7, 2012
 */
public class PingEventListenerImpl implements PingEventListener {
    
    private static final Logger log = Logger.getLogger(PingEventListenerImpl.class);

    /* (non-Javadoc)
     * @see org.mspring.mlog.event.ping.PingEventListener#ping(org.mspring.mlog.event.ping.PingEvent)
     */
    @Override
    @Async
    public void ping(PingEvent event) {
        // TODO Auto-generated method stub
        log.debug("Execute Event PingEventListenerImpl.ping, Source=" + event.getSource());
        try {
            Article article = (Article) event.getSource();
            String title = article.getTitle();
            String url = ServiceFactory.getOptionService().getOption(ConfigTokens.mspring_config_base_blogurl);
            String blogTitle = ServiceFactory.getOptionService().getOption(ConfigTokens.mspring_config_base_blogname);
            
            // if is localhost blog
            if ("localhost".equals(url.split(":")[0].trim())) {
                log.info("Blog runs on local server, so should not ping Blog Search Service");
                return;
            }
            url = StringUtils.removeEnd("http://" + url, "/");
            
            String articleUrl = url + article.getId() + ".html";
            
            String spec = "http://blogsearch.google.com/ping?name=" + URLEncoder.encode(blogTitle, "UTF-8") + "&url=" + URLEncoder.encode(url, "UTF-8") + "&changesURL=" + URLEncoder.encode(articleUrl, "UTF-8");;
            
            log.debug("Request Google Blog Search Service API[" + spec + "] while adding an article[title=" + title + "]");
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }

}
