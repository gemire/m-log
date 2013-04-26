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
public enum GravatarRating {

    GENERAL_AUDIENCES("g"),

    PARENTAL_GUIDANCE_SUGGESTED("pg"),

    RESTRICTED("r"),

    XPLICIT("x");

    private String code;

    private GravatarRating(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}