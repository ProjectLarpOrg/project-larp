package com.projectlarp.app.common.search;

import java.util.ArrayList;
import java.util.List;

public class SpringRESTResponseEmbedded<T> {
	List<T> items = new ArrayList<T>();

	public SpringRESTResponseEmbedded(List<T> items) {
		this.items = items;
	}
}