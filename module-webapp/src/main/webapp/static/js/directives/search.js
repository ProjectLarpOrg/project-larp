'use strict';

angular.module('app') //

.directive('appSearchEmpty', function() {
	return {
		templateUrl : 'js/directives/search-empty.html'
	};
})

.directive('appSearch', function() {
	return {
		restrict : 'E',
		scope : {
			search : '&',
			clear : '&'
		},
		transclude : true,
		templateUrl : 'js/directives/search.html'
	};
})

.directive('appSearchService', function() {
	return {
		restrict : 'E',
		scope : {
			appcode : '=',
			servicename : '=',
			functionname : '='
		},
		template : 
' <div layout="row" layout-wrap>'+
'	<md-input-container class="md-block" flex="none">'+
'		<label>Application</label>'+
'		<input ng-model="appcode">'+
'	</md-input-container>'+
'	<md-input-container class="md-block" flex="none">'+
'		<label>Service</label>'+
'		<input ng-model="servicename">'+
'	</md-input-container>'+
'	<md-input-container class="md-block" flex="none">'+
'		<label>Function</label>'+
'		<input ng-model="functionname">'+
'	</md-input-container>'+
'</div>'
	};
})

.directive('appSearchStatus', function() {
	return {
		scope : {
			status : '='
		},
		controller : function($scope, $element, $attrs) {
			$scope.statusList = [ //
			null, //
			{
				name : 'Active',
				value : 'IN_PROGRESS'
			}, {
				name : 'Completed',
				value : 'COMPLETE'
			}, {
				name : 'Error',
				value : 'ERROR'
			} ];
		},
		template : 
'<div layout="row">'+
'	<md-input-container class="md-block" flex="nogrow">'+
'		<label>Status</label>'+
'		<md-select ng-model="status">'+
'			<md-option ng-repeat="i in statusList" value="{{::i.value}}">'+
'				{{::i.name}}'+
'			</md-option>'+
'		</md-select>'+
'	</md-input-container>'+
'</div>'
	};
})

;

