package com.projectlarp.app.common.search;

public class SpringRESTResponse<T> {
	SpringRESTResponseEmbedded<T> _embedded;
	SpringRESTResponsePage page;

	public SpringRESTResponse(SpringRESTResponseEmbedded<T> _embedded, SpringRESTResponsePage page) {
		this._embedded = _embedded;
		this.page = page;
	}
}
