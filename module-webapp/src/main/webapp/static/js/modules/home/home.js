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

.controller('HomeController', function($scope, $route) {
	// CONST
	$scope.helpTplUrl = 'js/modules/help/help-search.html';
	$scope.helpCharts = 'js/modules/help/help-charts.html';
	$scope.helpUrl = '#/help';
	$scope.errorUrl = '#/charts/error/count';
	$scope.cacheUrl = '#/charts/cache/count';
	$scope.repartitionUrl = '#/charts/service/count';
	$scope.trafficUrl = '#/charts/service/count';

	// INIT
});