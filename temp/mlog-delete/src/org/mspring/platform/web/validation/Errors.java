/**
 * 
 */
package org.mspring.platform.web.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gao Youbo
 * @since 2012-8-2
 * @Description
 * @TODO
 */
public class Errors {

    private List<ErrorBean> errors;

    public void addErrors(String field, String message) {
        // TODO Auto-generated method stub
        if (errors == null) {
            errors = new ArrayList<ErrorBean>();
        }
        errors.add(new ErrorBean(field, message));
    }

    public void addErrors(ErrorBean errorBean) {
        // TODO Auto-generated method stub
        if (errors == null) {
            errors = new ArrayList<ErrorBean>();
        }
        errors.add(errorBean);
    }

    public boolean hasErrors() {
        // TODO Auto-generated method stub
        if (errors != null && errors.size() > 0) {
            return true;
        }
        return false;
    }

    public void clearErrors() {
        // TODO Auto-generated method stub
        if (errors != null && errors.size() > 0) {
            errors.clear();
        }
    }

    public void addErrors(List<ErrorBean> errors) {
        // TODO Auto-generated method stub
        if (this.errors == null) {
            this.errors = new ArrayList<ErrorBean>();
        }
        this.errors.addAll(errors);
    }

    public List<ErrorBean> getErrors() {
        // TODO Auto-generated method stub
        return this.errors;
    }

}
