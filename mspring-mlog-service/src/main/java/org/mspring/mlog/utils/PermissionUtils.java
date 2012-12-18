/**
 * 
 */
package org.mspring.mlog.utils;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Album;
import org.mspring.mlog.entity.Post;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2012-11-26
 * @Description
 * @TODO 权限管理
 */
public class PermissionUtils {
    private static final String ALBUM_TOKEN_KEY_PRE = "ALBUM_TOKEN_";
    private static final String POST_TOKEN_KEY_PRE = "POST_TOKEN_";

    /**
     * 是否有访问相册的权限
     * 
     * @param albumId
     * @param request
     * @return
     */
    public static boolean hasAlbumPermission(Long albumId, HttpSession session) {
        Album album = ServiceFactory.getAlbumService().getAlbumById(albumId);
        if (Album.Type.PUBLIC.equals(album.getType())) {
            return true;
        }
        else if (Album.Type.PRIVATE.equals(album.getType())) {
            return false;
        }
        Object obj = session.getAttribute(getAlbumTokenKey(albumId));
        if (obj != null && obj instanceof Token) {
            Token token = (Token) obj;
            boolean flag = ServiceFactory.getAlbumService().hasPermission(new Long(token.getKey()), token.getPassword());
            if (flag == false) {
                session.setAttribute(getAlbumTokenKey(albumId), null);
            }
            return flag;
        }
        return false;
    }

    /**
     * 判断是否有访问相册的权限
     * 
     * @param albumId
     * @param password
     * @return
     */
    public static boolean hasAlbumPermission(Long albumId, String password) {
        if (albumId == null || StringUtils.isBlank(password)) {
            return false;
        }
        return ServiceFactory.getAlbumService().hasPermission(albumId, password);
    }

    /**
     * 设置相册访问的token
     * 
     * @param albumId
     * @param password
     * @param request
     */
    public static void setAlbumPermission(Long albumId, String password, HttpSession session) {
        Token token = new Token(albumId.toString(), password);
        session.setAttribute(getAlbumTokenKey(albumId), token);
    }

    /**
     * 获取保存Album Token的key
     * 
     * @param albumId
     * @return
     */
    private static String getAlbumTokenKey(Long albumId) {
        return ALBUM_TOKEN_KEY_PRE + albumId;
    }

    // **************************************************************************//

    /**
     * 是否有访问文章的权限
     * 
     * @param post
     * @param request
     * @return
     */
    public static boolean hasPostPermission(Post post, HttpSession session) {
        if (StringUtils.isBlank(post.getPassword())) {
            return true;
        }
        Object obj = session.getAttribute(getPostTokenKey(post.getId()));
        if (obj != null && obj instanceof Token) {
            Token token = (Token) obj;
            if (post.getPassword().equals(token.getPassword())) {
                return true;
            }
            else {
                session.setAttribute(getPostTokenKey(post.getId()), null);
                return false;
            }
        }
        return false;
    }

    /**
     * 判断是否有访问相册的权限
     * 
     * @param postId
     * @param password
     * @return
     */
    public static boolean hasPostPermission(Long postId, String password) {
        if (postId == null || StringUtils.isBlank(password)) {
            return false;
        }
        return ServiceFactory.getPostService().hasPermisstion(postId, password);
    }

    /**
     * 设置文章访问权限
     * 
     * @param postId
     * @param password
     * @param request
     * @return
     */
    public static void setPostPermission(Long postId, String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Token token = new Token(postId.toString(), password);
        session.setAttribute(getPostTokenKey(postId), token);
    }

    /**
     * 获取保存Post Token的Key
     * 
     * @param postId
     * @return
     */
    private static String getPostTokenKey(Long postId) {
        return POST_TOKEN_KEY_PRE + postId;
    }
}

class Token implements Serializable {
    // 标识
    private String key;
    // 密码
    private String password;

    /**
     * 
     */
    public Token() {
        super();
    }

    /**
     * @param key
     * @param password
     */
    public Token(String key, String password) {
        super();
        this.key = key;
        this.password = password;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}