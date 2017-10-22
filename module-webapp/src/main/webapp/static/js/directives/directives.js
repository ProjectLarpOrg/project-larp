'use strict';
angular.module('app') //

.directive('appLoading', function() {
	return {
		templateUrl : 'js/directives/loading-progress.html'
	};
})

.directive('appButtonOpen', function() {
	return {
		restrict : 'E',
		scope : {
			href : '='
		},
		template : 
'<md-button class="md-raised" href="{{::href}}">'+
'  <md-icon  md-svg-icon="action:open_in_new"></md-icon>'+
'  {{ \'open\' | translate }}'+
'</md-button>'
	};
})

.directive('appButtonDownload', function() {
	return {
		restrict : 'E',
		scope : {
			active : '=',
			url : '&'
		},
		transclude : true,
		controller : function($scope, $element, $attrs) {
			$scope.updateDownloadUrl = function() {
				$scope.downloadUrl = $scope.url();
			}
			if (!$attrs.disabled) {
				$scope.disabled = function(id) {
					return false;
				};
			}
		},
		templateUrl : 'js/directives/download-html'
	};
})

;
