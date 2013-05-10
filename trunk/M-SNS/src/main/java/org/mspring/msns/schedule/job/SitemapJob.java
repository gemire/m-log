/**
 * 
 */
package org.mspring.msns.schedule.job;

import java.io.File;
import java.util.List;

import org.mspring.msns.core.ServiceFactory;
import org.mspring.msns.entity.Post;
import org.mspring.msns.service.OptionService;
import org.mspring.msns.service.PostService;
import org.mspring.msns.utils.PostUrlUtils;
import org.mspring.msns.utils.WebUtils;
import org.mspring.platform.api.sitemap.ChangeFreq;
import org.mspring.platform.api.sitemap.GoogleCodeSitemapGenerator;
import org.mspring.platform.api.sitemap.GoogleCodeSitemapUrl;
import org.mspring.platform.api.sitemap.GoogleMobileSitemapGenerator;
import org.mspring.platform.api.sitemap.GoogleMobileSitemapUrl;
import org.mspring.platform.api.sitemap.WebSitemapGenerator;
import org.mspring.platform.api.sitemap.WebSitemapUrl;
import org.mspring.platform.api.sitemap.GoogleCodeSitemapUrl.FileType;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Service;

/**
 * @author Gao Youbo
 * @since 2013-1-25
 * @description
 * @TODO
 */
@Service
public class SitemapJob extends BaseJob {

    private OptionService optionService = ServiceFactory.getOptionService();
    private PostService postService = ServiceFactory.getPostService();

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.msns.schedule.job.AbstractJob#nativeExecuteInternal(org.quartz
     * .JobExecutionContext)
     */
    @Override
    public void nativeExecuteInternal(JobExecutionContext context) {
        // TODO Auto-generated method stub
        try {
            String baseUrl = optionService.getOption("siteurl");
            String baseDirPath = WebUtils.getRealContextPath();
            File baseDir = new File(baseDirPath);

            WebSitemapGenerator generator = WebSitemapGenerator.builder(baseUrl, baseDir).fileNamePrefix("sitemap").build();
            GoogleCodeSitemapGenerator googleGenerator = GoogleCodeSitemapGenerator.builder(baseUrl, baseDir).fileNamePrefix("sitemap_google").build();
            GoogleMobileSitemapGenerator googleMobileGenerator = GoogleMobileSitemapGenerator.builder(baseUrl, baseDir).fileNamePrefix("sitemap_google_mobile").build();

            List<Post> posts = postService.findAll();
            for (Post post : posts) {
                String loc = baseUrl + PostUrlUtils.getPostUrl(post);
                double priority = 0.9;
                if (post.getIsTop()) {
                    priority = 1.0;
                }
                WebSitemapUrl url = new WebSitemapUrl.Options(loc).lastMod(post.getModifyTime()).priority(priority).changeFreq(ChangeFreq.DAILY).build();
                generator.addUrl(url);

                // google
                GoogleCodeSitemapUrl googleUrl = new GoogleCodeSitemapUrl.Options(loc, FileType.HTML).lastMod(post.getModifyTime()).priority(priority).changeFreq(ChangeFreq.DAILY).build();
                googleGenerator.addUrl(googleUrl);

                // google mobile
                GoogleMobileSitemapUrl googleMobileSitemapUrl = new GoogleMobileSitemapUrl.Options(loc).lastMod(post.getModifyTime()).priority(priority).changeFreq(ChangeFreq.DAILY).build();
                googleMobileGenerator.addUrl(googleMobileSitemapUrl);
            }
            generator.write();
            googleGenerator.write();
            googleMobileGenerator.write();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
