'use strict';
angular.module('app-settings') //

.factory('ProfileService', function($resource) {
	return $resource('api/users/:userId/profil');
})

.controller('PreferencesController', function($scope, $routeParams, ProfileService) {
	// CONST
	var languages = 'en fr';
	var themes = 'Dark Light';
	// IMPL
	$scope.save = function(profile) {
		ProfileService.save(profile, function() {
			// SUCCESS
			$scope.profile = profile;
		});
	}
	// INIT
	$scope.languages = (languages)
		.split(' ').map(function(state) {
			return {abbrev: state};  });
	$scope.themes = (themes)
		.split(' ').map(function(state) {
			return {abbrev: state};  });
	
	if($routeParams.userId)
		ProfileService.get({userId : $routeParams.userId}, function(response) {
			$scope.profile = response;
		});
	else
		$scope.profile = {
			language: 'en',
			theme: 'Light'
		};
})

;