'use strict';
angular.module('app') //

.controller('HelpController', function($scope, $location, $anchorScroll, EnvService) {
	// CONST
	$scope.backUrl = '#/home';
	$scope.helpHome = 'js/modules/help/help-home.html';
	// ACTIONS
	$scope.scrollTo = function(id) {
		$location.hash(id);
		$anchorScroll();
	}
	/*
	// INIT
	EnvService.get(function(response) {
		$scope.buildVersion = response.buildVersion;
		$scope.buildTimestamp = response.buildTimestamp;
		$scope.supportUrl = response.supportUrl;
		$scope.teamUrl = 'http:// support';
	});
	*/
});
