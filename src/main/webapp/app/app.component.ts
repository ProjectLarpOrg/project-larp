import { Component } from '@angular/core';
import { environment } from '../environments/environment';

@Component({
    selector: 'app-root',
    template: `
	<div>	
	  <router-outlet></router-outlet>	
	</div>
  `
})
export class AppComponent { 
    constructor() {
        console.log('production mode: '+environment.production); // Logs false for default environment
      }
}