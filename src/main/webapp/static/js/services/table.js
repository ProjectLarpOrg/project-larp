'use strict';
angular.module('app-spring', []) //

.factory("SearchDatasHelper", function(CriteriaStorage) {
	return {
		initService : function($scope, callServer) {
			$scope.callServer = callServer;
		},
		initSearch : function($scope, criteriaStorageId) {
			// ACTIONS
			$scope.init = function() {
				$scope.pattern = 'YYYY/MM/DD HH:mm:ss';
				$scope.query = {
					filter : {
						fromDate : moment().subtract(1, 'hours').format($scope.pattern),
						toDate :   moment().format($scope.pattern)
					},
					order : '-startDate',
				};
				$scope.callServer();
			};
			$scope.search = function() {
				CriteriaStorage.save(criteriaStorageId, $scope.query);
				$scope.callServer();
			};
			// INIT
			if( CriteriaStorage.has(criteriaStorageId) ) {
				$scope.query = CriteriaStorage.load(criteriaStorageId); 
				$scope.callServer();
			} else {
				$scope.init();
			}
		}
	};
})

.factory("SearchTableHelper", function(PageHelper, CriteriaStorage, $location) {
	function addUrls(items, itemUrlTpl) {
		for (var i = 0; i < items.length; ++i) {
			items[i].url = itemUrlTpl.replace(':id', items[i].id);
		}
	};
	return {
		initService : function($scope, searchService, filternames, itemUrlTpl, successFunction) {
			$scope.isLoading = false;
			$scope.callServer = function() {
				$scope.isLoading = true;
				var request = PageHelper.newRequestFromMdtable($scope.query,
						filternames);
				return searchService.get(request, function(response) {
					$scope.data = PageHelper.newResponseForMdtable(response);
					addUrls($scope.data.items, itemUrlTpl);
					if(successFunction) {
						successFunction(response, $scope.data);
					}
					$scope.isLoading = false;
				}, function(rejection) {
					alert('Datas cannot be retrieved. '
							+ 'Please try again later or contact your admin.');
				});
			};
		},
		initTable : function($scope) {
			// INIT
			$scope.data = {
				item : [],
				total : 0
			}
			$scope.tableLimiteOptions = [10, 20, 50, 100];
		},
		initDownload : function($scope, url, filternames) {
			$scope.isNotDownloadable = function() {
				var notNull = ($scope.data.total !== null);
				var notEmpty = ($scope.data.total > 0);
				var downloadable = notNull && notEmpty;
				return !(downloadable);
			}
			// ACTIONS
			$scope.getCsvDownloadUrl = function() {
				var request = PageHelper.newRequestFromMdtable($scope.query,
						filternames);
				var params = "?csv=true";
				for(var propertyName in request) {
					var string = String(request[propertyName]);
					params += ("&" + propertyName + "=" + string.replaceAll('%','%25'));
				}
				return url + params;
			};
		},
		initSearch : function($scope, criteriaStorageId) {
			// ACTIONS
			$scope.init = function() {
				$scope.pattern = 'YYYY/MM/DD HH:mm:ss';
				$scope.query = {
					filter : {
						fromDate : moment().subtract(1, 'hours').format($scope.pattern),
						toDate :   moment().format($scope.pattern)
					},
					order : '-startDate',
					limit : 10,
					page : 1
				};
				$scope.deferred = $scope.callServer().$promise;
			};
			$scope.search = function() {
				CriteriaStorage.save(criteriaStorageId, $scope.query);
				$scope.query.page = 1;
				var promise = $scope.callServer().$promise;
				$scope.deferred = promise;
				return promise;
			};
			$scope.searchPagination = function() {
				CriteriaStorage.save(criteriaStorageId, $scope.query);
				var promise = $scope.callServer().$promise;
				$scope.deferred = promise;
				return promise;
			};
			// INIT
			if( CriteriaStorage.has(criteriaStorageId) ) {
				$scope.query = CriteriaStorage.load(criteriaStorageId); 
				$scope.deferred = $scope.callServer().$promise;
			} else {
				$scope.init();
			}
		},
		addUrls : addUrls
	}
})

.factory("CriteriaStorage", function() {
	var criterias = {};
	return {
		save : function(id, criteria) {
			criterias[id] = criteria;
		},
		has : function(id) {
			return !!criterias[id];
		},
		load : function(id) {
			var cri = criterias[id];
			// DEFAULT NO CONFLICT
			cri.order = '-startDate';
			cri.page = 1;
			cri.limit = 10;
			return criterias[id];
		}
	}
})

.factory("PageHelper", function($filter) {
	function formatLike(field) {
		return '%' + field + '%';
	}
	function formatDate(field) {
		var utc=moment(field).format("YYYY-MM-DDTHH:mm:ss") + ".000Z";
		return utc; // ISO DateTime
	}
	function addFilters(query, request, names) {
		function endsWith(str, suffix) {
		    return str.indexOf(suffix, str.length - suffix.length) !== -1;
		}
		for (var i = 0; i < names.length; ++i) {
		    var name = names[i];
			var field = '';
			if (query)
				field = query[name] ? query[name] : '';
			else
				field = '';
			if (endsWith(name, 'Date'))
				field = formatDate(field);
			else
				field = formatLike(field);
			request[name] = field;
		}
		return request;
	}
	function getSort(query) {
		var order = query.order;
		if (!order) {
			return '-startDate';
		}
		var prefix = '-';
		var startWith = order.slice(0, prefix.length);
		if (startWith == prefix) {
			var direction = 'desc';
			var name = order.slice(prefix.length, order.length);
		} else {
			var direction = 'asc';
			var name = order;
		}
		var sort = name + ',' + direction;
		return sort;
	}
	return {
		addLike : function(like, request, names) {
			for (var i = 0; i < names.length; ++i) {
		    	var name = names[i];
				request[name] = '%' + like + '%';
			}
			return request;
		},
		newRequestFromMdtable : function(query, names) {
			var sort = getSort(query);
			var request = {
				page : (query.page - 1),
				size : query.limit,
				sort : getSort(query)
			};
			request = addFilters(query.filter, request, names);
			return request;
		},
		newResponseForMdtable : function(response) {
			return {
				items : response._embedded.items,
				total : response.page.totalElements
			}
		},
	}
})


;