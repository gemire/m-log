/**
 * 
 */
package org.mspring.mlog.web.module.script.stat;

/**
 * @author Gao Youbo
 * @since 2012-11-16
 * @Description
 * @TODO
 */
public class StatCmdFactory {
    public static AbstractStatCmd getCmd(String name) {
        AbstractStatCmd cmd = null;
        if ("post_click".equals(name)) { // 文章点击统计
            cmd = new PostClickStatCmd();
        }
        else if ("blog_click".equals(name)) { // 博客点击
            cmd = new BlogClickStatCmd();
        }
        else {
            cmd = new NullStatCmd();
        }
        return cmd;
    }
}
