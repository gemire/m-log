/**
 * 
 */
package org.mspring.mlog.job.command;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.platform.exception.BusinessException;
import org.mspring.platform.job.command.AbstractCommand;

/**
 * @author Gao Youbo
 * @since 2012-8-5
 * @Description
 * @TODO
 */
public class UpdateStatInfoCmd extends AbstractCommand {

    private static final Logger log = Logger.getLogger(UpdateStatInfoCmd.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.job.command.Command#execute()
     */
    @Override
    public void execute() throws BusinessException {
        // TODO Auto-generated method stub
        log.debug("begin update post count...");
        ServiceFactory.getStatService().updatePostCount();
        log.debug("begin update comment count...");
        ServiceFactory.getStatService().updateCommentCount();
    }

}
