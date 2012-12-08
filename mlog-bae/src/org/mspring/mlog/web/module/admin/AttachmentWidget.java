/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.mspring.mlog.utils.AttachmentUtils;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class AttachmentWidget {
    @ResponseBody
    @RequestMapping("/upload")
    public String upload(HttpServletRequest request, HttpServletResponse response, Model model) {
        String url = "";
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
            url = AttachmentUtils.upload(mf);
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            JSONObject obj = new JSONObject();
            obj.put("error", 1);
            obj.put("message", "upload failure!");
            return obj.toString();
        }
        JSONObject obj = new JSONObject();
        obj.put("error", 0);
        obj.put("url", url);
        return obj.toString();
    }
}
