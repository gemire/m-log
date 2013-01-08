package org.mspring.mlog.api.dbank.nsp;

import java.util.Map;

import org.mspring.mlog.api.dbank.nsp.support.common.AssocArrayUtil;
import org.mspring.mlog.api.dbank.nsp.support.common.NSPException;

/**
 * dbank-sdk nsp.user<br/>
 * Desc:个人网络硬盘直链<br/>
 * @author <a href="mailto: 61606619@qq.com">Bruce Shen</a>
 * 2012-7-4
 */
public class Link {

	private NSPClient userClient;
	
	/**
     * @param client : 
     *            NSP的客户端对象
     * @see NSPClient
     */
    public Link(NSPClient client) {
        this.userClient = client;
    }
    
    /**
     * 获取直链
     * @param path 生成直链的文件路径地址，文件需要存放在 /我的网盘/我的应用/PublicFiles/ 目录下
     * @param clientIp 客户端IP地址
     * @return
     * @throws NSPException 
     */
    public Map<String, String> getDirectUrl(String path, String clientIp) throws NSPException {
    	Object obj = userClient.callService("nsp.vfs.link.getDirectUrl", new Object[] { path, clientIp});
        return (Map<String, String>) AssocArrayUtil.toObject(obj, false);
    }

}
