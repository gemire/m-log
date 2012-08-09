/**
 * 
 */
package org.mspring.mlog.web.validator;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.User;
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
public class UserInfoValidator extends AbstractValidator {
    private static final Logger log = Logger.getLogger(UserInfoValidator.class);

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
        User user = (User) target;
        Errors errors = getErrorsInstance();
        if (StringUtils.isBlank(user.getAlias())) {
            errors.addErrors("alias", "昵称不能为空");
        }
        if (StringUtils.isBlank(user.getEmail())) {
            errors.addErrors("email", "E-mail地址不能为空");
        }
        if (StringUtils.isNotBlank(user.getEmail()) && !ValidatorUtils.isEmailAddress(user.getEmail())) {
            errors.addErrors("email", "E-mail地址格式不正确");
        }
        return errors;
    }

}
