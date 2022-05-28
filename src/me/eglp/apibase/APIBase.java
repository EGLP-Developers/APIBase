package me.eglp.apibase;

import me.eglp.apibase.util.Endpoint;
import me.eglp.apibase.util.RequestParameters;
import me.mrletsplay.mrcore.http.HttpRequest;
import me.mrletsplay.mrcore.http.HttpResult;

public abstract class APIBase {
	
	public void onRequest(Endpoint endpoint, RequestParameters parameters, HttpRequest request) {
		// Can be overridden to implement extra behavior, like authorization
	}
	
	public void onRequestResult(Endpoint endpoint, RequestParameters parameters, HttpRequest request, HttpResult result) {
		// Can be overridden to implement extra behavior, like rate limiting based on response headers
	}
	
	public HttpResult makeRequest(Endpoint endpoint, RequestParameters parameters) {
		HttpRequest r = endpoint.createRequest(parameters);
		onRequest(endpoint, parameters, r);
		HttpResult res = r.execute();
		onRequestResult(endpoint, parameters, r, res);
		return res;
	}

}
