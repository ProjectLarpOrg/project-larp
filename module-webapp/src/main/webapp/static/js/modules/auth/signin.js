'use strict';
angular.module('app-auth') //

.controller('SigninController', function($scope, $rootScope, $auth, $location, $route, $mdDialog) {
	// CONST
	var loginErrorMsg = 
			"Authentification Error: " +
			"please check your login, password." +
			"Then retry.";
    var errorDialog = $mdDialog.alert()
	    .parent(angular.element(document.querySelector('#popupContainer')))
	    .clickOutsideToClose(true)
	    .title('Sorry')
	    .textContent(loginErrorMsg)
	    .ok('Got it!');
	var progressDialog = {
            clickOutsideToClose: false,
            scope: $scope,        
            preserveScope: true,           
            template: 
'<div layout-padding><md-dialog>' +
'  <md-dialog-content>' +
'<div layout="row" layout-align="center center">' +
'  <div>' +
'    <md-progress-circular md-mode="indeterminate" class="md-hue-1" md-diameter="96"></md-progress-circular>' +
'  </div>' +
'</div>' +
'  </md-dialog-content>' +
'</md-dialog> </div>',
            controller: function DialogController($scope, $mdDialog) {
               $scope.closeDialog = function() {
                  //Nothing
               }
            }
         };
    
	// ACTIONS
	$scope.login = function() {
		$mdDialog.show(progressDialog);
		$auth.login($scope.user)
			.then(function(response) {
				
				$scope.$emit('loggedin', '');

				$mdDialog.hide(progressDialog);
				$location.path('/');
			})
			.catch(function(error) {
				$mdDialog.hide(progressDialog);
				$mdDialog.show(errorDialog);
			});
	};
})

;