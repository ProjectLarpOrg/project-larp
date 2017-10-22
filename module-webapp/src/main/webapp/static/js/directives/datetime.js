'use strict';
angular.module('app') //

.directive('appSearchDatetimeInterval', function() {	
	return {
		scope : {
			dates: '='
		},
		controller : function($scope, $element, $attrs, Env) {		
			// CONST
			$scope.pattern = 'YYYY/MM/DD HH:mm:ss';
			var searchIntervalMaxDays = Env.vars().searchIntervalMaxDays;
			var searchFromdateMaxYears = Env.vars().searchFromdateMaxYears;
			// ACTIONS
		    $scope.changeDate = function(modelName, newDate) {
		        if (modelName === 'dates.fromDate') {
		        	var testDate = newDate;
		        	if(newDate.isBefore($scope.dates.fromMin))
		        		testDate = $scope.dates.fromMin;
		        	if(newDate.isAfter($scope.dates.fromMax))
		        		testDate = $scope.dates.fromMax;
		        	$scope.dates.fromDate = moment(testDate).format($scope.pattern);
		        	$scope.dates.toDate = moment(testDate).add(1, 'hours').format($scope.pattern);
		        	$scope.changeMinMax('dates.toDate', newDate);
		        }
		        if (modelName === 'dates.toDate') {
		        	var testDate = newDate;
		        	if(newDate.isBefore($scope.dates.toMin))
		        		testDate = $scope.dates.toMin;
		        	if(newDate.isAfter($scope.dates.toMax))
		        		testDate = $scope.dates.toMax;
		        	$scope.dates.toDate = moment(testDate).format($scope.pattern);
		        }
		    };
		    $scope.changeMinMax = function (modelName, newValue) {
			    var minDate = moment(newValue);
			    var maxDate = moment(newValue).add(searchIntervalMaxDays, 'days');
	        	if(moment(maxDate).isAfter()) {
	        		maxDate = moment();
	        	}
		        var values = {
		          minDate: minDate,
		          maxDate: maxDate,
		        }
		        if (modelName === 'dates.toDate') {
		          $scope.dates.toMin = minDate;
		          $scope.dates.toMax = maxDate;
		          $scope.$broadcast('pickerUpdate', ['pickerToDate'], values);
		        }
		        
		        $scope.$broadcast('pickerUpdate', ['pickerFromDate'], {
		          minDate: $scope.dates.fromMin,
		          maxDate: $scope.dates.fromMax,
		        });
		    }
			// INIT
		    $scope.dates.fromMin = moment().add(-searchFromdateMaxYears, 'y');
		    $scope.dates.fromMax = moment();
		},
		templateUrl : 'js/directives/datetime-interval.html'
	}
})

.directive('appSearchDatetimePeriods', function() {
	return {
		restrict : 'E',
		scope : {
			dates : '=',
			execute : '&'
		},
		controller : function($scope, $element, $attrs, GlobalsStorage,
				$mdDialog, Env, $interval) {
			// CONST
			$scope.refreshIntervalSecond = Env.vars().searchRefreshIntervalSeconds;
			$scope.refreshFromdateMinutes = Env.vars().searchRefreshFromdateMinutes;
			$scope.pattern = 'YYYY/MM/DD HH:mm:ss';
			var refresh;
			// INIT AUTORELOAD
			function startRefresh() {
				$scope.selected = [];
				if ( angular.isDefined(refresh) ) return;
				refresh = $interval(function(){
					$scope.dates.fromDate = moment().subtract($scope.refreshFromdateMinutes, 'minutes').format($scope.pattern);
					$scope.dates.toDate   = moment().format($scope.pattern);
					$scope.execute();
				}, $scope.refreshIntervalSecond*1000);
			}
			function stopRefresh() {
				if( angular.isDefined(refresh)) {
		            $interval.cancel(refresh);
		            refresh = undefined;
				}
			}
			$scope.$on('$destroy', function() {
		        stopRefresh(); // Make sure that the interval is destroyed too
		      });
			$scope.isAuto = function() {
				return angular.isDefined(refresh);
			};
			// ACTIONS
			$scope.searchAuto = function() {
				if(angular.isDefined(refresh))
			        stopRefresh();
				else
					startRefresh();
			};
			$scope.search1 = function() {
		        stopRefresh();
		        $scope.dates.fromDate = moment().subtract(1, 'hours').format($scope.pattern);
		        $scope.dates.toDate   = moment().format($scope.pattern);
				$scope.execute();
			};
			$scope.search2 = function() {
		        stopRefresh();
		        $scope.dates.fromDate = moment().subtract(1, 'days').format($scope.pattern);
		        $scope.dates.toDate   = moment().format($scope.pattern);
				$scope.execute();
			};
			$scope.search3 = function() {
		        stopRefresh();
		        $scope.dates.fromDate = moment().subtract(3, 'days').format($scope.pattern);
		        $scope.dates.toDate   = moment().format($scope.pattern);
				$scope.execute();
			};
		},
		templateUrl: 'js/directives/datetime-periods.html'
	};
})

;