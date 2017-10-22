'use strict';
angular.module('app') //

// https://material.angularjs.org/latest/demo/sidenav
.directive('appNavContent', function() {
	return {
		controller : function($scope, $timeout, $mdSidenav, $log, $location) {
		    $scope.close = function () {
		        $mdSidenav('left').close();
		    };
		},
		templateUrl : 'js/directives/nav-content.html'
	};
})

// https://material.angularjs.org/latest/demo/toolbar
// https://material.angularjs.org/latest/demo/sidenav
.directive('appNavToolbar', function() {
	return {
		controller : function($scope, $timeout, $mdSidenav, $log, $sce) {
		    $scope.toggleLeft = buildToggler('left');
		    
		    function buildToggler(navID) {
		      return function() {
		        $mdSidenav(navID).toggle();
		      }
		    }
		    
			// CONST
			$scope.locales = {
				'en' : 'EN',
				'fr' : 'FR'
			};
			// ACTIONS
			$scope.trustSrc = function(src) {
				return $sce.trustAsResourceUrl(src);
			}
			// INIT
			var originatorEv;
			$scope.openMenu = function($mdOpenMenu, ev) {
		      originatorEv = ev;
		      $mdOpenMenu(ev);
		    };
		},
		templateUrl : 'js/directives/nav-toolbar.html'
	};
})

;