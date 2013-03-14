/**
 * 
 */
package org.mspring.mlog.api.dbank;

import java.io.File;

import org.mspring.mlog.api.dbank.nsp.NSPClient;
import org.mspring.mlog.api.dbank.nsp.VFS;
import org.mspring.mlog.api.dbank.nsp.VFS.Result;
import org.mspring.mlog.api.dbank.nsp.VFSExt;
import org.mspring.mlog.api.dbank.nsp.support.common.NSPException;


/**
 * @author Gao Youbo
 * @since 2013-1-6
 * @Description 
 * @TODO 操作DBank的通用类
 */
public class DBankUtils {
    private static VFS vfs;
    private static DBankUtils utils;
    
    /**
     * 获取对象实例
     * @param session
     * @param secret
     * @return
     */
    public static DBankUtils getInstance(String session, String secret){
        if (utils == null) {
            NSPClient nsp = new NSPClient(session, secret);
            vfs = new VFS(nsp);
            utils = new DBankUtils();
        }
        return utils;
    }
    
    /**
     * 禁止通过new创建对象
     */
    private DBankUtils() {
    }
    
    /**
     * 删除文件
     * @param files
     * @return
     * @throws NSPException
     */
    public Result rm(String... files) throws NSPException{
        return vfs.rmfile(files, false, null);
    }
    
    /**
     * 上传文件
     * @param path 服务器上存放路径
     * @param file 待上传的文件
     * @param cname
     * @throws NSPException
     */
    public void mkfile(String path, File file, String cname) throws NSPException{
        VFSExt vfsExt = new VFSExt(vfs);
        Result result = vfsExt.uploadFile(path, file, cname);
        System.out.println(result);
    }
    
    public static void main(String[] args) throws NSPException {
        //getInstance("57918", "7Ux5YkRKNhai2qDn5plQpiC2R3eyG20c").mkfile("/test.jpg", new File("D:/1.jpg"), "test");
        getInstance("Uuug9cyuCnzKKua8kBYuuFt2CWTMwFAyek9ZyoBemL5mvBtp", "18a29691a91d267e6c69e9083422b43b").mkfile("/test", new File("D:/1.jpg"), "test");
    }
}
