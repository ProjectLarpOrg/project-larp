'use strict';
angular.module('app-settings') //

.factory('UserService', function($resource) {
	return $resource('api/users/:userId');
})

.controller('AccountController', function($scope, $routeParams, UserService) {
	// IMPL
	$scope.save = function(user) {
		UserService.save(user, function() {
			// SUCCESS
			$scope.user = response;
		});
	}
	// INIT
	if($routeParams.userId)
		UserService.get({userId : $routeParams.userId}, function(response) {
			$scope.user = response;
		});
	else
		$scope.user = {};
})

;