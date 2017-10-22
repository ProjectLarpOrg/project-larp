'use strict';
angular.module('app') //

.factory('AuthLogoutService', function($resource) {
	return $resource('api/auth/logout');
})

.controller('LogoutController', function($scope, $rootScope, $auth, $location, $route, AuthLogoutService) {
	// EXPIRE
	if($auth.isAuthenticated()) {
		var token = $auth.getToken();
		
		// CLIENT SIDE
	    $auth.logout().then(function() {
			$rootScope.notifyLogout();
		});
		// SERVER SIDE
		AuthLogoutService.save(token, function() {
		});
	}
	// REDIRECT
	$location.url('/auth/login');
	$route.reload();
})

;