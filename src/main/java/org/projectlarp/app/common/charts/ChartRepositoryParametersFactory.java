package org.projectlarp.app.common.charts;

import org.projectlarp.app.common.search.SearchHelper;

public class ChartRepositoryParametersFactory {
	
	public static ChartRepositoryParameters get(//
			String datemin, String datemax) {
		return new ChartRepositoryParameters(//
				SearchHelper.timestamp(datemin),//
				SearchHelper.timestamp(datemax));
	}

	public static ChartRepositoryParameters get(//
			String datemin, String datemax, //
			String appCode, String serviceName, String functionName) {
		appCode = (null == appCode) ? "" : appCode;
		serviceName = (null == serviceName) ? "" : serviceName;
		functionName = (null == functionName) ? "" : functionName;
		return new ChartRepositoryParameters(//
				SearchHelper.timestamp(datemin),//
				SearchHelper.timestamp(datemax),//
				//
				like(appCode),//
				like(serviceName),//
				like(functionName),//
				//
				10);
	}

	private static String like(String value) {
		return "%" + value + "%";
	}
	
}
