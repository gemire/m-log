/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.mlog.api.weibo.tencent.service.TencentWeiboService;
import org.mspring.mlog.entity.Twitter;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.TwitterService;
import org.mspring.mlog.web.query.TwitterQueryCriterion;
import org.mspring.mlog.web.security.SecurityUtils;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.utils.RequestUtils;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.ResponseEntity;
import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Gao Youbo
 * @since 2013-5-24
 * @description
 * @TODO
 */
@Widget
@RequestMapping("/t")
public class TwitterWidget extends AbstractWebWidget {

    private static final Logger log = Logger.getLogger(TwitterWidget.class);

    @Autowired
    private TwitterService jawService;
    @Autowired
    private TencentWeiboService tencentWeiboService;

    @RequestMapping({ "/", "" })
    public String show(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "skin:/twitter";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseEntity add(@RequestParam(required = false) String content, HttpServletRequest request, HttpServletResponse response, Model model) {
        ResponseEntity rsp = new ResponseEntity();
        User user = SecurityUtils.getCurrentUser(request);
        if (user == null) {
            rsp.setSuccess(false);
            rsp.setMessage("请先登录");
            return rsp;
        }
        if (StringUtils.isBlank(content)) {
            rsp.setSuccess(false);
            rsp.setMessage("请输入要发表的内容");
            return rsp;
        }

        try {
            String weiboId = null;

            try {
                // 同步腾讯微博
                ResponseEntity twbRsp = tencentWeiboService.postWeibo(user.getId(), content, RequestUtils.getRemoteIP(request));
                if (twbRsp.getSuccess()) {
                    weiboId = twbRsp.getData().get("id").toString();
                }
            } catch (Exception e) {
                // TODO: handle exception
                log.error(e.getMessage());
            }

            Twitter twitter = new Twitter();
            twitter.setAuthor(user);
            twitter.setContent(content);
            twitter.setTencentWeiboId(weiboId);

            Long id = jawService.createTwitter(twitter);
            twitter = jawService.getTwitterById(id);

            rsp.setSuccess(true);
            rsp.setMessage("发表成功");
            rsp.put("twitter", new Twitter[] { twitter });

            return rsp;
        } catch (Exception e) {
            // TODO: handle exception
            rsp.setSuccess(false);
            rsp.setMessage("发表内容失败");
            return rsp;
        }
    }

    @RequestMapping("/get")
    @ResponseBody
    public ResponseEntity get(@RequestParam(required = false) Integer page, HttpServletRequest request, HttpServletResponse response, Model model) {
        ResponseEntity rsp = new ResponseEntity();
        
        if (page == null || page < 1) {
            page = 1;
        }

        Page<Twitter> p = new Page<Twitter>();
        p.setPageNo(page);
        p.setPageSize(12);
        p.setSort(new Sort("id", Sort.DESC));

        jawService.findTwitterPage(new TwitterQueryCriterion(null), p);
        rsp.setSuccess(true);
        rsp.put("twitter", p.getResult());
        return rsp;
    }
}
