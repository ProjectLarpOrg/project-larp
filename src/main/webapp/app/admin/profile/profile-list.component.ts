import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { ProfileService, Profile } from '../services';
import { ProfileModalContent } from './profile-modal.content';
import { ConfirmDeleteModalContent } from '../../shared/modal/confirm-delete-modal.content';

@Component({
  templateUrl: './profile-list.component.html'
})
export class ProfileListComponent  implements OnInit { 
  
  profiles: Promise<Profile[]>;

  constructor(		
    private service: ProfileService,
    private modalService: NgbModal
  ) {}

  ngOnInit() {
    this.load();
  }

  add() {
    const modalRef = this.modalService.open(ProfileModalContent);
    modalRef.result.then(
      resolve => this.load(),
      reject => {}
    )
  }

  open(profile: Profile) {
    const modalRef = this.modalService.open(ProfileModalContent);
    modalRef.componentInstance.id = profile.username;
    modalRef.result.then(
      resolve => this.load(),
      reject => {}
    )
  }

  delete(poi: Profile) {
    const modalRef = this.modalService.open(ConfirmDeleteModalContent, { windowClass: 'danger-modal' });
    modalRef.result.then(
      resolve => this.deleteConfirm(poi),
      reject => {});
  }

  private load() {
    this.profiles = this.service.readAll();
  }

  private deleteConfirm(profile: Profile) {
    this.service.delete(profile.username).then(
      resolve => this.load()
    );
  }

}