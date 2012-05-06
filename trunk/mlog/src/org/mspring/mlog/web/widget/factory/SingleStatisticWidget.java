/**
 * 
 */
package org.mspring.mlog.web.widget.factory;

import java.util.List;

import org.mspring.mlog.web.widget.AbstractWidget;
import org.mspring.platform.common.Validator;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since May 5, 2012
 */
public class SingleStatisticWidget extends AbstractWidget {

    /* (non-Javadoc)
     * @see org.mspring.mlog.web.widget.WidgetInterface#render(java.util.List)
     */
    @Override
    public String render(List args) {
        // TODO Auto-generated method stub
        StringBuffer result = new StringBuffer();
        
        String strArticleId = "";
        if (args != null && args.size() > 0) {
            strArticleId = args.get(0).toString();
        }
        if (!StringUtils.isBlank(strArticleId) && Validator.isNumber(strArticleId)) {
            result.append("<script type=\"text/javascript\">\n");
            result.append(" $(document).ready(function(){\n");
            result.append("     singleStatistic(" + strArticleId + ");\n");
            result.append(" });\n");
            result.append("</script>\n");
        }
        
        return result.toString();
    }

}
