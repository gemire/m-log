/**
 * 
 */
package org.mspring.platform.task;

import java.util.Map;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public abstract class AbstractTask implements Task {

    // @Async
    public void doAsyncTask(final Map<Object, Object> context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    AbstractTask.this.doTask(context);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
