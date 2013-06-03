/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.mspring.mlog.api.weibo.tencent.service.TencentWeiboService;
import org.mspring.mlog.entity.Attachment;
import org.mspring.mlog.entity.Twitter;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.AttachmentService;
import org.mspring.mlog.service.TwitterService;
import org.mspring.mlog.web.query.TwitterQueryCriterion;
import org.mspring.mlog.web.security.SecurityUtils;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.utils.ImageUtils;
import org.mspring.platform.utils.RequestUtils;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.ResponseEntity;
import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    private TwitterService twitterService;
    @Autowired
    private TencentWeiboService tencentWeiboService;
    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping({ "/", "" })
    public String show(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "skin:/twitter";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseEntity add(@RequestParam(required = false) String content, @RequestParam(required = false) Long attachment, HttpServletRequest request, HttpServletResponse response, Model model) {
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
            Twitter twitter = new Twitter();
            twitter.setAuthor(user);
            twitter.setContent(content);
            twitter.setIp(RequestUtils.getRemoteIP(request));
            if (attachment != null) {
                twitter.setImage(new Attachment(attachment));
            }

            Long id = twitterService.createTwitter(twitter);
            twitter = twitterService.getTwitterById(id);

            // 绑定图片和twitter
            if (attachment != null) {
                attachmentService.setAttachmentFid(attachment, Attachment.AttachFrom.FROM_TWITTER, id);
            }

            if (twitter.getImage() != null && twitter.getImage().getId() != null && StringUtils.isBlank(twitter.getImage().getPath())) {
                Attachment image = attachmentService.getAttachmentById(twitter.getImage().getId());
                twitter.setImage(image);
            }

            rsp.setSuccess(true);
            rsp.setMessage("发表成功");
            rsp.put("twitter", new Twitter[] { twitter });
            return rsp;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            rsp.setSuccess(false);
            rsp.setMessage("发表内容失败");
            return rsp;
        }
    }

    /**
     * 获取Twitter
     * 
     * @param page
     * @param request
     * @param response
     * @param model
     * @return
     */
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

        twitterService.findTwitterPage(new TwitterQueryCriterion(null), p);
        rsp.setSuccess(true);
        rsp.put("twitter", p.getResult());

        return rsp;
    }

    /**
     * 上传图片
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/insert_image")
    @ResponseBody
    public ResponseEntity insertImage(@RequestParam("image") MultipartFile image, HttpServletRequest request, HttpServletResponse response, Model model) {
        ResponseEntity rsp = new ResponseEntity();
        User user = SecurityUtils.getCurrentUser(request);
        if (user == null) {
            rsp.setSuccess(false);
            rsp.setMessage("请先登录");
            return rsp;
        }
        // 限定上传图片的格式
        boolean isImage = ImageUtils.isImage(FilenameUtils.getExtension(image.getOriginalFilename()));
        if (!isImage) {
            rsp.setSuccess(false);
            rsp.setMessage("上传文件格式不正确");
            return rsp;
        }
        // 限定上传图片大小
        long size = image.getSize() / 1024;
        if (size > 512) {
            rsp.setSuccess(false);
            rsp.setMessage("上传图片大不能超过512K");
            return rsp;
        }
        Attachment attachment = null;
        try {
            attachment = attachmentService.createAttachment(image, Attachment.AttachFrom.FROM_TWITTER);
        } catch (Exception e) {
            // TODO: handle exception
            rsp.setSuccess(false);
            rsp.setMessage("上传图片失败");
            return rsp;
        }
        rsp.setSuccess(true);
        attachment.setUser(null);
        rsp.put("attachment", attachment);
        return rsp;
    }

    /**
     * 删除图片
     * 
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/delete_image")
    @ResponseBody
    public ResponseEntity deleteImage(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity rsp = new ResponseEntity();
        User user = SecurityUtils.getCurrentUser(request);
        if (user == null) {
            rsp.setSuccess(false);
            rsp.setMessage("请先登录");
            return rsp;
        }
        if (id == null) {
            rsp.setSuccess(false);
            rsp.setMessage("无法确认要删除的图片");
            return rsp;
        }
        attachmentService.deleteAttachment(id);
        rsp.setSuccess(true);
        return rsp;
    }
}
