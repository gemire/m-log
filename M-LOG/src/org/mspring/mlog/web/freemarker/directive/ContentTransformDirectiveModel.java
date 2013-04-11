/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive;

import java.io.IOException;
import java.util.Map;

import org.mspring.mlog.web.freemarker.DirectiveUtils;
import org.mspring.platform.utils.HTMLUtils;
import org.mspring.platform.utils.StringUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2013-2-28
 * @description
 * @TODO 字符串内容处理
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ContentTransformDirectiveModel extends AbstractDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        String content = DirectiveUtils.getString("content", params);
        if (content == null) {
            return;
        }

//        Boolean removeHtml = DirectiveUtils.getBoolean("removeHtml", params); // 是否剔除html
//        Boolean substring = DirectiveUtils.getBoolean("substring", params); // 是否截取
//        if (removeHtml == null) {
//            removeHtml = false;
//        }
//        if (substring == null) {
//            substring = false;
//        }
//        
//
//        if (removeHtml) {
//            content = HTMLUtils.getHtmlText(content);
//        }
//        if (substring) {
//            Integer beginIndex = DirectiveUtils.getInt("beginIndex", params);
//            Integer endIndex = DirectiveUtils.getInt("endIndex", params);
//
//            if (beginIndex == null) {
//                beginIndex = 0;
//            }
//            if (endIndex == null) {
//                if (beginIndex < content.length()) {
//                    content = StringUtils.substring(content, beginIndex) + "...";
//                }
//            } else {
//                if (endIndex < content.length()) {
//                    content = StringUtils.substring(content, beginIndex, endIndex) + "...";
//                }
//            }
//        }
        
        Boolean removeHtml = DirectiveUtils.getBoolean("removeHtml", params); // 是否剔除html
        Boolean substring = DirectiveUtils.getBoolean("substring", params); // 是否截取
        Integer beginIndex = DirectiveUtils.getInt("beginIndex", params);
        Integer endIndex = DirectiveUtils.getInt("endIndex", params);
        
        if (removeHtml == null) {
            removeHtml = false;
        }
        if (substring == null) {
            substring = false;
        }
        if (beginIndex == null) {
            beginIndex = 0;
        }
        
        content = StringUtils.contentTransform(content, removeHtml, substring, beginIndex, endIndex);
        
        env.getOut().append(content);
    }

}
