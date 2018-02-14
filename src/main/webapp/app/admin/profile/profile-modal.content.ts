import { Component, Input, OnInit, EventEmitter, Output, ViewChild } from '@angular/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Profile, ProfileService } from '../services';

@Component({
    selector: 'profile-modal-content',
    templateUrl: './profile-modal.content.html'
  })
export class ProfileModalContent implements OnInit{
  @Input() id;
  profile: Profile

  constructor(
    public activeModal: NgbActiveModal,
    private service: ProfileService) {}

  ngOnInit() {
    this.load();
  }

  save() {
    this.update().then(
      resolve => this.activeModal.close());
  }

  private load(): Promise<Profile> {
    return this.service.read(this.id).then(
      resolve => this.profile = resolve,
      reject => this.profile = new Profile());
  }

  private update() {
    if (!this.id) 
      return this.service.create(this.profile);
    return this.service.update(this.profile);
  }
  
}