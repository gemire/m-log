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
public class Analysis {

	private NSPClient userClient;
	
	/**
     * @param client : 
     *            NSP的客户端对象
     * @see NSPClient
     */
    public Analysis(NSPClient client) {
        this.userClient = client;
    }
    
    /**
     * 存放数据
     * @param name 数据名称
     * @param data 数据
     * @return
     * @throws NSPException 
     */
    public Map put(String name, Map<String, Map<String, Object>> data) throws NSPException {
    	Object obj = userClient.callService("nsp.developer.analysis.put", new Object[] { name, data});
    	return (Map) AssocArrayUtil.toObject(obj, false);
    }
    
    /**
     * @param name 查询数据到名称
     * @param keys 返回到字段
     * @param query <a href="http://www.elasticsearch.org/guide/reference/query-dsl/">查询语句</a>
     * @param option 支持 页号及每页数量
     * @return
     * @throws NSPException
     */
    public Map get(String name, String[] keys, String query, Map<String, Object> option) throws NSPException {
    	Object obj = userClient.callService("nsp.developer.analysis.get", new Object[] { name, keys, query, option});
    	return (Map) AssocArrayUtil.toObject(obj, false);
    }

}
