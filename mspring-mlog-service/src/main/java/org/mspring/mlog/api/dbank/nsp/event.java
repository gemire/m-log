package org.mspring.mlog.api.dbank.nsp;

import java.util.Map;

import org.mspring.mlog.api.dbank.nsp.support.common.AssocArrayUtil;
import org.mspring.mlog.api.dbank.nsp.support.common.NSPException;

/**
 * dbank-sdk nsp.event<br/>
 * Desc:针对nsp.event.*服务的封装<br/>
 * 
 * @author <a href="mailto: 61606619@qq.com">Bruce Shen</a> 2011 下午5:02:00
 */
public class event {

	private NSPClient userClient;

	/**
	 * @param client
	 *            NSP Client
	 * @see AuthServices
	 */
	public event(NSPClient client) {
		this.userClient = client;
	}

	/**
	 * 订阅
	 * @param event_type 事件类型
	 * @param filter 事件过滤条件
	 * @param endpoint endpoint属性
	 * @return 
	 * @throws NSPException
	 */
	public Object subscribe(String event_type, Map<String,String> filter, Map<String,String> endpoint ) throws NSPException{
		Object obj = userClient.callService("nsp.event.subscribe", new Object[]{event_type, filter, endpoint});
		return AssocArrayUtil.toObject(obj, false);
	}

	/**
	 * 取消订阅
	 * @param event_type 取消的事件类型
	 * @param filter 事件过滤条件
	 * @param endpoint endpoint属性
	 * @return
	 * @throws NSPException
	 */
	public Object unsubscribe(String event_type, Map<String,String> filter, Map<String,String> endpoint ) throws NSPException{
		Object obj = userClient.callService("nsp.event.unsubscribe", new Object[]{event_type, filter, endpoint});
		return AssocArrayUtil.toObject(obj, false);
	}

	/**
	 * 发送event消息
	 * @param event_typ 事件类型
	 * @param params 事件内容
	 * @return
	 * @throws NSPException
	 */
	public Object send(String event_type, Map<String, Object> params) throws NSPException{
		Object obj = userClient.callService("nsp.event.send", new Object[]{event_type, params});
		return AssocArrayUtil.toObject(obj, false);
	}
}
