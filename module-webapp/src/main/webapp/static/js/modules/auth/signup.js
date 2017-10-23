'use strict';
angular.module('app') //

.controller('SignupController', function($scope, EnvService) {

	// INIT
	EnvService.get(function(response) {
		$scope.buildVersion = response.buildVersion;
		$scope.siteUrl = response.siteUrl;
	});
})

;