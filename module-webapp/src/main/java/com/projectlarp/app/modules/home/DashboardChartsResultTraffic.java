package com.projectlarp.app.modules.home;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardChartsResultTraffic {

	private Date dt;
	private Long count;
}
