package org.projectlarp.app.common.charts;

import java.sql.Timestamp;

public class ChartRepositoryParameters {

	public Timestamp startDateMin;
	public Timestamp startDateMax;

	public String appCode;
	public String serviceName;
	public String functionName;

	public Integer n;

	public ChartRepositoryParameters(Timestamp startDateMin, Timestamp startDateMax) {
		this.startDateMin = startDateMin;
		this.startDateMax = startDateMax;
	}

	public ChartRepositoryParameters(Timestamp startDateMin, Timestamp startDateMax, String appCode, String serviceName,
			String functionName, Integer n) {
		super();
		this.startDateMin = startDateMin;
		this.startDateMax = startDateMax;
		this.appCode = appCode;
		this.serviceName = serviceName;
		this.functionName = functionName;
		this.n = n;
	}

}
