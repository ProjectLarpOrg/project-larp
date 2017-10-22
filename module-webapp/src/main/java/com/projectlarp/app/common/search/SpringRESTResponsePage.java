package com.projectlarp.app.common.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpringRESTResponsePage {
	int size;
	long totalElements;
	int totalPages;
	int number;
}
