package com.projectlarp.app.modules.home;

import java.util.List;

import javax.sql.DataSource;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectlarp.app.common.charts.ChartObjectPieValue;
import com.projectlarp.app.common.charts.ChartRepositoryHelper;
import com.projectlarp.app.common.charts.ChartRepositoryParameters;

@Component
@RequiredArgsConstructor
@NoArgsConstructor
public class DashboardChartsRepository {

	@NonNull
	@Autowired
	DataSource ds;

	public List<ChartObjectPieValue> status(ChartRepositoryParameters p) {
		return ChartRepositoryHelper.query(ds,
				DashboardChartsRepositorySQL.QUERY_STATUS,
				ChartObjectPieValue.class, p);
	}

	public List<ChartObjectPieValue> cache(ChartRepositoryParameters p) {
		return ChartRepositoryHelper.query(ds,
				DashboardChartsRepositorySQL.QUERY_CACHE,
				ChartObjectPieValue.class, p);
	}

	public List<ChartObjectPieValue> total(ChartRepositoryParameters p) {
		return ChartRepositoryHelper.query(ds,
				DashboardChartsRepositorySQL.QUERY_TOTAL,
				ChartObjectPieValue.class, p);
	}

	public List<DashboardChartsResultCount> repartition(
			ChartRepositoryParameters p) {
		return ChartRepositoryHelper.query(ds,
				DashboardChartsRepositorySQL.QUERY_REPARTITION,
				DashboardChartsResultCount.class, p);
	}

	public List<DashboardChartsResultTraffic> traffic(
			ChartRepositoryParameters p) {
		return ChartRepositoryHelper.query(ds,
				DashboardChartsRepositorySQL.QUERY_TRAFFIC,
				DashboardChartsResultTraffic.class, p);
	}
}
