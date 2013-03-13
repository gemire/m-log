/**
 * 
 */
package org.mspring.mlog.api.spider.handler.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mspring.mlog.api.spider.handler.ContentHandler;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.platform.utils.ImageUtils;

/**
 * @author Gao Youbo
 * @since 2013-3-8
 * @description
 * @TODO
 */
public class ContentImageHandler implements ContentHandler {

    @Override
    public Element handle(Element content) {
        // TODO Auto-generated method stub
        HashMap<String, String> img_urls = new HashMap<String, String>();
        Elements imgs = content.select("img");
        for (int i = 0; i < imgs.size(); i++) {
            Element img = imgs.get(i);
            String src = img.absUrl("src");
            if (!src.startsWith("/"))
                try {
                    URL imgUrl = new URL(src);
                    String new_src = img_urls.get(src);
                    if (new_src == null) {
                        new_src = fetchImage(imgUrl);
                        img_urls.put(src, new_src);
                    }
                    img.attr("src", new_src);
                } catch (MalformedURLException e) {
                    img.remove();
                } catch (Exception e) {
                    e.printStackTrace();
                    img.remove();
                }
        }
        return content;
    }

    public static String fetchImage(URL imgUrl) {
        try {
            HttpURLConnection httpConnection = (HttpURLConnection) imgUrl.openConnection();
            httpConnection.setRequestMethod("GET");
            BufferedInputStream bis = new BufferedInputStream(httpConnection.getInputStream());

            String ext = FilenameUtils.getExtension(imgUrl.toString());
            if (!ImageUtils.isImage(ext)) {
                ext = "jpg";
            }
            String fileName = "/temp/" + System.currentTimeMillis() + "." + ext;
            String src = ServiceFactory.getFileService().uploadFile(fileName, bis);
            return src;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }
}
