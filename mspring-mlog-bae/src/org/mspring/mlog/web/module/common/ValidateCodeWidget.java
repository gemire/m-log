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
    private static final String DEFAULT_ALLOW_VALIDATE_STRING = "0123456789";

    @RequestMapping(value = "validateCode", method = RequestMethod.GET)
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String validateCode = ImageUtils.validateCode(response.getOutputStream(), DEFAULT_ALLOW_VALIDATE_STRING);
        request.getSession().setAttribute(Keys.VALIDATE_CODE, validateCode);
    }

    @RequestMapping(value = "validateImage", method = RequestMethod.GET)
    public String validateImage() {
        return "common/validateImage";
    }
}
