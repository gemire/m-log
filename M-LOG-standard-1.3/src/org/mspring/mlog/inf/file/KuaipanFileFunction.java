/**
 * 
 */
package org.mspring.mlog.inf.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.mspring.mlog.api.kuaipan.KuaipanUtils;
import org.mspring.mlog.api.kuaipan.MKuaipanAPI;
import org.mspring.mlog.api.kuaipan.client.hook.CountingOutputStream;
import org.mspring.mlog.api.kuaipan.client.model.KuaipanFile;
import org.mspring.mlog.api.kuaipan.client.model.KuaipanHTTPResponse;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2013-1-7
 * @Description 
 * @TODO 
 */
public class KuaipanFileFunction extends AbstractFileFunction {
    
    private MKuaipanAPI api = KuaipanUtils.getApi();

    /* (non-Javadoc)
     * @see org.mspring.mlog.inf.standard.file.AbstractFileFunction#save(java.lang.String, java.io.File)
     */
    @Override
    public String save(String fileName, File file) {
        // TODO Auto-generated method stub
        try {
            InputStream inputStream = new FileInputStream(file);
            String url = save(fileName, inputStream, file.length());
            inputStream.close();
            return url;
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "";
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.inf.standard.file.AbstractFileFunction#save(java.lang.String, java.io.InputStream, long)
     */
    @Override
    public String save(String fileName, InputStream inputStream, long contentLength) {
        // TODO Auto-generated method stub
        try {
            KuaipanFile kpFile = api.uploadFile(fileName, inputStream, contentLength, false, null);
            //kpFile.path;
            
            CountingOutputStream os = new CountingOutputStream();
            KuaipanHTTPResponse resp = api.thumbnail(fileName, os, null);
            os.close();
            return resp.url.url;
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "";
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.inf.standard.file.AbstractFileFunction#saveBase64(java.lang.String, java.lang.String)
     */
    @Override
    public String saveBase64(String fileName, String base64) {
        // TODO Auto-generated method stub
        byte[] bytes = StringUtils.decodeBASE64(base64.getBytes());
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {// 调整异常数据
                bytes[i] += 256;
            }
        }
        InputStream inputStream = new ByteArrayInputStream(bytes);
        return save(fileName, inputStream, bytes.length);
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.inf.standard.file.AbstractFileFunction#delete(java.lang.String)
     */
    @Override
    public void delete(String filePath) {
        // TODO Auto-generated method stub
        
    }
    
    public static void main(String[] args) {
        String url = new KuaipanFileFunction().save("/test.jpg", new File("D:/1.jpg"));
        System.out.println(url);
    }

}
