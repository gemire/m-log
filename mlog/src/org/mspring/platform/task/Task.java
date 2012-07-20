/**
 * 
 */
package org.mspring.platform.task;

import java.util.Map;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public abstract interface Task {
    public abstract void doSyncTask(Map<Object, Object> paramMap);

    public abstract void doAsyncTask(Map<Object, Object> paramMap);
}
