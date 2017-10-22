package com.projectlarp.app.common.search;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpringRESTResponseEmbedded<T> {
	List<T> items = new ArrayList<T>();
}