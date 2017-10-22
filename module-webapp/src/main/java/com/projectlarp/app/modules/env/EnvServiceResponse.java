package com.projectlarp.app.modules.env;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvServiceResponse {
	String buildVersion;
	String buildTimestamp;
	String siteUrl;
	String searchIntervalMaxDays;
	String searchFromdateMaxYears;
	String searchRefreshIntervalSeconds;
	String searchRefreshFromdateMinutes;
}
