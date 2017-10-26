'use strict';

// IE HACK
if (!String.prototype.startsWith) {
  String.prototype.startsWith = function (searchString, position) {
      position = position || 0;
      return this.substr(position, searchString.length) === searchString;
  };
}
if (!String.prototype.replaceAll) {
	String.prototype.replaceAll = function(search, replacement) {
	    var target = this;
	    return target.split(search).join(replacement);
	};
}


angular.module('app') //

.filter('maxLength', function() {
	return function(input, length) {
		if (input && input.length >= length) {
			return input.substring(0, length) + '...';
		} else {
			return input;
		}
	};
})

.factory('Env', function() {
	var env = {
		searchIntervalMaxDays: 7,
		searchFromdateMaxYears: 2,
		searchRefreshIntervalSeconds: 2,
		searchRefreshFromdateMinutes: 15,
		resubmitRefreshIntervalSeconds: 5
	};
	return {
		vars: function() {  
			return env;
		}
	}
})

.factory("GlobalsStorage", function() {
	// GLOBAL VARS
	var searchIntervalMaxDays = 7;
	var searchFromdateMaxYears = 2;
	// INIT
	return {
		searchMaxDays : function() {
			return searchIntervalMaxDays;
		}
	}
})

;