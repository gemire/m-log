/**
 * 
 */
package org.mspring.mlog.web.validator;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Catalog;
import org.mspring.platform.exception.BusinessException;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.validation.AbstractValidator;
import org.mspring.platform.web.validation.Errors;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since 2012-8-8
 * @Description
 * @TODO Catalog对象验证
 */
@Component
public class CatalogValidator extends AbstractValidator {

    private static final Logger log = Logger.getLogger(CatalogValidator.class);

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
        Catalog catalog = (Catalog) target;
        if (StringUtils.isBlank(catalog.getName())) {
            errors.addErrors("name", "分类名称不能为空");
        }
        if (StringUtils.isNotBlank(catalog.getName()) && ServiceFactory.getCatalogService().catalogExists(catalog.getName(), catalog.getId())) {
            errors.addErrors("name", "分类名称已经存在");
        }
        return errors;
    }

}
