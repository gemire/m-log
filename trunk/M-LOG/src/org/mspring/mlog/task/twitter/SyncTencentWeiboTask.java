/**
 * 
 */
package org.mspring.mlog.task.twitter;

import java.util.Map;

import org.mspring.mlog.api.weibo.tencent.service.TencentWeiboService;
import org.mspring.mlog.entity.Twitter;
import org.mspring.mlog.service.TwitterService;
import org.mspring.platform.task.AbstractTask;
import org.mspring.platform.web.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since 2013-5-31
 * @description
 * @TODO
 */
@Component
public class SyncTencentWeiboTask extends AbstractTask {

    @Autowired
    private TencentWeiboService tencentWeiboService;
    @Autowired
    private TwitterService twitterService;

    @Override
    protected void doTask(Map<Object, Object> paramMap) throws Exception {
        // TODO Auto-generated method stub
        Object obj = paramMap.get("twitterId");
        if (obj == null) {
            return;
        }
        Long twitterId = new Long(obj.toString());
        Twitter t = twitterService.getTwitterById(twitterId);
        if (t == null) {
            return;
        }
        String weiboId = null;
        try {
            ResponseEntity twbRsp = null;
            // 同步腾讯微博
            if (t.getImage() != null) {
                twbRsp = tencentWeiboService.postWeibo(t.getAuthor().getId(), t.getContent(), t.getIp(), t.getImage().getPath());
            }
            else {
                twbRsp = tencentWeiboService.postWeibo(t.getAuthor().getId(), t.getContent(), t.getIp());
            }
            if (twbRsp.getSuccess()) {
                weiboId = twbRsp.getData().get("id").toString();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        if (weiboId != null) {
            twitterService.setTencentWeiboId(twitterId, weiboId);
        }
    }

}
