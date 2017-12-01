'use strict';
angular.module('app') //

/**
 * https://material.angularjs.org/latest/demo/toolbar
 */
.directive('appNavbar', function() {
	return {
		controller : function($scope, $timeout, $mdSidenav, $log, $sce, $auth, $mdMenu) {
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
			var originatorEv;
			
			// ACTIONS
			$scope.openMenu = function($mdMenu, ev) {
		      originatorEv = ev;
		      $mdMenu.open(ev);
		    };
			$scope.trustSrc = function(src) {
				return $sce.trustAsResourceUrl(src);
			};
			$scope.isAuthenticated = function() {
		      return $auth.isAuthenticated();
		    };
		    
			// INIT
		    $scope.$on('loggedin', function (event, data) {
		    	console.log($auth.getToken());
			});
		    
			if ($auth.isAuthenticated()) { 
				$scope.$emit('loggedin', '');
			}
		},
		templateUrl : 'js/directives/navbar.html'
	};
})

;