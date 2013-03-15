/**
 * 
 */
package org.mspring.mlog.inf.file;

import org.mspring.mlog.core.ServiceFactory;

/**
 * @author Gao Youbo
 * @since 2013-1-7
 * @Description 
 * @TODO 
 */
public class FileFunctionFactory {
    
    public final static AbstractFileFunction getFunction(){
        AbstractFileFunction fun = null;
        String kuaipan_on = ServiceFactory.getOptionService().getOption("api_kuaipan_on");
        if ("true".equals(kuaipan_on)) {
            fun = new KuaipanFileFunction();
        }
        else {
            fun = new SystemFileFunction();
        }
        return fun;
    }
}
