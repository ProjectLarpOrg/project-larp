'use strict';

angular.module('app', [ //
    // APP MODULES
    'app-auth', //
    'app-admin', //
    'app-settings', 'app-profile', //
    // THIRD PARTY MODULES
	'ngResource',  'ngRoute', //
	'ngMaterial', 'ngAnimate', //
	'satellizer', //
	'md.data.table', //
	'nvd3', //
	'pascalprecht.translate', 'tmh.dynamicLocale', 'ngCookies', //
	'datePicker', //
	'templates-main' // GENERATED/AGGREGATED template.js
])

// CONFIG
.config(function($routeProvider, $locationProvider) {
	// ROUTE: URLs
	$routeProvider. //
		when('/home',        { templateUrl : 'js/modules/home/home.html', resolve : { loginRequired : loginRequired }	}).
		when('/about',       { templateUrl : 'js/modules/home/about.html'	}).
		// AUTH
		when('/login',       { templateUrl : 'js/modules/auth/login.html'	}).
		when('/logout',      { templateUrl : 'js/modules/auth/logout.html' }).
		when('/help',        { templateUrl : 'js/modules/help/help.html', resolve : { loginRequired : loginRequired }	}).
		// ADMIN
		when('/admin',       			{ templateUrl : 'js/modules/admin/admin.html', viewUrl: 'js/modules/admin/index.html', resolve : { loginRequired : loginRequired }	}).
		when('/admin/users', 			{ templateUrl : 'js/modules/admin/admin.html', viewUrl: 'js/modules/admin/user-list.html', resolve : { loginRequired : loginRequired }	}).
		// PROFILE
		when('/profile',     { templateUrl : 'js/modules/profile/profile.html', resolve : { loginRequired : loginRequired }	}).
		// SETTINGS
		when('/settings',    { templateUrl : 'js/modules/settings/settings.html', resolve : { loginRequired : loginRequired }	}).
		when('/settings/:userId',    { templateUrl : 'js/modules/settings/settings.html', resolve : { loginRequired : loginRequired }	}).
		//
		otherwise('/home');
	$locationProvider.hashPrefix('');
	//$locationProvider.html5Mode(true);
})

.config(function($authProvider) {
	$authProvider.withCredentials = true;
	$authProvider.tokenRoot = null;
	$authProvider.cordova = false;
	$authProvider.baseUrl = './api/';
	$authProvider.loginUrl = '/auth/login';
	$authProvider.signupUrl = '/auth/signup'; // TODO ???
	$authProvider.unlinkUrl = '/auth/unlink/'; // TODO ???
	$authProvider.tokenName = 'access_token';
	$authProvider.tokenPrefix = 'project_larp';
	$authProvider.authHeader = 'Authorization';
	$authProvider.authToken = 'Bearer';
	$authProvider.storageType = 'localStorage';
})

/**
 * https://material.io/icons/
 */
.config(function($mdIconProvider) {
	$mdIconProvider //
		.iconSet('action', 'angular/icons/sets/action-icons.svg', 24) //
//		.iconSet('alert', 'angular/icons/sets/alert-icons.svg', 24) //
//		.iconSet('av', 'angular/icons/sets/av-icons.svg', 24) //
//		.iconSet('communication', 'angular/icons/sets/communication-icons.svg', 24) //
		.iconSet('content', 'angular/icons/sets/content-icons.svg', 24) //
//		.iconSet('device', 'angular/icons/sets/device-icons.svg', 24) //
//		.iconSet('editor', 'angular/icons/sets/editor-icons.svg', 24) //
//		.iconSet('file', 'angular/icons/sets/file-icons.svg', 24) //
		.iconSet('hardware', 'angular/icons/sets/hardware-icons.svg', 24) //
//		.iconSet('icons', 'angular/icons/sets/icons-icons.svg', 24) //
//		.iconSet('image', 'angular/icons/sets/image-icons.svg', 24) //
//		.iconSet('maps', 'angular/icons/sets/maps-icons.svg', 24) //
//		.iconSet('navigation', 'angular/icons/sets/navigation-icons.svg', 24) //
//		.iconSet('notification', 'angular/icons/sets/notification-icons.svg', 24) //
		.iconSet('social', 'angular/icons/sets/social-icons.svg', 24) //
//		.iconSet('toggle', 'angular/icons/sets/toggle-icons.svg', 24) //
//		.iconSet('mdi', 'angular/icons/sets/mdi-icons.svg', 24) //
		;
})

.config(function($mdThemingProvider) {
	// generated here http://mcg.mbitson.com/#!?mcgpalette0=%23000000
	$mdThemingProvider.definePalette('black', {
		  '50': 'e0e0e0',
		  '100': 'b3b3b3',
		  '200': '808080',
		  '300': '4d4d4d',
		  '400': '262626',
		  '500': '000000',
		  '600': '000000',
		  '700': '000000',
		  '800': '000000',
		  '900': '000000',
		  'A100': 'a6a6a6',
		  'A200': '8c8c8c',
		  'A400': '737373',
		  'A700': '666666',
	    'contrastDefaultColor': 'light'
    });
	$mdThemingProvider.theme('default') //
		.primaryPalette('black') //
		.accentPalette('green') // 
		.warnPalette('red') //
		.backgroundPalette('grey') //
		;
	$mdThemingProvider.setDefaultTheme('default');
})

//i18n: LANGUAGE
.config( function ($translateProvider) {
	
  // ADD LANG
  $translateProvider
  .useStaticFilesLoader({
    prefix: 'i18n/locale-',
    suffix: '.json'
  })
  
  // REMEMBER
  .preferredLanguage('en')
  .fallbackLanguage('en')
  .useCookieStorage()
  
  // SECURITY
  .useSanitizeValueStrategy('escape');
})

/**
 * Helper auth functions
 * https://github.com/sahat/satellizer/blob/master/examples/client/app.js
 */
function skipIfLoggedIn($q, $auth) {
	var deferred = $q.defer();
	if ($auth.isAuthenticated()) {
		deferred.reject();
	} else {
		deferred.resolve();
	}
	return deferred.promise;
}

function loginRequired($q, $location, $auth, $route) {
	var deferred = $q.defer();
	if ($auth.isAuthenticated()) {
		deferred.resolve();
	} else {
		$location.url('/login');
		$route.reload();
	}
	return deferred.promise;
}


// disable aria warning (accessibility)
console.realWarn = console.warn;
console.warn = function(message) {
	if (message.indexOf("ARIA") == -1) {
		console.realWarn.apply(console, arguments);
	}
};
