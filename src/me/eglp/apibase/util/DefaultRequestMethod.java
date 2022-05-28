package me.eglp.apibase.util;

import java.util.function.BiFunction;

import me.mrletsplay.mrcore.http.HttpGet;
import me.mrletsplay.mrcore.http.HttpPost;
import me.mrletsplay.mrcore.http.HttpRequest;
import me.mrletsplay.mrcore.http.data.JSONObjectData;
import me.mrletsplay.mrcore.http.data.URLEncodedData;
import me.mrletsplay.mrcore.misc.FriendlyException;

public enum DefaultRequestMethod implements RequestMethod {
	
	GET((endpoint, parameters) -> {
		if(!endpoint.getDescriptor().getPostParameters().isEmpty()
				|| !endpoint.getDescriptor().getDynamicPostParameters().isEmpty()) throw new FriendlyException("GET request can't have POST parameters");
		HttpGet get = HttpRequest.createGet(endpoint.getDescriptor().getURL(parameters));
		initializeRequest(get, endpoint, parameters);
		return get;
	}),
	POST_URLENCODED((endpoint, parameters) -> {
		HttpPost post = HttpRequest.createPost(endpoint.getDescriptor().getURL(parameters));
		initializeRequest(post, endpoint, parameters);
		URLEncodedData data = new URLEncodedData();
		endpoint.getDescriptor().getPostParameters().forEach(data::set);
		endpoint.getDescriptor().getDynamicPostParameters().forEach((k, v) -> {
			if(!parameters.contains(v)) return;
			data.set(k, parameters.get(v));
		});
		post.setData(data);
		return post;
	}),
	POST_JSON((endpoint, parameters) -> {
		HttpPost post = HttpRequest.createPost(endpoint.getDescriptor().getURL(parameters));
		initializeRequest(post, endpoint, parameters);
		JSONObjectData data = new JSONObjectData();
		endpoint.getDescriptor().getPostParameters().forEach(data::set);
		endpoint.getDescriptor().getDynamicPostParameters().forEach((k, v) -> {
			if(!parameters.contains(v)) return;
			data.set(k, parameters.get(v));
		});
		return post;
	})
	;
	
	private final BiFunction<Endpoint, RequestParameters, HttpRequest> requestFunction;
	
	private DefaultRequestMethod(BiFunction<Endpoint, RequestParameters, HttpRequest> requestFunction) {
		this.requestFunction = requestFunction;
	}
	
	@Override
	public HttpRequest createRequest(Endpoint endpoint, RequestParameters parameters) {
		return requestFunction.apply(endpoint, parameters);
	}
	
	private static void initializeRequest(HttpRequest request, Endpoint endpoint, RequestParameters parameters) {
		endpoint.getDescriptor().getHeaders().forEach(request::setHeader);
		endpoint.getDescriptor().getDynamicHeaders().forEach((k, v) -> {
			if(!parameters.contains(v)) return;
			request.setHeader(k, parameters.get(v));
		});
		endpoint.getDescriptor().getQueryParameters().forEach(request::setQueryParameter);
		endpoint.getDescriptor().getDynamicQueryParameters().forEach((k, v) -> {
			if(!parameters.contains(v)) return;
			request.addQueryParameter(k, parameters.get(v));
		});
	}

}
