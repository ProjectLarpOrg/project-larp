package com.projectlarp.app.common.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpringRESTResponse<T> {
	SpringRESTResponseEmbedded<T> _embedded;
	SpringRESTResponsePage page;
}
