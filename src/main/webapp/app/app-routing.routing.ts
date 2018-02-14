import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard, LoginComponent } from './security';
import { MainLayoutComponent, DefaultLayoutComponent} from './layout';
import { HomeComponent } from './home';
import { AdminModule } from './admin';

const routes: Routes = [
    { path: '',         component: MainLayoutComponent, canActivate: [AuthGuard],
      children: [
            { path: '', component: HomeComponent },
            { path: 'admin', loadChildren: './admin/admin.module#AdminModule' }
        ]
     },
     { path: 'logout',   redirectTo: 'login' }, 
     { path: 'login',    component: DefaultLayoutComponent,
       children: [
        { path: '', component: LoginComponent }
      ]
     },
     { path: '**', redirectTo: '' }
];

@NgModule({
    imports: [ 
            RouterModule.forRoot(routes, { useHash: true }) 
    ],
    exports: [ 
            RouterModule 
    ]
  })
export class AppRoutingModule{ }