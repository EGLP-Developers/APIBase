package me.eglp.apibase.util;

import me.mrletsplay.mrcore.http.HttpRequest;

public interface RequestMethod {
	
	public HttpRequest createRequest(Endpoint endpoint, RequestParameters parameters);

}
