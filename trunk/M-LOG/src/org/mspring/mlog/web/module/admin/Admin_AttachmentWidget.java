/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Attachment;
import org.mspring.mlog.service.AttachmentService;
import org.mspring.mlog.support.log.Log;
import org.mspring.platform.utils.JSONUtils;
import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author Gao Youbo
 * @since 2012-11-22
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/attachment")
public class Admin_AttachmentWidget extends AbstractAdminWidget {
    @Autowired
    private AttachmentService attachmentService;

    @ResponseBody
    @RequestMapping("/upload")
    @Log
    public String upload(@RequestParam(required = false) Long fid, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            if (fileMap == null || fileMap.size() < 1) {
                return null;
            }
            MultipartFile mf = null;
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                mf = entity.getValue();
                break;
            }
            Attachment attachment = attachmentService.createAttachment(mf, Attachment.AttachFrom.FROM_POST, fid);
            return JSONUtils.toJson(attachment);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/dialog")
    public String uploadDialog(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/admin/attachment/dialog";
    }

    @RequestMapping("/uploadview")
    public String upload_view(@RequestParam(required = false) Long post, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("post", post);
        return "/admin/attachment/uploadview";
    }

    @RequestMapping("/attachments")
    public String attachments(@RequestParam(required = false) Long post, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Attachment> attachments = attachmentService.findAttachmentsByPost(post);
        model.addAttribute("attachments", attachments);
        model.addAttribute("post", post);
        return "/admin/attachment/attachments";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            attachmentService.deleteAttachment(id);
        } catch (Exception e) {
            // TODO: handle exception
            return "false";
        }
        return "true";
    }
}
