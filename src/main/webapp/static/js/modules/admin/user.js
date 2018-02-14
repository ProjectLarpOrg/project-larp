'use strict';
angular.module('app-admin') //

.factory('UserSearchService', function($resource) {
	return $resource('api/users/search/filter');
})

.controller('UserListController', function($scope, UserSearchService, SearchTableHelper) {
	// CONST
	$scope.userUrl = '#/settings';
	var filternames = [ 'username'];
	var openUrl = '#/admin/user/:id';	
	// INIT
    $scope.selected = [];
	SearchTableHelper.initTable($scope);
	SearchTableHelper.initService($scope, UserSearchService, filternames, openUrl);
	SearchTableHelper.initSearch($scope, 'search');
})

.controller('UserDetailsController', function($scope, $routeParams, UserSearchService) {
	// CONST
	$scope.view = 'js/modules/settings/account.html';
})

;