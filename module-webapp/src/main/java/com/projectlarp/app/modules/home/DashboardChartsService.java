package com.projectlarp.app.modules.home;

import static com.google.common.collect.Lists.transform;
import static com.projectlarp.app.common.charts.ChartRepositoryHelper.intervalPrecision;
import static com.projectlarp.app.common.search.SearchHelper.checkArguments;
import static com.projectlarp.app.common.search.SearchHelper.timestamp;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.projectlarp.app.common.charts.ChartObjectPieValue;
import com.projectlarp.app.common.charts.ChartObjectPoint;
import com.projectlarp.app.common.charts.ChartObjectSerie;
import com.projectlarp.app.common.charts.ChartRepositoryParametersFactory;

@RestController()
@RequestMapping("api/home/charts/")
@RequiredArgsConstructor
@NoArgsConstructor
public class DashboardChartsService {

	@NonNull
	@Autowired
	DashboardChartsRepository repository;

	@Value("${search.home.max.days:7}")
	String max;
	
	@RequestMapping("service/status")
	public List<ChartObjectPieValue> serviceStatus( //
			String fromDate, String toDate) throws ParseException {
		checkArguments(fromDate, toDate);
		return repository //
				.status(ChartRepositoryParametersFactory //
						.get(fromDate, toDate));
	}

	@RequestMapping("service/cache")
	public List<ChartObjectPieValue> cache( //
			String fromDate, String toDate) throws ParseException {
		checkArguments(fromDate, toDate);
		ArrayList<ChartObjectPieValue> arrayList = new ArrayList<ChartObjectPieValue>();
		arrayList.addAll(repository //
				.cache(ChartRepositoryParametersFactory //
						.get(fromDate, toDate)));
		arrayList.addAll(repository //
				.total(ChartRepositoryParametersFactory //
						.get(fromDate, toDate)));
		return arrayList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("traffic")
	public List<ChartObjectSerie> traffic(//
			String fromDate, String toDate) throws ParseException {
		checkArguments(fromDate, toDate);
		List<DashboardChartsResultTraffic> datas = repository.traffic(ChartRepositoryParametersFactory.get(fromDate, toDate));
		long precision = intervalPrecision(timestamp(fromDate), timestamp(toDate));
		return ImmutableList.of( //
				new ChartObjectSerie("TRAFFIC", transform(datas, countTime), precision));
	}

	Function<DashboardChartsResultTraffic, ChartObjectPoint> countTime = new Function<DashboardChartsResultTraffic, ChartObjectPoint>() {

		@Override
		public ChartObjectPoint apply(DashboardChartsResultTraffic input) {
			return new ChartObjectPoint( //
					input.getDt().getTime(), //
					input.getCount());
		}
	};
}
