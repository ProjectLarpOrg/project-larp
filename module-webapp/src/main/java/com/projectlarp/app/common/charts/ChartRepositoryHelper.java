package com.projectlarp.app.common.charts;

import static com.google.common.base.Preconditions.checkState;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class ChartRepositoryHelper {

	public static final String MAGIC = " TRUNC(t.startdate,'hh24') + (TRUNC(to_char(t.startdate,'mi')/:precision)*:precision)/24/60 ";

	public static final String CLAUSE_SERVICE = "" //
			+ "  AND upper(t.appCode)      LIKE upper(:appCode) " //
			+ "  AND upper(t.serviceName)  LIKE upper(:serviceName) " //
			+ "  AND upper(t.functionName) LIKE upper(:functionName) ";

	public static final String CLAUSE_FROM_TO = "" //
			+ "  AND t.startdate > :startDateMin " //
			+ "  AND t.startdate < :startDateMax ";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> List<T> query(DataSource dataSource, String sql,
			Class<T> rowClass, ChartRepositoryParameters p) {
		NamedParameterJdbcTemplate t = new NamedParameterJdbcTemplate(
				dataSource);
		SqlParameterSource np = new BeanPropertySqlParameterSource(p);
		String precision = String.valueOf(intervalPrecision(
				p.getStartDateMin(), p.getStartDateMax()));
		String nsql = sql.replaceAll(":precision", precision);
		return t.query(nsql, np, new BeanPropertyRowMapper(rowClass));
	}

	public static long intervalPrecision(Timestamp startDateMin,
			Timestamp startDateMax) {
		long minutes = intervalDuration(startDateMin, startDateMax);
		return minutes / INTERVAL_COUNT;
	}

	public static long intervalDuration(Timestamp startDateMin,
			Timestamp startDateMax) {
		long dt1 = startDateMin.getTime();
		long dt2 = startDateMax.getTime();
		long millis = dt2 - dt1;
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		return minutes;
	}

	private static int INTERVAL_COUNT = 10;

	public static List<ChartObjectPoint> sortByX(List<ChartObjectPoint> list) {
		List<ChartObjectPoint> list2 = new ArrayList<ChartObjectPoint>(list);
		Collections.sort(list2, new Comparator<ChartObjectPoint>() {
			@Override
			public int compare(ChartObjectPoint item1, ChartObjectPoint item2) {

				return item1.getX().compareTo(item2.getX());
			}
		});
		return list2;
	}
	
	public static List<ChartObjectPoint> completeXAxis(
			List<ChartObjectPoint> incomplete, List<ChartObjectPoint> reference) {
		List<ChartObjectPoint> complete = new ArrayList<ChartObjectPoint>();
		for (ChartObjectPoint ref : reference) {
			ChartObjectPoint completeItem = hasX(incomplete, ref.getX()) //
			? findByX(incomplete, ref.getX())
					: new ChartObjectPoint(ref.getX(), 0L);
			complete.add(completeItem);
		}
		checkState(complete.size() == reference.size());
		return complete;
	}
	
	public static ChartObjectPoint findByX(List<ChartObjectPoint> list, Long x) {
		for (ChartObjectPoint item : list) {
			if (x.equals(item.getX())) {
				return item;
			}
		}
		return null;
	}

	private static boolean hasX(List<ChartObjectPoint> list, Long x) {
		for (ChartObjectPoint item : list) {
			if (x.equals(item.getX())) {
				return true;
			}
		}
		return false;
	}
}
