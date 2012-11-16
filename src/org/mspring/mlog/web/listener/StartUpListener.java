/**
 * 
 */
package org.mspring.mlog.web.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.PrefixImprovedNamingStrategy;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Gao Youbo
 * @since 2012-11-15
 * @Description
 * @TODO 系统启动Listener
 */
public class StartUpListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(StartUpListener.class);

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
     * ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
     * .ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        // TODO Auto-generated method stub
        log.debug("install...");
        if (!checkInstalled()) {
            String path = event.getServletContext().getRealPath("/WEB-INF/db");
            try {
                init(path);
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 检测是否已经安装
     * 
     * @return
     */
    private boolean checkInstalled() {
        ImprovedNamingStrategy naming = new PrefixImprovedNamingStrategy();
        String sql = "select count(*) from " + naming.tableName("user");
        int count = ServiceFactory.getJdbcTemplate().queryForInt(sql);
        if (count > 0) {
            return true;
        }
        return false;
    }

    private void init(String path) throws IOException {
        JdbcTemplate jdbcTemplate = ServiceFactory.getJdbcTemplate();
        String filepath = path + "/mlog.sql";
        File sqlFile = new File(filepath);
        if (!sqlFile.exists()) {
            log.warn("check mlog.sql exists");
            return;
        }
        FileReader fr = new FileReader(sqlFile);
        BufferedReader in = new BufferedReader(fr);
        String line;
        while ((line = in.readLine()) != null) {
            try {
                jdbcTemplate.execute(line);
            }
            catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                continue;
            }
        }
        in.close();
        fr.close();
    }

}
