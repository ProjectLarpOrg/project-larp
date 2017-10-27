'use strict';
angular.module('app') //

.factory('AuthLogoutService', function($resource) {
	return $resource('api/auth/logout');
})

.controller('LogoutController', function($auth, $location) {
	
	if (!$auth.isAuthenticated()) { return; }
    
    $auth.logout()
      .then(function() {
        $location.path('#/');
      });
})

;