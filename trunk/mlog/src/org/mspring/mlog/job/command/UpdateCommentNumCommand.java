/**
 * 
 */
package org.mspring.mlog.job.command;

import org.apache.log4j.Logger;
import org.mspring.mlog.common.ServiceFactory;
import org.mspring.platform.exception.BusinessException;
import org.mspring.platform.job.command.AbstractCommand;

/**
 * 更新文章评论数量
 * 
 * @author Gao Youbo
 * @since May 5, 2012
 * 
 */
public class UpdateCommentNumCommand extends AbstractCommand {

    private static final Logger log = Logger.getLogger(UpdateCommentNumCommand.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.job.command.Command#execute()
     */
    @Override
    public void execute() throws BusinessException {
        // TODO Auto-generated method stub
        log.debug("############################################ execute command UpdateCommentNumCommand");
        try {
            ServiceFactory.getArticleService().updateCommentNum();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.warn("############################################ execute command UpdateCommentNumCommand failure", e);
        }
    }

}
