/**
 * 
 */
package org.mspring.platform.task;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public abstract class AbstractTask implements Task {
    protected PlatformTransactionManager transactionManager;

    @Autowired
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Async
    public void doAsyncTask(final Map<Object, Object> context) {
        new TransactionTemplate(this.transactionManager).execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    AbstractTask.this.doTask(context);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
