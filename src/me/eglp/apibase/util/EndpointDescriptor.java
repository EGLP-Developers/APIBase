package me.eglp.apibase.util;

import java.util.HashMap;
import java.util.Map;

public class EndpointDescriptor {
	
	private RequestMethod method;
	private String url;
	private Map<String, String> headers;
	private Map<String, String> dynamicHeaders;
	private Map<String, String> dynamicQueryParameters;
	private Map<String, String> queryParameters;
	private Map<String, String> postParameters;
	private Map<String, String> dynamicPostParameters;
	
	public EndpointDescriptor(RequestMethod method, String url,
			Map<String, String> headers, Map<String, String> dynamicHeaders,
			Map<String, String> queryParameters, Map<String, String> dynamicQueryParameters,
			Map<String, String> postParameters, Map<String, String> dynamicPostParameters) {
		this.method = method;
		this.url = url;
		this.headers = headers;
		this.dynamicHeaders = dynamicHeaders;
		this.dynamicQueryParameters = dynamicQueryParameters;
		this.queryParameters = queryParameters;
		this.postParameters = postParameters;
		this.dynamicPostParameters = dynamicPostParameters;
	}

	public RequestMethod getMethod() {
		return method;
	}

	public String getURL() {
		return url;
	}

	public String getURL(RequestParameters parameters) {
		String fURL = url;
		for(Map.Entry<String, String> p : parameters.getParameters().entrySet()) {
			fURL = fURL.replace("{" + p.getKey() + "}", p.getValue());
		}
		return fURL;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}
	
	public Map<String, String> getDynamicHeaders() {
		return dynamicHeaders;
	}
	
	public Map<String, String> getQueryParameters() {
		return queryParameters;
	}
	
	public Map<String, String> getDynamicQueryParameters() {
		return dynamicQueryParameters;
	}
	
	public Map<String, String> getPostParameters() {
		return postParameters;
	}
	
	public Map<String, String> getDynamicPostParameters() {
		return dynamicPostParameters;
	}
	
	public static Builder builder(RequestMethod method, String url) {
		return new Builder(method, url);
	}
	
	public static class Builder implements me.mrletsplay.mrcore.misc.Builder<EndpointDescriptor, Builder> {
		
		private RequestMethod method;
		private String url;
		private Map<String, String> headers;
		private Map<String, String> dynamicHeaders;
		private Map<String, String> dynamicQueryParameters;
		private Map<String, String> queryParameters;
		private Map<String, String> postParameters;
		private Map<String, String> dynamicPostParameters;
		
		public Builder(RequestMethod method, String url) {
			this.method = method;
			this.url = url;
			this.headers = new HashMap<>();
			this.dynamicHeaders = new HashMap<>();
			this.queryParameters = new HashMap<>();
			this.dynamicQueryParameters = new HashMap<>();
			this.postParameters = new HashMap<>();
			this.dynamicPostParameters = new HashMap<>();
		}
		
		public Builder header(String name, String value) {
			headers.put(name, value);
			return this;
		}
		
		public Builder dynamicHeader(String name, String parameter) {
			dynamicHeaders.put(name, parameter);
			return this;
		}
		
		public Builder query(String name, String value) {
			queryParameters.put(name, value);
			return this;
		}
		
		public Builder dynamicQuery(String name, String parameter) {
			dynamicQueryParameters.put(name, parameter);
			return this;
		}
		
		public Builder post(String name, String value) {
			postParameters.put(name, value);
			return this;
		}
		
		public Builder dynamicPost(String name, String parameter) {
			dynamicPostParameters.put(name, parameter);
			return this;
		}
		
		@Override
		public EndpointDescriptor create() throws IllegalStateException {
			if(method == null || url == null) throw new IllegalStateException("Method and url must be set");
			return new EndpointDescriptor(method, url, headers, dynamicHeaders, queryParameters, dynamicQueryParameters, postParameters, dynamicPostParameters);
		}
		
	}
	
}
