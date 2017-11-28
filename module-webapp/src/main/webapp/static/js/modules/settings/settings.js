'use strict';
angular.module('app-settings', []) //

.controller('SettingsController', function($scope, $location, $anchorScroll) {
	// CONST
	$scope.links = 'js/modules/settings/links.html';
	$scope.account = 'js/modules/settings/account.html';
	$scope.preferences = 'js/modules/settings/preferences.html';
	// ACTIONS
	/* FIXME scrolling hide the navbar
	$scope.scrollTo = function(id) {
		$location.hash(id);
		$anchorScroll();
	}
	*/
})

.controller('AccountController', function($scope) {
	$scope.languages = ('en fr')
	.split(' ').map(function(state) {
		return {abbrev: state};  });
})

.controller('PreferencesController', function($scope) {
	$scope.themes = ('Lign Dark')
		.split(' ').map(function(state) {
			return {abbrev: state};  });
})

;