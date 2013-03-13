/**
 * 
 */
package org.mspring.mlog.web.security.rememberme;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.mlog.common.Keys;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.web.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;

/**
 * @author Gao Youbo
 * @since 2013-1-15
 * @Description
 * @TODO
 */
@SuppressWarnings("deprecation")
public class TokenBasedRememberMeServices extends org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices {

    private static final Logger log = Logger.getLogger(TokenBasedRememberMeServices.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.web.authentication.rememberme.
     * TokenBasedRememberMeServices#processAutoLoginCookie(java.lang.String[],
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        if (cookieTokens.length != 3) {
            throw new InvalidCookieException("Cookie token did not contain 3" + " tokens, but contained '" + Arrays.asList(cookieTokens) + "'");
        }
        
        long tokenExpiryTime;
        try {
            tokenExpiryTime = new Long(cookieTokens[1]).longValue();
        }
        catch (NumberFormatException nfe) {
            throw new InvalidCookieException("Cookie token[1] did not contain a valid number (contained '" + cookieTokens[1] + "')");
        }

        if (isTokenExpired(tokenExpiryTime)) {
            throw new InvalidCookieException("Cookie token[1] has expired (expired on '" + new Date(tokenExpiryTime) + "'; current time is '" + new Date() + "')");
        }

        UserDetails userDetails = getUserDetailsService().loadUserByUsername(cookieTokens[0]);

        String expectedTokenSignature = makeTokenSignature(tokenExpiryTime, userDetails.getUsername(), userDetails.getPassword());
        if (!equals(expectedTokenSignature, cookieTokens[2])) {
            throw new InvalidCookieException("Cookie token[2] contained signature '" + cookieTokens[2] + "' but expected '" + expectedTokenSignature + "'");
        }
        
        //remember-me自动登录成功后，将User对象保存到Session中
        setSessionUser(userDetails, request);
        return userDetails;
    }
    
    /**
     * 将User对象保存到Session中
     */
    private static void setSessionUser(UserDetails userDetails, HttpServletRequest request){
        User user = null;
        String username = userDetails.getUsername();
        if (userDetails instanceof UserDetailsImpl) {
            UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetails;
            user = userDetailsImpl.getUserEntity();
            if (user == null) {
                user = ServiceFactory.getUserService().getUserByName(username);
            }
        }
        else {
            user = ServiceFactory.getUserService().getUserByName(username);
        }
        request.getSession().setAttribute(Keys.CURRENT_USER, user);
    }

    /**
     * Constant time comparison to prevent against timing attacks.
     */
    private static boolean equals(String expected, String actual) {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        if (expectedBytes.length != actualBytes.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < expectedBytes.length; i++) {
            result |= expectedBytes[i] ^ actualBytes[i];
        }
        return result == 0;
    }

    private static byte[] bytesUtf8(String s) {
        if (s == null) {
            return null;
        }
        return Utf8.encode(s);
    }

}
