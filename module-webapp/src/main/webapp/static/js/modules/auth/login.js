'use strict';
angular.module('app-auth', []) //

.controller('LoginController', function($scope, $auth, $location, $route) {
	// INIT
	if($auth.isAuthenticated()) {
		$location.url('/');
		$route.reload();
	}
	// RESOURCES
	$scope.signin = 'js/modules/auth/signin.html';
	$scope.signup = 'js/modules/auth/signup.html';
})

;