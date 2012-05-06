/**
 * 
 */
package org.mspring.mlog.exception;

/**
 * @author Gao Youbo
 * @since Jan 17, 2012 http://www.mspring.org
 */
public class ThemeNotFoundException extends NullPointerException {

    /**
     * 
     */
    private static final long serialVersionUID = -6235613868408858043L;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return "theme not found, please check theme folder.";
    }

}
