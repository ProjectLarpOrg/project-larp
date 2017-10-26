'use strict';
angular.module('app') //

.controller('HelpController', function($scope, $location, $anchorScroll) {
	// CONST
	$scope.backUrl = '#/home';
	$scope.helpHome = 'js/modules/help/help-home.html';
	// ACTIONS
	$scope.scrollTo = function(id) {
		$location.hash(id);
		$anchorScroll();
	}
});
