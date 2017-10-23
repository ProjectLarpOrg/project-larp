'use strict';
angular.module('app') //

.controller('LoginController', function($scope, $auth, $location, $route) {
	
	// INIT
	if($auth.isAuthenticated()) {
		$location.url('/');
		$route.reload();
	}
	// DATA BINDING
	$scope.signin = 'js/modules/auth/signin.html';
	$scope.signup = 'js/modules/auth/signup.html';
})

;