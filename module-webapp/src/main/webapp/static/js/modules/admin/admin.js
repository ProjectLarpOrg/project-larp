'use strict';
angular.module('app-admin', []) //

.controller('AdminController', function($scope, $route, $routeParams) {
	// CONST
	$scope.links = 'js/modules/admin/links.html';
	// INIT
	$scope.view = $route.current.viewUrl;
});