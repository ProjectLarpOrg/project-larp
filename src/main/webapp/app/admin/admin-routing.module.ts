import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileListComponent }  from './profile';

const adminRoutes: Routes = [
  {	path: '',                 component: ProfileListComponent },
  {	path: 'profile/list',        component: ProfileListComponent }
];

@NgModule({
  imports: [ RouterModule.forChild(adminRoutes) ],
  exports: [ RouterModule ]
})
export class AdminRoutingModule{ }