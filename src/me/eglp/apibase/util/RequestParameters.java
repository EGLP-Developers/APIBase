package me.eglp.apibase.util;

import java.util.HashMap;
import java.util.Map;

public class RequestParameters {
	
	private Map<String, String> parameters;
	
	public RequestParameters() {
		this.parameters = new HashMap<>();
	}
	
	public RequestParameters put(String key, String value) {
		parameters.put(key, value);
		return this;
	}
	
	public String get(String key) {
		return parameters.get(key);
	}
	
	public boolean contains(String key) {
		return parameters.containsKey(key);
	}
	
	public Map<String, String> getParameters() {
		return parameters;
	}

}
