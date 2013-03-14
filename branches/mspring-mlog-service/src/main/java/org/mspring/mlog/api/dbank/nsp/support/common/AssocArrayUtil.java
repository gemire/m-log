package org.mspring.mlog.api.dbank.nsp.support.common;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.phprpc.util.AssocArray;
import org.phprpc.util.PHPSerializer;

public class AssocArrayUtil {

	/**
	 * 把 AssocArray 对象转成非 AssocArray对象，主要是去除 Map 和 List 
	 */
	public static Object toObject(Object obj, boolean keeyKey) {
		if(obj == null) {
			return null;
		}
		if(!(obj instanceof AssocArray)) {
			return obj;
		}
		AssocArray assocArray = (AssocArray) obj;
		if(assocArray.isEmpty()) {
			return Collections.EMPTY_LIST;
		}
		Map temp = assocArray.toHashMap();
		if(!keeyKey && isList(temp)) {
			List ret = new LinkedList(temp.values());
			for(int i = 0 ; i < ret.size(); i++) {
				if(ret.get(i) instanceof AssocArray) {
					ret.set(i, toObject((AssocArray) ret.get(i), keeyKey));
				}else if(ret.get(i) instanceof byte[]) {
					ret.set(i, new String((byte[]) ret.get(i)));
				}
			}
			return ret;
		} else {
			for(Object key : temp.keySet()) {
				if(temp.get(key) instanceof AssocArray) {
					temp.put(key, toObject((AssocArray) temp.get(key), keeyKey));
				}else if(temp.get(key) instanceof byte[]) {
					temp.put(key, new String((byte[]) temp.get(key)));
				}
			}
			return temp;
		}
	}
	
	/**
	 * 判断是否是List
	 */
	private static boolean isList(Map temp) {
		Set s = new TreeSet(temp.keySet());
		Object[] keyArray = s.toArray(new Object[]{});
		for(int i = 0; i < keyArray.length; i++ ) {
			if(keyArray[i] instanceof Number) {
				if(((Number) keyArray[i]).intValue() != i) {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param args
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws Exception {
		PHPSerializer phpSerializer = new PHPSerializer();
		String s = "a:35:{s:8:\"user.uid\";s:8:\"25392024\";s:26:\"profile.link_access_switch\";N;s:24:\"profile.dlink_white_list\";N;s:20:\"profile.dlink_switch\";N;s:22:\"product.fileuploadsize\";s:9:\"209715200\";s:11:\"user.status\";s:1:\"1\";s:13:\"user.username\";s:10:\"1437488603\";s:10:\"user.email\";s:17:\"1437488603@qq.com\";s:21:\"product.spacecapacity\";s:10:\"5368709120\";s:24:\"profile.spaceextcapacity\";s:10:\"1493172224\";s:25:\"profile.usedspacecapacity\";s:10:\"5706216157\";s:20:\"profile.orderenddate\";s:19:\"2012-03-25 00:00:17\";s:17:\"profile.productid\";s:1:\"0\";s:18:\"profile.extra_apps\";N;s:19:\"product.productname\";s:12:\"标准用户\";s:18:\"profile.dbank_skin\";s:7:\"default\";s:25:\"profile.dbank_avatar_type\";N;s:20:\"profile.dbank_avatar\";N;s:19:\"profile.paycapacity\";N;s:16:\"profile.interval\";N;s:21:\"profile.last_login_ip\";s:53:\"118.113.113.134||四川省杭州市||2012-08-07 20:09\";s:21:\"profile.dbank_outlink\";N;s:21:\"profile.dbank_netdisk\";N;s:26:\"profile.dbank_outlink_file\";N;s:27:\"profile.login_setter_status\";N;s:20:\"product.linkfilesize\";s:9:\"104857600\";s:15:\"product.package\";N;s:13:\"product.medal\";N;s:17:\"profile.card_type\";N;s:9:\"plugin.dl\";N;s:14:\"plugin.netdisk\";N;s:16:\"product.tryvip68\";N;s:17:\"product.tryvip990\";N;s:18:\"profile.pwd_switch\";N;s:21:\"profile.direct_switch\";N;}";
		List t  = new ArrayList();
		t.add(1);
		t.add("abc");
		t.add(new String[]{"123123","aaaaa"});
		
		s = new String(phpSerializer.serialize(t));
		System.out.println(s);
		
		byte[] ss = s.getBytes();
		Object obj = phpSerializer.unserialize(ss);
		if(obj instanceof AssocArray) {
			System.out.println(toObject((AssocArray)obj, true));
		}else {
			System.out.println(obj);
		}
		
		// 
	}

}
