package me.eglp.apibase.util;

public enum Test implements Endpoint {
	
	E(EndpointDescriptor.builder(DefaultRequestMethod.GET, "/{a}/{b}")
			.header("H", "Q")
			.dynamicHeader("H", "qww")
			.create());
	
	private Test(EndpointDescriptor e) {
	}
	
	@Override
	public EndpointDescriptor getDescriptor() {
		return null;
	}

}
