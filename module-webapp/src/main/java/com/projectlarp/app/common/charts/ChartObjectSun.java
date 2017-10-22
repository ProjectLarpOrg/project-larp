package com.projectlarp.app.common.charts;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChartObjectSun {
	private String name;
	private List<ChartObjectSun> children;
	private Long size;
}
