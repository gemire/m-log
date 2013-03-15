/**
 * 
 */
package org.mspring.mlog.web.module.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.common.Keys;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.platform.utils.ImageUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
@Widget
@RequestMapping(value = "/common")
public class ValidateCodeWidget {
    //验证码中允许出现的字符串
    private static final String DEFAULT_ALLOW_VALIDATE_STRING = "0123456789abcdefghijklmnopqrstuvwxyz";

    @RequestMapping(value = "validateCode", method = RequestMethod.GET)
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String validateCode = ImageUtils.validateCode(response.getOutputStream(), DEFAULT_ALLOW_VALIDATE_STRING);
        request.getSession().setAttribute(Keys.SESSION_VALIDATE_CODE, validateCode);
    }
}
