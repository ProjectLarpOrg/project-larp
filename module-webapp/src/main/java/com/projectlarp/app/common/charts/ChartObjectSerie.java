package com.projectlarp.app.common.charts;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ChartObjectSerie<T> {
	@NonNull
	private String key;
	@NonNull
	private List<T> values;
	private Long precision; // OPTIONNAL
}
	