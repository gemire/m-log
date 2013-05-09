/**
 * 
 */
package org.mspring.platform.task;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public abstract class AbstractTask implements Task {

    public void doAsyncTask(final Map<Object, Object> context) {
        BlockingQueue queue = new LinkedBlockingQueue();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 1, TimeUnit.DAYS, queue);
        executor.execute(new Runnable() {
            public void run() {
                try {
                    AbstractTask.this.doTask(context);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        executor.shutdown();
    }

    public void doSyncTask(Map<Object, Object> context) {
        try {
            doTask(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void doTask(Map<Object, Object> paramMap) throws Exception;
}
