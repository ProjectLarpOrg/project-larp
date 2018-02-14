import { Component, Output , ViewEncapsulation} from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'confirm-delete-modal-content',
    templateUrl: './confirm-delete-modal.content.html',
    encapsulation: ViewEncapsulation.None,
    styles: [`
    .danger-modal .modal-content {
      background-color: #dc3545;
      color: white;
    }
    .danger-modal .close {
      color: white;   
    }

    .dark-modal .modal-content {
        background-color: #292b2c;
        color: white;
      }
    .dark-modal .close {
        color: white;   
      }
    `]
  })
export class ConfirmDeleteModalContent {

  constructor(
      public activeModal: NgbActiveModal) {}
}