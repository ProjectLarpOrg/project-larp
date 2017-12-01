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

;