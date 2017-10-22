package com.projectlarp.app.common.charts;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChartRepositoryParameters {

	private Timestamp startDateMin;
	private Timestamp startDateMax;

	private String appCode;
	private String serviceName;
	private String functionName;

	private Integer n;

	public ChartRepositoryParameters(Timestamp startDateMin,
			Timestamp startDateMax) {
		this.startDateMin = startDateMin;
		this.startDateMax = startDateMax;
	}
}
