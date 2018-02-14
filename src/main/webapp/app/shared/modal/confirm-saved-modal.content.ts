import { Component, Output , ViewEncapsulation} from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'confirm-saved-modal-content',
    templateUrl: './confirm-saved-modal.content.html',
    encapsulation: ViewEncapsulation.None
  })
export class ConfirmSavedModalContent {

  constructor(
      public activeModal: NgbActiveModal) {}
}