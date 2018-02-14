'use strict';
angular.module('app-commons') //

.factory("Nvd3Helper", function() {
	return {
		message_noData: "No Data. Try new search?",
		height1: 400,
		height2: 350,
		height_100: 600,
		width_50: 600,
		width_100: 850,
		width_wrap: 380,
		axisFormat_DateTime: {
			axisLabel : 'DateTime',
			tickFormat : function(d) {
				return d3.time.format('%d/%m %H:%M')(new Date(d))
			},
			rotateLabels : 30,
			showMaxMin : false
		},
		axisFormat_Date: {
			axisLabel : 'Date',
			tickFormat : function(d) {
				return d3.time.format('%d/%m')(new Date(d))
			},
			rotateLabels : 30,
			showMaxMin : false
		},
		axisFormat_Hour: {
			axisLabel : 'Hour(s)',
			tickFormat : function(d) {
				return (-d) + 'h';
			}
		},
		axisFormat_Count: {
			axisLabel : 'Count',
			tickFormat : function(d) {
				return d;
			}
		},
		axisFormat_Duration: {
			axisLabel : 'Response Time (ms)',
			tickFormat : function(d) {
				return d;
			},
		},
		option_pieGauge: {
			startAngle: function(d) {
				return d.startAngle / 3 - Math.PI / 3;
			},
			endAngle: function(d) {
				return d.endAngle / 3 - Math.PI / 3;
			}
		}
	}
});
