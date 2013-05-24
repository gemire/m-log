/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Jaw;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.JawService;
import org.mspring.mlog.web.security.SecurityUtils;
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
@RequestMapping("/jaw")
public class JawWidget extends AbstractWebWidget {

    @Autowired
    private JawService jawService;

    @RequestMapping({ "/", "" })
    public String show(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "skin:/jaw";
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

        Jaw jaw = new Jaw();
        jaw.setAuthor(user);
        jaw.setContent(content);
        try {
            Long id = jawService.createJaw(jaw);
            rsp.setSuccess(true);
            rsp.setMessage("发表成功");
            rsp.addData("id", id);
            return rsp;
        } catch (Exception e) {
            // TODO: handle exception
            rsp.setSuccess(false);
            rsp.setMessage("发表内容失败");
            return rsp;
        }
    }
}
