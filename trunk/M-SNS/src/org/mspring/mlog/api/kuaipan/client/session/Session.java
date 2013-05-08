package org.mspring.mlog.api.kuaipan.client.session;

import org.mspring.mlog.api.kuaipan.client.exception.KuaipanAuthExpiredException;
import org.mspring.mlog.api.kuaipan.client.model.AccessToken;
import org.mspring.mlog.api.kuaipan.client.model.Consumer;
import org.mspring.mlog.api.kuaipan.client.model.RequestToken;
import org.mspring.mlog.api.kuaipan.client.model.TokenPair;

public abstract class Session {
	
	public Consumer consumer;
	public TokenPair token;
	public final String root;
	
	public Session(String key, String secret, Root root) {
		this.consumer = new Consumer(key, secret);
		this.token = null;
		this.root = root.toString();
	}
	
	public void setAuthToken(String key, String secret) {
		this.token = new AccessToken(key, secret);
	}
	
	public void setTempToken(String key, String secret) {
		this.token = new RequestToken(key, secret);
	}
	
	public boolean isAuth() { 
		if (this.token == null)
			return false;
		return (this.token instanceof AccessToken);
	}
	
	public void unAuth() {
		this.token = null;
	}
	
	public void assertAuth() throws KuaipanAuthExpiredException {
		if (! isAuth())
			throw new KuaipanAuthExpiredException();
	}
	
	public static enum Root {
		KUAIPAN, APP_FOLDER;
		public String toString() {
			return this.name().toLowerCase();
		}
	}
}
