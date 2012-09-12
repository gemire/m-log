/**
 * 
 */
package org.mspring.mlog.web.validator;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Link;
import org.mspring.platform.exception.BusinessException;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.ValidatorUtils;
import org.mspring.platform.web.validation.AbstractValidator;
import org.mspring.platform.web.validation.Errors;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since 2012-8-9
 * @Description
 * @TODO
 */
@Component
public class LinkValidator extends AbstractValidator {
    private static final Logger log = Logger.getLogger(LinkValidator.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.platform.web.validation.Validator#validate(java.lang.Object)
     */
    @Override
    public Errors validate(Object target) {
        // TODO Auto-generated method stub
        if (target == null) {
            log.error("validation failure, target object is null.");
            throw new BusinessException("validation failure, target object is null.");
        }
        Errors errors = getErrorsInstance();
        Link link = (Link) target;
        if (StringUtils.isBlank(link.getName())) {
            errors.addErrors("name", "链接名称不能为空");
        }
        if (!ValidatorUtils.isUrl(link.getUrl())) {
            errors.addErrors("url", "链接URL不符合规范");
        }
        return errors;
    }

}
