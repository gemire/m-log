/**
 * 
 */
package org.mspring.mlog.web.api.t.utils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * @author Gao Youbo
 * @since 2012-8-30
 * @Description
 * @TODO
 */
public class THttpUtils {
    /**
     * post方式发送请求
     * 
     * @param url
     * @param queryString
     * @return
     * @throws URISyntaxException 
     * @throws UnsupportedEncodingException 
     * @throws Exception
     */
    public static String httpPost(String url, String queryString) throws URISyntaxException, UnsupportedEncodingException {
        String responseData = null;
        URI tmpUri = new URI(url);
        URI uri = URIUtils.createURI(tmpUri.getScheme(), tmpUri.getHost(), tmpUri.getPort(), tmpUri.getPath(), queryString, null);

        HttpPost httpPost = new HttpPost(uri);
        httpPost.getParams().setParameter("http.socket.timeout", new Integer(5000));
        if (queryString != null && !queryString.equals("")) {
            StringEntity reqEntity = new StringEntity(queryString);
            // 设置类型
            reqEntity.setContentType("application/x-www-form-urlencoded");
            // 设置请求的数据
            httpPost.setEntity(reqEntity);
        }

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(httpPost);
            responseData = EntityUtils.toString(response.getEntity());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            httpPost.abort();
        }
        return responseData;
    }
    
    
    /**
     * get请求
     * @param url
     * @param queryString
     * @return
     * @throws URISyntaxException
     * @throws UnsupportedEncodingException
     */
    public static String httpGet(String url, String queryString) throws URISyntaxException, UnsupportedEncodingException {
        String responseData = null;
        URI tmpUri = new URI(url);
        URI uri = URIUtils.createURI(tmpUri.getScheme(), tmpUri.getHost(), tmpUri.getPort(), tmpUri.getPath(), queryString, null);
        
        System.out.println(uri);

        HttpGet httpGet = new HttpGet(uri);
        httpGet.getParams().setParameter("http.socket.timeout", new Integer(5000));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(httpGet);
            responseData = EntityUtils.toString(response.getEntity());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            httpGet.abort();
        }
        return responseData;
    }
}
