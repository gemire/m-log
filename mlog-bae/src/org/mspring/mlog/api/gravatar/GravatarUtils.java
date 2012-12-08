/**
 * 
 */
package org.mspring.mlog.api.gravatar;

/**
 * @author Gao Youbo
 * @since 2012-8-14
 * @Description
 * @TODO
 */
public final class GravatarUtils {
    /**
     * 获取Gravatar图像链接
     * 
     * @param email
     * @return
     */
    public static String getGravatarImage(String email) {
        return new Gravatar().getUrl(email);
    }
}
