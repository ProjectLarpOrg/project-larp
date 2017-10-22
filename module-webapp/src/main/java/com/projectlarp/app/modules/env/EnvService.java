package com.projectlarp.app.modules.env;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/env")
public class EnvService {
	@Autowired
	Environment env;

	@Value("${version:UNKNOWN}")	private String buildVersion;
	@Value("${timestamp:UNKNOWN}")	private String buildTimestamp;
	
	@Value("${site.url:UNKNOWN}")	private String siteUrl;
	
	@Value("${search.interval.max.days:7}")	private String searchIntervalMaxDays;
	@Value("${search.fromdate.max.years:2}")	private String searchFromdateMaxYears;
	@Value("${search.refresh.interval.seconds:2}")	private String searchRefreshIntervalSeconds;
	@Value("${search.refresh.fromdate.minutes:15}")	private String searchRefreshFromdateMinutes;

	@RequestMapping
	public EnvServiceResponse get() {
		return new EnvServiceResponse( //
				buildVersion, //
				buildTimestamp, //
				siteUrl, //
				searchIntervalMaxDays, //
				searchFromdateMaxYears, //
				searchRefreshIntervalSeconds, //
				searchRefreshFromdateMinutes);
	}

}
