/**
 * 
 */
package org.mspring.mlog.web.api.t.common;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2012-8-30
 * @Description
 * @TODO 存放post参数
 */
public class ParamArrayList extends ArrayList<NameValuePair> {

    /**
     * 
     */
    private static final long serialVersionUID = 7370267981429313818L;

    /*
     * (non-Javadoc)
     * 
     * @see java.util.ArrayList#add(java.lang.Object)
     */
    @Override
    public boolean add(NameValuePair e) {
        // TODO Auto-generated method stub
        if (StringUtils.isNotBlank(e.getValue())) {
            return super.add(e);
        }
        else {
            return false;
        }
    }
    
    public boolean add(String name, String value){
        return add(new BasicNameValuePair(name, value));
    }

}
