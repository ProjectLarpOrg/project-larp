'use strict';
angular.module('app') //

.directive('appFormField', function() {
	return {
		restrict : 'E',
		transclude : true,
		scope : {
			label : '@'
		},
		templateUrl : 'js/directives/form-field.html'
	};
})

.directive('appFormStatus', function() {
	return {
		scope : {
			value : '@'
		},
		link : function(scope, element, attrs) {
			if(scope.value in appStatus) {
				var status = appStatus[scope.value];
				scope.icon = status.icon;
				scope.cssClass = status.cssClass;
				scope.tooltip = status.tooltip;
			} else {
				var status = appStatus['UNKNOWN'];
				scope.icon = status.icon;
				scope.cssClass = status.cssClass;
				scope.tooltip = (status.tooltip +': '+ scope.value);
			}
		},
		template:
'<md-icon md-svg-icon="{{::icon}}" class="{{::cssClass}}">'+
' <md-tooltip> {{::tooltip}} </md-tooltip> '+
'</md-icon>'
	};
})

.directive('prettyprint', function() {

    return {
        restrict: 'C',
        link: function postLink(scope, element, attrs) {
            element.html(
            		  prettyPrintOne(
            				  vkbeautify.xml(
            								  scope.dom
            				  ),
            				  'xml', false
            		  )
            );
        }
    };
})

;