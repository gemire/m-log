package org.mspring.mlog.api.dbank.nsp;

import java.util.Map;

import org.mspring.mlog.api.dbank.nsp.support.common.AssocArrayUtil;
import org.mspring.mlog.api.dbank.nsp.support.common.NSPException;

/**
 * dbank-sdk nsp.user<br/>
 * Desc:针对nsp.user.*服务的封装<br/>
 * @author <a href="mailto: 61606619@qq.com">Bruce Shen</a>
 * 2011 下午5:02:00
 */
public class user {

	private NSPClient userClient;

	/**
	 * 构造函数
	 * @param NSPClient nspClient
	 * @see AuthServices
	 */
	public user(NSPClient userClient) {
		this.userClient = userClient;
	}

	/**
	 * 获取用户信息。
	 * @param <b>attrs</b> 需要获取的用户属性，
	 * 			参见<a href='http://open.dbank.com/wikiNew/index.php?title=%E7%94%A8%E6%88%B7%E5%B1%9E%E6%80%A7'>用户属性</a>，默认返回 uid
	 * @return Map
	 * @throws NSPException 
	 */
	public Map<String, String> getInfo(String[] attrs) throws NSPException {
		Object obj = userClient.callService("nsp.user.getInfo", new Object[]{attrs});
		return (Map<String, String>) AssocArrayUtil.toObject(obj, false);
	}

	/**
	 * 更新用户信息
	 * @param <b>attrs</b> 需要设置的属性
	 * @return Boolean
	 * @throws NSPException 
	 */
	public Boolean update(Map<String,Object> attrs) throws NSPException {
		Object obj = userClient.callService("nsp.user.update", new Object[]{attrs});
		return (Boolean) AssocArrayUtil.toObject(obj, false);
	}

	/**
	 * 获取用户对应的账号
	 * @param type 帐号类型 0:全部 1:邮箱 2：手机
	 * @return Map
	 * @throws NSPException 
	 */
	public Map<String,String>[] getAccounts(Integer type) throws NSPException{
		Object obj = userClient.callService("nsp.user.getAccounts", new Object[]{type});
		return (Map<String,String>[]) AssocArrayUtil.toObject(obj, false);
	}
}
