'use strict';
angular.module('app') //

.directive('appHelpButton', function() {
	return {
		restrict : 'E',
		scope : {
			href : '@',
		},
		transclude : true,
		controller : function($scope, $element, $attrs, $mdDialog) {
			$scope.showAlert = function(ev) {
				$mdDialog.show({
				  	locals: {
				  		content: $attrs.href
				  	},
					controller : 'HelpDialogController',
					templateUrl : 'js/directives/help-dialog.html',
					parent : angular.element(document.body),
					targetEvent : ev,
					clickOutsideToClose : true
				});
			};
		},
		templateUrl : 'js/directives/help-html'
	};
})

.controller('HelpDialogController', function($scope, $mdDialog, content) {
	$scope.content = content;
	$scope.closeDialog = function() {
		$mdDialog.hide();
	};
})

;