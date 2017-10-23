'use strict';
angular.module('app') //

.factory('ServiceStatusDashboardChartsService', function($resource) {
	return $resource('api/home/charts/service/status');
})

.factory('CacheDashboardChartsService', function($resource) {
	return $resource('api/home/charts/service/cache');
})

.factory('TrafficDashboardChartsService', function($resource) {
	return $resource('api/home/charts/traffic');
})

.controller('HomeController', function($scope, PageHelper,
		ServiceStatusDashboardChartsService, CacheDashboardChartsService,
		TrafficDashboardChartsService, Nvd3Helper, $route) {
	// CONST
	$scope.helpTplUrl = 'js/modules/help/help-search.html';
	$scope.helpCharts = 'js/modules/help/help-charts.html';
	$scope.helpUrl = '#/help';
	$scope.errorUrl = '#/charts/error/count';
	$scope.cacheUrl = '#/charts/cache/count';
	$scope.repartitionUrl = '#/charts/service/count';
	$scope.trafficUrl = '#/charts/service/count';
	// CHARTS
	var chartsHeight = 250;
	var chartsWidth = 300;
	$scope.serviceOptions = {
		chart : {
			type : 'pieChart',
			height : chartsHeight,
			width : chartsWidth,
			x : function(d) {
				return d.key;
			},
			y : function(d) {
				return d.y;
			},
			showLabels : false,
			showLegend : true,
			labelThreshold : 0.01,
			labelSunbeamLayout : true,
			color : function(d, i) {
				return Nvd3Helper.colorStatus(d.key);
			},
			noData: Nvd3Helper.message_noData
		}
	};

	$scope.cacheOptions = {
		chart : {
			title : 'Cache',
			type : 'pieChart',
			height : chartsHeight,
			width : chartsWidth,
			donut : true,
			showLabels : false,
			showLegend : false,
			pie : Nvd3Helper.option_pieGauge,
			color : function(d, i) {
				if (d.key === 'TRUE') {
					return Nvd3Helper.colorStatus('CACHE');
				}
				return Nvd3Helper.colorStatus('UNKNOWN');
			},
			noData: Nvd3Helper.message_noData
		}
	};
	$scope.trafficOptions = {
		chart : {
			type : 'lineChart',
			height : chartsHeight,
			width : chartsWidth*2,
			isArea : true,
			xAxis : Nvd3Helper.axisFormat_Date,
			noData: Nvd3Helper.message_noData
		}
	};
	var filternames = [ 'appCode', 'serviceName', 'functionName', 'status',
			'fromDate', 'toDate' ];
	// ACTIONS
	$scope.search = function() {
		$scope.isLoading = true;
		var request = PageHelper.newRequestFromMdtable($scope.query, filternames);
		ServiceStatusDashboardChartsService.query(request, function(response) {
			$scope.serviceStatusData = response;
		});
		CacheDashboardChartsService.query(request, function(response) {
			$scope.cacheData = response;
		});
		TrafficDashboardChartsService.query(request, function(response) {
			$scope.trafficData = response;
			$scope.isLoading = false;
		});
	};
	$scope.init = function() {
		$scope.query = {
			filter : {
				fromDate : moment().subtract(1, 'hours'),
				toDate : moment()
			}
		};
	};
	// INIT
	$scope.init();
	$scope.search();
});