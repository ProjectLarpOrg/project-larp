package com.projectlarp.app.modules.home;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardChartsResultCount {

	private String appCode;
	private String serviceName;
	private String functionName;
	
	private Long size;
}
