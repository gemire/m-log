/**
 * 
 */
package org.mspring.platform.api.akisment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author Gao Youbo
 * @since 2013-5-6
 * @description
 * @TODO Akismet反垃圾评论API
 */
public class Akismet {

    private Log logger = LogFactory.getLog(Akismet.class);

    // Constants
    private static final String USER_AGENT_HEADER = "User-Agent";
    private static final String USER_AGENT_VALUE = "Akismet Java API/1.02";

    private static final String API_PARAMETER_KEY = "key";
    private static final String API_PARAMETER_BLOG = "blog";
    private static final String API_PARAMETER_USER_IP = "user_ip";
    private static final String API_PARAMETER_USER_AGENT = "user_agent";
    private static final String API_PARAMETER_REFERRER = "referrer";
    private static final String API_PARAMETER_PERMALINK = "permalink";
    private static final String API_PARAMETER_COMMENT_TYPE = "comment_type";
    private static final String API_PARAMETER_COMMENT_AUTHOR = "comment_author";
    private static final String API_PARAMETER_COMMENT_AUTHOR_EMAIL = "comment_author_email";
    private static final String API_PARAMETER_COMMENT_AUTHOR_URL = "comment_author_url";
    private static final String API_PARAMETER_COMMENT_CONTENT = "comment_content";

    private static final String VALID_RESPONSE = "valid";
    private static final String FALSE_RESPONSE = "false";

    public static final String COMMENT_TYPE_BLANK = "";
    public static final String COMMENT_TYPE_COMMENT = "comment";
    public static final String COMMENT_TYPE_TRACKBACK = "trackback";
    public static final String COMMENT_TYPE_PINGBACK = "pingback";

    private DefaultHttpClient httpClient;
    private String apiKey;
    private String blog;
    private boolean verifiedKey = false;
    private int httpResult;

    /**
     * Construct an instance to work with the Akismet API.
     * <p>
     * </p>
     * 
     * <pre>
     * Usage:
     * <p/>
     * Akismet akismet = new Akismet("Your API key", "http://your.blog.com/");
     * System.out.println("Testing comment spam: " + akismet.commentCheck("x.y.z.w", "XXX", "", "", "", "", "", "", "VIAGRA! LOTS OF VIAGRA!", null));
     * </pre>
     * <p>
     * You <strong>do not</strong> need to call {@link #verifyAPIKey()} before
     * using the
     * {@link #commentCheck(String, String, String, String, String, String, String, String, String, java.util.Map)},
     * {@link #submitSpam(String, String, String, String, String, String, String, String, String, java.util.Map)}
     * , or
     * {@link #submitHam(String, String, String, String, String, String, String, String, String, java.util.Map)}
     * methods.
     * </p>
     * 
     * @param apiKey
     *            Akismet API key
     * @param blog
     *            Blog associated with the API key
     * @throws IllegalArgumentException
     *             If either the API key or blog is <code>null</code>
     */
    public Akismet(String apiKey, String blog) {
        this.apiKey = apiKey;
        this.blog = blog;

        if (apiKey == null) {
            throw new IllegalArgumentException("API key cannot be null");
        }

        if (blog == null) {
            throw new IllegalArgumentException("Blog cannot be null");
        }

        httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(USER_AGENT_HEADER, USER_AGENT_VALUE);
    }

    /**
     * Return the HTTP status code of the last operation
     * 
     * @return HTTP status code
     */
    public int getHttpResult() {
        return httpResult;
    }

    /**
     * Check to see if the API key has been verified
     * 
     * @return <code>true</code> if the API key has been verified,
     *         <code>false</code> otherwise
     */
    public boolean isVerifiedKey() {
        return verifiedKey;
    }

    /**
     * Sets proxy configuration information. This method must be called before
     * any calls to the API if you require proxy configuration.
     * 
     * @param proxyHost
     *            Proxy host
     * @param proxyPort
     *            Proxy port
     */
    public void setProxyConfiguration(String proxyHost, int proxyPort) {
        HttpHost host = new HttpHost(proxyHost, proxyPort);
        httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, host);
    }

    /**
     * Check to see if the input is <code>null</code> or blank
     * 
     * @param input
     *            Input
     * @return <code>true</code> if input is null or blank, <code>false</code>
     *         otherwise
     */
    private boolean checkNullOrBlank(String input) {
        return (input == null || "".equals(input));
    }

    /**
     * Sets proxy authentication information. This method must be called before
     * any calls to the API if you require proxy authentication.
     * 
     * @param proxyUsername
     *            Username to access proxy
     * @param proxyPassword
     *            Password to access proxy
     */
    public void setProxyAuthenticationConfiguration(String proxyUsername, String proxyPassword) {
        httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(proxyUsername, proxyPassword));
    }

    /**
     * Verify your API key
     * 
     * @return <code>true</code> if the API key has been verified,
     *         <code>false</code> otherwise
     */
    public boolean verifyAPIKey() {
        boolean callResult = true;

        HttpPost post = new HttpPost("http://rest.akismet.com/1.1/verify-key");

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair(API_PARAMETER_KEY, apiKey));
        nvps.add(new BasicNameValuePair(API_PARAMETER_BLOG, blog));
        try {
            post.setEntity(new UrlEncodedFormEntity(nvps));
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            HttpResponse response = httpClient.execute(post);

            httpResult = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());

            if (logger.isDebugEnabled()) {
                logger.debug("Akismet response: " + result);
            }

            if (!checkNullOrBlank(result)) {
                if (!VALID_RESPONSE.equals(result)) {
                    callResult = false;
                }
            }
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e);
            }

            callResult = false;
        }

        verifiedKey = callResult;

        return callResult;
    }

    /**
     * Generic call to Akismet
     * 
     * @param function
     *            Function used in constructing the URL to Akismet for the
     *            proper function to call. Either "comment-check",
     *            "submit-spam", or "submit-ham".
     * @param ipAddress
     *            IP address of the comment submitter
     * @param userAgent
     *            User agent information
     * @param referrer
     *            The content of the HTTP_REFERER header should be sent here
     * @param permalink
     *            The permanent location of the entry the comment was submitted
     *            to
     * @param commentType
     *            May be blank, comment, trackback, pingback, or a made up value
     *            like "registration"
     * @param author
     *            Submitted name with the comment
     * @param authorEmail
     *            Submitted email address
     * @param authorURL
     *            Commenter URL
     * @param commentContent
     *            The content that was submitted
     * @param other
     *            In PHP there is an array of enviroment variables called
     *            $_SERVER which contains information about the web server
     *            itself as well as a key/value for every HTTP header sent with
     *            the request. This data is highly useful to Akismet as how the
     *            submited content interacts with the server can be very
     *            telling, so please include as much information as possible.
     * @return <code>true</code> if the comment is identified by Akismet as
     *         spam, <code>false</code> otherwise
     */
    protected boolean akismetCall(String function, String ipAddress, String userAgent, String referrer, String permalink, String commentType, String author, String authorEmail, String authorURL, String commentContent, Map other) {
        boolean callResult = false;

        String akismetURL = "http://" + apiKey + ".rest.akismet.com/1.1/" + function;

        HttpPost post = new HttpPost(akismetURL);
        post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair(API_PARAMETER_BLOG, blog));
        if (ipAddress != null) {
            nvps.add(new BasicNameValuePair(API_PARAMETER_USER_IP, ipAddress));
        } else {
            nvps.add(new BasicNameValuePair(API_PARAMETER_USER_IP, ""));
        }
        if (userAgent != null) {
            nvps.add(new BasicNameValuePair(API_PARAMETER_USER_AGENT, userAgent));
        }
        if (referrer != null) {
            nvps.add(new BasicNameValuePair(API_PARAMETER_REFERRER, referrer));
        }
        if (permalink != null) {
            nvps.add(new BasicNameValuePair(API_PARAMETER_PERMALINK, permalink));
        }
        if (commentType != null) {
            nvps.add(new BasicNameValuePair(API_PARAMETER_COMMENT_TYPE, commentType));
        }
        if (author != null) {
            nvps.add(new BasicNameValuePair(API_PARAMETER_COMMENT_AUTHOR, author));
        }
        if (authorEmail != null) {
            nvps.add(new BasicNameValuePair(API_PARAMETER_COMMENT_AUTHOR_EMAIL, authorEmail));
        }
        if (authorURL != null) {
            nvps.add(new BasicNameValuePair(API_PARAMETER_COMMENT_AUTHOR_URL, authorURL));
        }
        if (commentContent != null) {
            nvps.add(new BasicNameValuePair(API_PARAMETER_COMMENT_CONTENT, commentContent));
        }

        if (other != null && other.size() > 0) {
            Iterator keyIterator = other.keySet().iterator();
            while (keyIterator.hasNext()) {
                String key = (String) keyIterator.next();
                if ((key != null) && (other.get(key) != null)) {
                    nvps.add(new BasicNameValuePair(key, other.get(key).toString()));
                }
            }
        }
        
        try {
            post.setEntity(new UrlEncodedFormEntity(nvps));
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            HttpResponse response = httpClient.execute(post);

            httpResult = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity());

            if (logger.isDebugEnabled()) {
                logger.debug("Akismet response: " + result);
            }

            if (!checkNullOrBlank(result)) {
                result = result.trim();

                if (!FALSE_RESPONSE.equals(result)) {
                    callResult = true;
                }
            }
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e);
            }

            callResult = true;
        }

        return callResult;
    }

    /**
     * From the API docs, This is basically the core of everything. This call
     * takes a number of arguments and characteristics about the submitted
     * content and then returns a thumbs up or thumbs down. Almost everything is
     * optional, but performance can drop dramatically if you exclude certain
     * elements. I would recommend erring on the side of too much data, as
     * everything is used as part of the Akismet signature."
     * 
     * @param ipAddress
     *            IP address of the comment submitter
     * @param userAgent
     *            User agent information
     * @param referrer
     *            The content of the HTTP_REFERER header should be sent here
     * @param permalink
     *            The permanent location of the entry the comment was submitted
     *            to
     * @param commentType
     *            May be blank, comment, trackback, pingback, or a made up value
     *            like "registration"
     * @param author
     *            Submitted name with the comment
     * @param authorEmail
     *            Submitted email address
     * @param authorURL
     *            Commenter URL
     * @param commentContent
     *            The content that was submitted
     * @param other
     *            In PHP there is an array of enviroment variables called
     *            $_SERVER which contains information about the web server
     *            itself as well as a key/value for every HTTP header sent with
     *            the request. This data is highly useful to Akismet as how the
     *            submited content interacts with the server can be very
     *            telling, so please include as much information as possible.
     * @return <code>true</code> if the comment is identified by Akismet as
     *         spam, <code>false</code> otherwise
     */
    public boolean commentCheck(String ipAddress, String userAgent, String referrer, String permalink, String commentType, String author, String authorEmail, String authorURL, String commentContent, Map other) {
        return akismetCall("comment-check", ipAddress, userAgent, referrer, permalink, commentType, author, authorEmail, authorURL, commentContent, other);
    }

    /**
     * From the API docs, This call is for submitting comments that weren't
     * marked as spam but should have been. It takes identical arguments as
     * comment check."
     * 
     * @param ipAddress
     *            IP address of the comment submitter
     * @param userAgent
     *            User agent information
     * @param referrer
     *            The content of the HTTP_REFERER header should be sent here
     * @param permalink
     *            The permanent location of the entry the comment was submitted
     *            to
     * @param commentType
     *            May be blank, comment, trackback, pingback, or a made up value
     *            like "registration"
     * @param author
     *            Submitted name with the comment
     * @param authorEmail
     *            Submitted email address
     * @param authorURL
     *            Commenter URL
     * @param commentContent
     *            The content that was submitted
     * @param other
     *            In PHP there is an array of enviroment variables called
     *            $_SERVER which contains information about the web server
     *            itself as well as a key/value for every HTTP header sent with
     *            the request. This data is highly useful to Akismet as how the
     *            submited content interacts with the server can be very
     *            telling, so please include as much information as possible.
     * @return <code>true</code> if the comment is identified by Akismet as
     *         spam, <code>false</code> otherwise
     */
    public void submitSpam(String ipAddress, String userAgent, String referrer, String permalink, String commentType, String author, String authorEmail, String authorURL, String commentContent, Map other) {
        akismetCall("submit-spam", ipAddress, userAgent, referrer, permalink, commentType, author, authorEmail, authorURL, commentContent, other);
    }

    /**
     * From the API docs, This call is intended for the marking of false
     * positives, things that were incorrectly marked as spam. It takes
     * identical arguments as comment check and submit spam."
     * 
     * @param ipAddress
     *            IP address of the comment submitter
     * @param userAgent
     *            User agent information
     * @param referrer
     *            The content of the HTTP_REFERER header should be sent here
     * @param permalink
     *            The permanent location of the entry the comment was submitted
     *            to
     * @param commentType
     *            May be blank, comment, trackback, pingback, or a made up value
     *            like "registration"
     * @param author
     *            Submitted name with the comment
     * @param authorEmail
     *            Submitted email address
     * @param authorURL
     *            Commenter URL
     * @param commentContent
     *            The content that was submitted
     * @param other
     *            In PHP there is an array of enviroment variables called
     *            $_SERVER which contains information about the web server
     *            itself as well as a key/value for every HTTP header sent with
     *            the request. This data is highly useful to Akismet as how the
     *            submited content interacts with the server can be very
     *            telling, so please include as much information as possible.
     * @return <code>true</code> if the comment is identified by Akismet as
     *         spam, <code>false</code> otherwise
     */
    public void submitHam(String ipAddress, String userAgent, String referrer, String permalink, String commentType, String author, String authorEmail, String authorURL, String commentContent, Map other) {
        akismetCall("submit-ham", ipAddress, userAgent, referrer, permalink, commentType, author, authorEmail, authorURL, commentContent, other);
    }

}
