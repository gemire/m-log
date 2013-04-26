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
public enum GravatarDefaultImage {

    GRAVATAR_ICON(""),

    IDENTICON("identicon"),

    MONSTERID("monsterid"),

    WAVATAR("wavatar"),

    HTTP_404("404");

    private String code;

    private GravatarDefaultImage(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
