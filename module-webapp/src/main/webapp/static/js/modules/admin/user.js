'use strict';
angular.module('app-admin') //

.factory('UserSearchService', function($resource) {
	return $resource('api/users/search/filter');
})
.factory('UserService', function($resource) {
	return $resource('api/users/:userId');
})

.controller('UserListController', function($scope, UserSearchService, SearchTableHelper) {
	// CONST
	var filternames = [ 'username'];
	var openUrl = '#/admin/user/:id';	
	// INIT
    $scope.selected = [];
	SearchTableHelper.initTable($scope);
	SearchTableHelper.initService($scope, UserSearchService, filternames, openUrl);
	SearchTableHelper.initSearch($scope, 'search');
})

.controller('UserDetailsController', function($scope, $routeParams, UserService, UserSearchService) {
	// CONST

	// IMPL
	$scope.save = function() {
		UserService.save($scope.user, function() {
			// SUCCESS
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