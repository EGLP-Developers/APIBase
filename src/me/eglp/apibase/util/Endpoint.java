package me.eglp.apibase.util;

import me.mrletsplay.mrcore.http.HttpRequest;

public interface Endpoint {
	
	public EndpointDescriptor getDescriptor();
	
	public default HttpRequest createRequest(RequestParameters parameters) {
		return getDescriptor().getMethod().createRequest(this, parameters);
	}
	
}
