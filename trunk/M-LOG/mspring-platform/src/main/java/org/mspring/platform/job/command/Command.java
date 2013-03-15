/**
 * 
 */
package org.mspring.platform.job.command;

import org.mspring.platform.exception.BusinessException;

/**
 * @author Gao Youbo
 * @since May 5, 2012
 */
public interface Command {
    public abstract void execute() throws BusinessException;
}
