/**
 * 
 */
package org.mspring.mlog.schedule.job;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.mspring.mlog.api.sitemap.ChangeFreq;
import org.mspring.mlog.api.sitemap.WebSitemapGenerator;
import org.mspring.mlog.api.sitemap.WebSitemapUrl;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Job;
import org.mspring.mlog.entity.JobLog;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.utils.WebUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Gao Youbo
 * @since 2013-1-25
 * @description
 * @TODO
 */
public class SitemapJob extends AbstractJob {

    public static final Long JOB_ID = new Long(2);
    public static final String JOB_NAME = "UpdateStatInfoJob";

    private OptionService optionService = ServiceFactory.getOptionService();
    private PostService postService = ServiceFactory.getPostService();

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org
     * .quartz.JobExecutionContext)
     */
    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
        // TODO Auto-generated method stub
        JobLog jobLog = new JobLog();
        jobLog.setTime(new Date());
        long start = System.currentTimeMillis();
        boolean success = true;
        try {
            String baseUrl = optionService.getOption("blogurl");
            String baseDirPath = WebUtils.getRealContextPath();
            File baseDir = new File(baseDirPath);
            WebSitemapGenerator generator = new WebSitemapGenerator(baseUrl, baseDir);
            List<Post> posts = postService.findAll();
            for (Post post : posts) {
                String loc = baseUrl + post.getUrl();
                double priority = 0.9;
                if (post.getIsTop()) {
                    priority = 1.0;
                }
                WebSitemapUrl url = new WebSitemapUrl.Options(loc).lastMod(post.getModifyTime()).priority(priority).changeFreq(ChangeFreq.DAILY).build();
                generator.addUrl(url);
            }
            generator.write();
        } catch (Exception e) {
            // TODO: handle exception
            success = false;
            jobLog.setMessage(e.getMessage());
        }
        long end = System.currentTimeMillis();
        jobLog.setUseTime(end - start);
        jobLog.setJob(new Job(JOB_ID));
        jobLog.setName(JOB_NAME);
        jobLog.setSuccess(success);
        ServiceFactory.getJobLogService().createJobLog(jobLog);
    }

}
