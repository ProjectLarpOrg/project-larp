'use strict';
angular.module('app') //

.directive('appCard', function() {
	return {
		restrict : 'E',
		transclude : true,
		scope : {
			title : '@?'
		},
		template : 
'<div style="height: 100% !important; margin-right: 20px !important; margin-bottom: 20px !important;">'+
'	<div class="md-title">{{ ::title | uppercase }}</div>'+
'	<div ng-transclude></div>'+
'</div>'
	};
})

.directive('appCardContainer', function() {
	return {
		restrict : 'E',
		transclude : true,
		template : 
'<div class="md-padding" layout="row" layout-wrap ng-transclude layout-align="space stretch"></div>'
	};
});