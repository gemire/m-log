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
public class GravatarDownloadException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public GravatarDownloadException(Throwable cause) {
        super("Gravatar could not be downloaded: " + cause.getMessage(), cause);
    }

}
