/*jshint -W099 */
angular.module('templates-main', []).run(['$templateCache', function($templateCache) {
	$templateCache.put('js/directives/datetime-interval.html',
	"<div flex=\"noshrink\" layout=\"row\" ng-cloak class=\"md-inline-form\">\n" +
	"\n" +
	"<md-input-container flex=\"noshrink\">\n" +
	"	<input date-time id=\"pickerFromDate\" name=\"pickerFromDate\"\n" +
	"		ng-model=\"dates.fromDate\" date-change=\"changeDate\"\n" +
	"		view=\"date\" min-view=\"minutes\" format=\"{{format}}\"\n" +
	"		>\n" +
	"	<md-tooltip>\n" +
	"		{{ 'search.datetime.tooltip' | translate }}\n" +
	"	</md-tooltip>\n" +
	"</md-input-container>\n" +
	"\n" +
	"<md-input-container flex=\"noshrink\"> \n" +
	"	<input date-time id=\"pickerToDate\" name=\"pickerToDate\"\n" +
	"		ng-model=\"dates.toDate\" date-change=\"changeDate\"\n" +
	"		view=\"date\" min-view=\"minutes\" format=\"{{format}}\"\n" +
	"		>\n" +
	"	<md-tooltip>\n" +
	"		{{ 'search.datetime.tooltip' | translate }}\n" +
	"	</md-tooltip>\n" +
	"</md-input-container>\n" +
	"\n" +
	"</div>");
	$templateCache.put('js/directives/datetime-periods.html',
	"<span>{{ 'search.datetime' | translate }}:\n" +
	"	\n" +
	"	<md-button ng-click=\"searchAuto()\" ng-class=\"{'md-raised md-warn app-status-progress': isAuto()}\"> \n" +
	"		<span>AUTO</span>\n" +
	"		<md-tooltip>\n" +
	"			{{ 'search.datetime.autorefresh.tooltip1' | translate }}\n" +
	"			{{refreshFromdateMinutes}}\n" +
	"			{{ 'search.datetime.autorefresh.tooltip2' | translate }}\n" +
	"		</md-tooltip>\n" +
	"	</md-button>\n" +
	"	\n" +
	"	<md-button ng-click=\"search1()\"> \n" +
	"		<span hide-sm show-gt-sm>{{ 'search.datetime.choice.1' | translate }}</span>\n" +
	"		<span show-sm hide-gt-sm>1H</span>\n" +
	"	</md-button>\n" +
	"	<md-button ng-click=\"search2()\">\n" +
	"		<span hide-sm show-gt-sm>{{ 'search.datetime.choice.2' | translate }}</span>\n" +
	"		<span show-sm hide-gt-sm>24H</span>\n" +
	"	</md-button> \n" +
	"	<md-button ng-click=\"search3()\">\n" +
	"		<span hide-sm show-gt-sm>{{ 'search.datetime.choice.3' | translate }}</span>\n" +
	"		<span show-sm hide-gt-sm>72H</span>\n" +
	"	</md-button>\n" +
	"</span>");
	$templateCache.put('js/directives/download-button.html',
	"<div layout=\"row\">\n" +
	"	<md-button class=\"md-fab md-mini\" ng-mouseover=\"updateDownloadUrl()\" href=\"{{downloadUrl}}\" ng-disabled=\"disabled()\">\n" +
	"		<md-icon md-svg-icon=\"file:file_download\">\n" +
	"			<md-tooltip md-direction=\"left\">{{ 'download.tooltip' | translate }}</md-tooltip>\n" +
	"		</md-icon> \n" +
	"	</md-button>\n" +
	"</div>");
	$templateCache.put('js/directives/form-field.html',
	"<div>\n" +
	"	<md-divider></md-divider>\n" +
	"	<div style=\"margin-top: 5px; margin-bottom: 5px;\">\n" +
	"		<div>\n" +
	"			<strong>{{ ::label}}</strong>\n" +
	"		</div>\n" +
	"		<div>\n" +
	"			<span ng-transclude></span>&nbsp;\n" +
	"		</div>\n" +
	"	</div>\n" +
	"</div>");
	$templateCache.put('js/directives/help-button.html',
	"<md-button class=\"md-mini\" ng-click=\"showAlert($event)\">\n" +
	"	<md-icon md-svg-icon=\"action:help\"></md-icon>\n" +
	"		<md-tooltip>\n" +
	"			{{ 'help.tooltip' | translate }} 			\n" +
	"		</md-tooltip>\n" +
	"</md-button>");
	$templateCache.put('js/directives/help-dialog.html',
	"<md-dialog ng-cloak>\n" +
	"<form>\n" +
	"	<md-toolbar>\n" +
	"	<div class=\"md-toolbar-tools\">\n" +
	"		<h2>{{ 'help.dialog' | translate }}</h2>\n" +
	"		<span flex></span>\n" +
	"		<md-button class=\"md-icon-button\" ng-click=\"closeDialog()\">\n" +
	"		<md-icon md-svg-icon=\"navigation:close\"></md-icon> </md-button>\n" +
	"	</div>\n" +
	"	</md-toolbar>\n" +
	"	<md-dialog-content>\n" +
	"	<div class=\"md-dialog-content\" ng-include=\"content\"></div>\n" +
	"	</md-dialog-content>\n" +
	"	<md-dialog-actions layout=\"row\"> <md-button\n" +
	"		class=\"md-raised md-primary\" ng-click=\"closeDialog()\"\n" +
	"		style=\"margin-right:20px;\"> {{ 'got_it' | translate }} </md-button> </md-dialog-actions>\n" +
	"</form>\n" +
	"</md-dialog>");
	$templateCache.put('js/directives/loading-progress.html',
	"<div class=\"loading\" ng-hide=\"hide\">\n" +
	"\n" +
	"	<div class=\"container\"\n" +
	"		style=\"margin-top: 10%; margin-left: 10%; margin-right: 10%;\">\n" +
	"		<div layout=\"column\" layout-align=\"center stretch\">\n" +
	"			<div>{{ 'loading' | translate }}</div>\n" +
	"			<div>\n" +
	"				<md-progress-linear md-mode=\"indeterminate\"></md-progress-linear>\n" +
	"			</div>\n" +
	"			<div>{{ 'loading.message' | translate }}</div>\n" +
	"		</div>\n" +
	"	</div>\n" +
	"</div>");
	$templateCache.put('js/directives/nav-content.html',
	"<md-toolbar ng-click=\"close()\" class=\"md-hue-2\"> \n" +
	"\n" +
	"  <md-button ng-click=\"close()\" hide-gt-md> \n" +
	"    <md-icon md-svg-icon=\"navigation:chevron_left\"></md-icon> \n" +
	"	{{ 'hide' | translate }} \n" +
	"  </md-button> \n" +
	"  \n" +
	"  <div layout=\"column\"> \n" +
	"    	<md-button  href=\"#/home\"> <md-icon md-svg-icon=\"action:home\"></md-icon> {{ 'navbar.home' | translate }}</md-button> \n" +
	"    	<md-button  href=\"#/help\"> <md-icon md-svg-icon=\"action:help\"></md-icon> {{ 'navbar.help' | translate }}</md-button> \n" +
	"    <md-divider></md-divider> \n" +
	"    <md-button disabled=\"true\"> <md-icon md-svg-icon=\"mdi:table-large\"></md-icon>  </md-button> \n" +
	"    	<md-button  href=\"#/services\"> {{ 'navbar.service_flow' | translate }} </md-button> \n" +
	"    	<md-button  href=\"#/exchange\"> {{ 'navbar.exchanges' | translate }}  </md-button> \n" +
	"   		<md-button  href=\"#/error\"> {{ 'navbar.error_flow' | translate }} </md-button> \n" +
	"    <md-divider></md-divider> \n" +
	"    <md-button disabled=\"true\"> <md-icon md-svg-icon=\"mdi:chart-areaspline\"></md-icon>  </md-button> \n" +
	"    	<md-button  href=\"#/charts/service/count\"> {{ 'navbar.service_traffic' | translate }} </md-button> \n" +
	"   		<md-button  href=\"#/charts/error/count\"> {{ 'navbar.error_count' | translate }} </md-button> \n" +
	"    	<md-button  href=\"#/charts/service/responsetime\"> {{ 'navbar.service_response' | translate }} </md-button> \n" +
	"    	<md-button  href=\"#/charts/cache/count\"> {{ 'navbar.cache_count' | translate }} </md-button> \n" +
	"	<md-divider ng-if=\"isAuthenticatedResubmit()\" ></md-divider>  \n" +
	"    <md-button  ng-if=\"isAuthenticatedResubmit()\" ng-disabled=\"true\"> <md-icon md-svg-icon=\"action:build\"></md-icon>  </md-button> \n" +
	"   	 	<md-button  ng-if=\"isAuthenticatedResubmit()\" href=\"#/resubmit\"> {{ 'navbar.resubmit' | translate }} </md-button> \n" +
	"    <md-divider></md-divider> \n" +
	"\n" +
	"    <md-button disabled=\"true\">\n" +
	"    	<md-icon md-svg-icon=\"social:person\"></md-icon> {{ ::session.user.mail}}\n" +
	"    </md-button> \n" +
	"  </div>\n" +
	"</md-toolbar>");
	$templateCache.put('js/directives/nav-toolbar.html',
	"<md-toolbar layout=\"row\"> \n" +
	"  <div class=\"md-toolbar-tools\"> \n" +
	"	<md-button id=\"menu-button\" ng-show=\"isAuthenticated()\" ng-click=\"toggleLeft()\" \n" +
	" 		class=\"md-raised\" hide-gt-md> \n" +
	" 		<md-icon md-svg-icon=\"navigation:menu\"></md-icon> \n" +
	" 		MENU \n" +
	"	</md-button> \n" +
	"    <md-button class=\"nav-brand\" href=\"#/home\">\n" +
	"    	<img src=\"img/logo.png\"> \n" +
	"	    <span>{{ 'app.name' | translate }} </span>\n" +
	"    </md-button>\n" +
	"    \n" +
	"	<span flex></span> \n" +
	"\n" +
	"	<md-menu ng-if=\"isAuthenticated()\" md-position-mode=\"target-right target\">\n" +
	"		<md-button ng-click=\"openMenu($mdOpenMenu, $event)\">\n" +
	"			APPS\n" +
	"   			<md-icon md-svg-icon=\"navigation:apps\"></md-icon> \n" +
	"		</md-button>\n" +
	"		<md-menu-content width=\"4\" id=\"applicationsMenu\">\n" +
	"\n" +
	"		</md-menu-content>\n" +
	"	</md-menu>\n" +
	"	\n" +
	"	<md-select ng-if=\"isAuthenticated()\" id=\"langMenu\" ng-model=\"selectedLocale\" ng-change=\"changeLocale(selectedLocale)\"> \n" +
	"		<md-option ng-repeat=\"(key, value) in locales\" value=\"{{ ::key}}\"> \n" +
	"			LANG:{{ ::value}} \n" +
	"		</md-option> \n" +
	"	</md-select> \n" +
	"	\n" +
	"   	<md-button ng-if=\"isAuthenticated()\" href=\"#/auth/logout\"> \n" +
	"   		{{ 'logout' | translate }}\n" +
	"   		<md-icon md-svg-icon=\"mdi:logout\"></md-icon> \n" +
	"   	</md-button> \n" +
	"  </div> \n" +
	"</md-toolbar>");
	$templateCache.put('js/directives/search-empty.html',
	"<div ng-show=\"!items.length\">\n" +
	"	<div layout-padding>\n" +
	"		<section layout=\"row\" layout-sm=\"column\" layout-align=\"center center\"\n" +
	"			layout-wrap>\n" +
	"			<div style=\"width: 600px;\">\n" +
	"				<strong>{{ 'search.empty' | translate }}</strong>\n" +
	"			</div>\n" +
	"		</section>\n" +
	"	</div>\n" +
	"</div>");
	$templateCache.put('js/directives/search.html',
	"<form ng-submit=\"search()\" flex class=\"app-search ng-pristine ng-valid\" layout=\"row\">\n" +
	"	<div layout=\"row\">\n" +
	"		<div>\n" +
	"			<md-button type=\"submit\" class=\"md-raised md-primary md-mini\">\n" +
	"				<md-icon md-svg-icon=\"action:search\"></md-icon> \n" +
	"				<span hide-md hide-sm hide-xs>{{ 'search.search' | translate }}</span>\n" +
	"			</md-button>\n" +
	"		</div>\n" +
	"		<div>\n" +
	"			<md-button ng-click=\"clear()\" class=\"md-raised md-mini\">\n" +
	"				<md-icon md-svg-icon=\"content:clear\"></md-icon> \n" +
	"				<span hide-md hide-sm hide-xs>{{ 'search.reset' | translate }}</span> \n" +
	"			</md-button>\n" +
	"		</div>\n" +
	"	</div>\n" +
	"	<div layout=\"row\" layout-wrap ng-cloak class=\"md-inline-form\" \n" +
	"		ng-transclude></div>\n" +
	"</form>");
	$templateCache.put('js/directives/toolbar.html',
	"<md-toolbar class=\"md-hue-3\">\n" +
	"	<div class=\"md-toolbar-tools\">\n" +
	"		<md-button ng-if=\"backurl\" href=\"{{ ::backurl}}\">\n" +
	"			<md-icon md-svg-icon=\"navigation:arrow_back\"></md-icon> \n" +
	"			{{ 'toolbar.back' | translate }}\n" +
	"		</md-button>\n" +
	"		<h3>\n" +
	"			{{title | translate }}\n" +
	"		</h3> \n" +
	"		<span flex></span>\n" +
	"		<span layout=\"row\" ng-transclude></span> \n" +
	"	</div>\n" +
	"</md-toolbar>");
	$templateCache.put('js/modules/auth/login.html',
	"<div class=\"app-auth\" ng-controller=\"LoginController\">\n" +
	"	<div layout=\"row\" layout-align=\"center center\">\n" +
	"		<div layout=\"column\" layout-gt-sm=\"row\" layout-padding>\n" +
	"		\n" +
	"<form id=\"login\" name=\"loginForm\" ng-submit=\"login()\" novalidate layout-padding>\n" +
	"	<div class=\"brand\"> \n" +
	"		<img src=\"img/app-logo.png\">\n" +
	"	</div>\n" +
	"	<h2>{{ 'auth.login' | translate }}</h2>\n" +
	"	<p>{{ 'auth.login.message' | translate }}</p>\n" +
	"	<!-- LOGIN -->\n" +
	"	<md-input-container class=\"md-block\" flex-gt-sm>\n" +
	"		<input class=\"form-control input-lg\" type=\"text\" name=\"username\"\n" +
	"			ng-model=\"username\" \n" +
	"			placeholder=\"Login\" required autofocus>\n" +
	"	</md-input-container>\n" +
	"	<!-- PASSWORD -->\n" +
	"	<md-input-container class=\"md-block\" flex-gt-sm>\n" +
	"		<input server-error class=\"form-control input-lg\" type=\"password\"\n" +
	"			name=\"password\" ng-model=\"password\" \n" +
	"			placeholder=\"Password\" required>\n" +
	"	</md-input-container>\n" +
	"	<!-- HELP -->\n" +
	"	<div class=\"help-block\" ng-if=\"loginForm.$invalid\"\n" +
	"		ng-messages=\"loginForm.$invalid\">\n" +
	"		<div ng-message=\"required\">\n" +
	"		{{ 'auth.login.help' | translate }}</div>\n" +
	"	</div>\n" +
	"	<!-- SUBMIT -->\n" +
	"	<div>\n" +
	"		<md-button class=\"md-primary md-raised\"\n" +
	"		 	type=\"submit\" \n" +
	"			ng-disabled=\"loginForm.$invalid\">\n" +
	"			LOG IN <md-icon\n" +
	"			md-svg-icon=\"mdi:login\"></md-icon> </md-button>\n" +
	"	</div>\n" +
	"	<div>\n" +
	"		<md-button class=\"md-primary\" href=\"{{ ::siteUrl }}\">\n" +
	"			{{ 'copyright' | translate }}\n" +
	"			<md-icon md-svg-icon=\"image:navigate_next\"></md-icon>\n" +
	"		</md-button>\n" +
	"		<md-button class=\"md-primary\" href=\"{{ ::siteUrl }}\">\n" +
	"			 version3 {{ ::buildVersion }}\n" +
	"		</md-button>\n" +
	"	</div>\n" +
	"</form>\n" +
	"\n" +
	"<form id=\"signup\" name=\"signupForm\" layout-padding>\n" +
	"	<div hide show-gt-sm class=\"brand\"> </div>\n" +
	"      <h2>\n" +
	"        {{ 'auth.signup' | translate }}\n" +
	"        <md-icon md-svg-icon=\"communication:vpn_key\"></md-icon>\n" +
	"      </h2>\n" +
	"      <p>{{ 'auth.signup.message' | translate }}</p>\n" +
	"      <p>\n" +
	"        <md-button class=\"md-raised md-primary\"\n" +
	"          href=\"{{ ::siteUrl }}\">\n" +
	"          {{ 'auth.signup.submit' | translate }} \n" +
	"          <md-icon md-svg-icon=\"action:help\"></md-icon></md-button>\n" +
	"      </p>\n" +
	"</form>\n" +
	"\n" +
	"		</div>\n" +
	"	</div>\n" +
	"</div>");
	$templateCache.put('js/modules/auth/logout.html',
	"<div ng-controller=\"LogoutController\">\n" +
	"\n" +
	"</div>");
	$templateCache.put('js/modules/help/help-home.html',
	"<h2>{{ 'help.manual.search' | translate }}</h2>\n" +
	"\n" +
	"<h3>{{ 'help.manual.search.section1' | translate }}</h3>\n" +
	"<p>{{ 'help.manual.search.section1.text' | translate }}</p>\n" +
	"<img src=\"img/help/help-01.jpg\">\n" +
	"\n" +
	"<h3>{{ 'help.manual.search.section2' | translate }}</h3>\n" +
	"<p>{{ 'help.manual.search.section2.text' | translate }}</p>\n" +
	"<img src=\"img/help/help-03.jpg\">\n" +
	"<br />\n" +
	"<img src=\"img/help/help-16.jpg\">\n" +
	"\n" +
	"<h3>{{ 'help.manual.search.section3' | translate }}</h3>\n" +
	"<p>{{ 'help.manual.search.section3.text' | translate }}</p>\n" +
	"<img src=\"img/help/help-02.jpg\">");
	$templateCache.put('js/modules/help/help.html',
	"<div ng-controller=\"HelpController\">\n" +
	"	<app-toolbar title=\"'help'\" backurl=\"backUrl\"> </app-toolbar>\n" +
	"	<md-content layout-padding>\n" +
	"\n" +
	"<div layout-padding>\n" +
	"	<h2>{{ 'help.about' | translate }}</h2>\n" +
	"	<p>{{ 'auth.login.message' | translate }}</p>\n" +
	"  	<section layout=\"row\" layout-xs=\"column\" layout-align=\"start center\" layout-wrap>\n" +
	"		<md-button class=\"md-raised\" ng-click=\"scrollTo('help-section-top')\">\n" +
	"			{{ 'help.get_help' | translate }} \n" +
	"		 </md-button>\n" +
	"		<md-button class=\"md-raised\" href=\"{{ ::supportUrl}}\">\n" +
	"			{{ 'help.report_issue' | translate }} \n" +
	"		 </md-button>\n" +
	"	</section>\n" +
	"	 <p>Version {{ ::buildVersion}} ({{ ::buildTimestamp}})</p>\n" +
	"	<div>\n" +
	"		<md-button href=\"{{ ::teamUrl}}\">\n" +
	"			{{ 'help.copyright' | translate }} \n" +
	"			<md-icon md-svg-icon=\"image:navigate_next\"></md-icon>\n" +
	"		 </md-button>\n" +
	"	</div>\n" +
	"</div>\n" +
	"\n" +
	"<md-divider></md-divider>\n" +
	"\n" +
	"<div id=\"help-section-top\" layout-padding>\n" +
	"	<h2>{{ 'help.help' | translate }}</h2>\n" +
	"  	<section layout=\"row\" layout-sm=\"column\" layout-align=\"center center\" layout-wrap>\n" +
	"		<md-button class=\"md-primary\" ng-click=\"scrollTo('help-section-features')\">\n" +
	"			{{ 'help.manual.index.features' | translate }} </a></md-button>\n" +
	"		<md-button class=\"md-primary\" ng-click=\"scrollTo('help-section-menu')\">\n" +
	"			{{ 'help.manual.index.home' | translate }} </a></md-button>\n" +
	"	</section>\n" +
	"</div>\n" +
	"<div id=\"help-section-features\" layout-padding>\n" +
	"	{{ 'help.manual.back' | translate }} <md-button class=\"md-fab md-mini md-primary\" ng-click=\"scrollTo('help-section-top')\"><md-icon md-svg-icon=\"navigation:expand_less\"></md-icon></md-button>\n" +
	"	<h2>{{ 'help.manual.features' | translate }}</h2>\n" +
	"	<p>{{ 'help.manual.features.text' | translate }}</p>\n" +
	"	<app-card-container> \n" +
	"		<app-card title=\"Charts\"> <img src=\"img/help/help-07.jpg\"\n" +
	"		style=\"height: 300px; width: 320px;\"> </app-card> \n" +
	"		<app-card title=\"Data Tables\"> <img src=\"img/help/help-06.jpg\"\n" +
	"		style=\"height: 300px; width: 320px;\"> </app-card>\n" +
	"		<app-card title=\"Item Details\"> <img src=\"img/help/help-08.jpg\"\n" +
	"		style=\"height: 300px; width: 320px;\"> </app-card> \n" +
	"	</app-card-container>\n" +
	"</div>\n" +
	"<div id=\"help-section-menu\" layout-padding>\n" +
	"	{{ 'help.manual.back' | translate }} <md-button class=\"md-fab md-mini md-primary\" ng-click=\"scrollTo('help-section-top')\"><md-icon md-svg-icon=\"navigation:expand_less\"></md-icon></md-button>\n" +
	"	<ng-include src=\"helpHome\"></ng-include>\n" +
	"</div>\n" +
	"\n" +
	"	</md-content>\n" +
	"</div>");
	$templateCache.put('js/modules/home/home.html',
	"<div ng-controller=\"DashboardController\" layout='column' layout-fill>\n" +
	"	<app-toolbar title=\"'home.dashboard'\">\n" +
	"		<app-search-datetime-periods dates=\"query.filter\" execute=\"search()\"></app-search-datetime-periods>\n" +
	"		<app-help-button href=\"{{ ::helpTplUrl}}\"></app-help-button>\n" +
	"	</app-toolbar>\n" +
	"	<md-content layout-padding>\n" +
	"\n" +
	"<app-search search=\"search()\" clear=\"init()\">\n" +
	"	<app-search-datetime-interval dates=\"query.filter\"></app-search-datetime-interval>\n" +
	"	<md-button class=\"md-accent\" href=\"{{ ::helpUrl}}\"> \n" +
	"		{{ 'home.dashboard.help' | translate }}\n" +
	"		<md-icon md-svg-icon=\"action:help\"></md-icon>\n" +
	"	 </md-button>\n" +
	"</app-search>\n" +
	"\n" +
	"<md-button class=\"md-warn\">{{ 'home.dashboard.datetime' | translate }}</md-button>\n" +
	"\n" +
	"<div ng-if=\"isLoading\" app-loading></div>\n" +
	"<div ng-if=\"!isLoading\" layout=\"row\" layout-wrap class=\"md-padding\">\n" +
	"\n" +
	"	<md-card layout=\"column\">\n" +
	"		<md-card-header>\n" +
	"          <md-card-avatar>\n" +
	"            <md-icon md-svg-icon=\"navigation:check\"></md-icon>\n" +
	"          </md-card-avatar>\n" +
	"          <md-card-header-text>\n" +
	"            <span class=\"md-title\">{{ 'home.dashboard.status' | translate }}</span>\n" +
	"            <span class=\"md-subhead\">{{ 'home.dashboard.status.subhead' | translate }}</span>\n" +
	"          </md-card-header-text>\n" +
	"        </md-card-header>\n" +
	"	    <nvd3 options=\"serviceOptions\" data=\"serviceStatusData\"></nvd3>\n" +
	"        <md-card-actions layout=\"row\" layout-align=\"start center\">\n" +
	"       		<md-button class=\"md-primary\" href=\"{{ ::errorUrl}}\"> \n" +
	"				{{ 'more' | translate }} \n" +
	"				<md-icon md-svg-icon=\"navigation:chevron_right\"></md-icon>\n" +
	"			</md-button>\n" +
	"		</md-card-actions>\n" +
	"	</md-card>\n" +
	"	\n" +
	"	<md-card layout=\"column\">\n" +
	"		<md-card-header>\n" +
	"          <md-card-avatar>\n" +
	"            <md-icon md-svg-icon=\"toggle:star_half\"></md-icon>\n" +
	"          </md-card-avatar>\n" +
	"          <md-card-header-text>\n" +
	"            <span class=\"md-title\">{{ 'home.dashboard.cache' | translate }}</span>\n" +
	"            <span class=\"md-subhead\">{{ 'home.dashboard.cache.subhead' | translate }}</span>\n" +
	"          </md-card-header-text>\n" +
	"        </md-card-header>\n" +
	"		<nvd3 options=\"cacheOptions\" data=\"cacheData\" title=\"Cache\"></nvd3>\n" +
	"        <md-card-actions layout=\"row\" layout-align=\"start center\">\n" +
	"			<md-button class=\" md-primary\" href=\"{{ ::cacheUrl}}\"> \n" +
	"				{{ 'more' | translate }} \n" +
	"				<md-icon md-svg-icon=\"navigation:chevron_right\"></md-icon>\n" +
	"			</md-button>\n" +
	"		</md-card-actions>\n" +
	"	</md-card>\n" +
	"	\n" +
	"	<md-card layout=\"column\">\n" +
	"		<md-card-header>\n" +
	"          <md-card-avatar>\n" +
	"            <md-icon md-svg-icon=\"mdi:chart-areaspline\"></md-icon>\n" +
	"          </md-card-avatar>\n" +
	"          <md-card-header-text>\n" +
	"            <span class=\"md-title\">{{ 'home.dashboard.traffic' | translate }}</span>\n" +
	"            <span class=\"md-subhead\">{{ 'home.dashboard.traffic.subhead' | translate }}</span>\n" +
	"          </md-card-header-text>\n" +
	"        </md-card-header>\n" +
	"       	<nvd3 options=\"trafficOptions\" data=\"trafficData\"></nvd3>\n" +
	"        <md-card-actions layout=\"row\" layout-align=\"start center\">\n" +
	"			<md-button class=\" md-primary\" href=\"{{ ::trafficUrl}}\"> \n" +
	"				{{ 'more' | translate }} \n" +
	"				<md-icon md-svg-icon=\"navigation:chevron_right\"></md-icon>\n" +
	"			</md-button>\n" +
	"		</md-card-actions>\n" +
	"	</md-card>\n" +
	"		\n" +
	"</div>\n" +
	"\n" +
	"	</md-content>\n" +
	"</div>");
}]);
