'use strict';
angular.module('app') //

.directive('appToolbar', function() {
	return {
		restrict : 'E',
		transclude : true,
		templateUrl : 'js/directives/toolbar.html',
        link: function(scope, elem, attrs){
            scope.backurl = scope.$eval(attrs.backurl);
            scope.title = scope.$eval(attrs.title);
        }
	};
});