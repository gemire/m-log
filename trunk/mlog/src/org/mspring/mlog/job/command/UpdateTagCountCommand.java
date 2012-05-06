/**
 * 
 */
package org.mspring.mlog.job.command;

import org.apache.log4j.Logger;
import org.mspring.mlog.common.ServiceFactory;
import org.mspring.platform.exception.BusinessException;
import org.mspring.platform.job.command.AbstractCommand;

/**
 * 更新TAG使用量
 * @author Gao Youbo
 * @since 2012-5-6
 */
public class UpdateTagCountCommand extends AbstractCommand {
    
    private static final Logger log = Logger.getLogger(UpdateTagCountCommand.class);

    /* (non-Javadoc)
     * @see org.mspring.platform.job.command.Command#execute()
     */
    @Override
    public void execute() throws BusinessException {
        // TODO Auto-generated method stub
        log.debug("############################################ execute command UpdateTagCountCommand");
        try {
            ServiceFactory.getTagService().updateTagCount();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.warn("############################################ execute command UpdateTagCountCommand failure", e);
        }
    }

}
