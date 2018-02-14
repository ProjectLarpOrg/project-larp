import { NgModule }   from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule }   from '@angular/common';
import { ReactiveFormsModule }    from '@angular/forms';
import { NgbModule, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ProfileListComponent, ProfileModalContent } from './profile';
import { ProfileService } from './services';
import { AdminRoutingModule } from './admin-routing.module';
import { ModalModule } from '../shared/modal/modal.module';

@NgModule({
  imports: [     
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule.forRoot(),
    AdminRoutingModule,
    ModalModule
  ], 
  declarations: [
    ProfileListComponent, ProfileModalContent
  ],
  providers: [ 
    NgbActiveModal,
    ProfileService
  ],
  entryComponents: [
    ProfileModalContent
 ]
})
export class AdminModule { }