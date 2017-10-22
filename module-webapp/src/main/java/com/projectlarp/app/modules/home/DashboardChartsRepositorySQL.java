package com.projectlarp.app.modules.home;

import static com.projectlarp.app.common.charts.ChartRepositoryHelper.CLAUSE_FROM_TO;
import static com.projectlarp.app.common.charts.ChartRepositoryHelper.CLAUSE_SERVICE;
import static com.projectlarp.app.common.charts.ChartRepositoryHelper.MAGIC;

public class DashboardChartsRepositorySQL {

	final static String QUERY_STATUS = "" //
			+ "SELECT " //
			+ " t.status AS key, " //
			+ " count(*) AS y " //
			+ " FROM service_flow t" //
			+ " WHERE 1=1 " //
			+ CLAUSE_FROM_TO //
			+ " GROUP BY t.status";

	static final String QUERY_CACHE = ""//
			+ "select "//
			+ " 'TRUE' AS key, " //
			+ " count (t.startdate) AS y"
			+ " FROM service_flow t"
			+ "    , service_step_flow s"
			+ " WHERE 1=1 " //
			+ CLAUSE_FROM_TO //
			+ " AND t.idservice = s.idservice"
			+ " AND s.stepcode = 'END_WITH_CACHE'" //
			+ " GROUP BY t.status ";

	static final String QUERY_TOTAL = ""//
			+ "select "//
			+ " 'FALSE' AS key, " + " count (*) AS y"
			+ " FROM service_flow t "
			+ " WHERE 1=1 " //
			+ CLAUSE_FROM_TO;

	static final String QUERY_REPARTITION = ""//
			+ "SELECT " //
			+ " t.appcode AS appcode," //
			+ " t.servicename AS servicename," //
			+ " t.functionname AS functionname," //
			+ " COUNT(*) AS \"size\" " //
			+ " FROM service_flow t " //
			+ " WHERE 1=1 " //
			+ CLAUSE_SERVICE //
			+ CLAUSE_FROM_TO //
			+ " GROUP BY t.appcode, t.servicename, t.functionname ";
	
	static final String QUERY_TRAFFIC = ""//
			+ "SELECT " //
			+ "  " + MAGIC + " AS dt, " //
			+ "  COUNT(t.duration) AS count " //
			+ " FROM service_flow t " //
			+ " WHERE 1=1 " //
			+ CLAUSE_FROM_TO //
			+ " GROUP BY " + MAGIC //
			+ " ORDER BY dt DESC ";
}
