package org.rimasu.cloister.client;

import com.google.web.bindery.requestfactory.shared.Receiver;

public class ProxyReciever<T> extends Receiver<T> {
	
	private ProxyTarget<T> target;

	public ProxyReciever(ProxyTarget<T> target)
	{
		this.target = target;
	}
	
	@Override
	public void onSuccess(T response) {
		target.setProxy(response);
	}

}
