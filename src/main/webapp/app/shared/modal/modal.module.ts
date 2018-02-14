import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonModule }   from '@angular/common';
import { ConfirmDeleteModalContent } from './confirm-delete-modal.content';
import { ConfirmSavedModalContent } from './confirm-saved-modal.content';

@NgModule({
    imports:        [ FormsModule , CommonModule ],
    declarations:   [ ConfirmDeleteModalContent, ConfirmSavedModalContent ],
    exports:        [ ConfirmDeleteModalContent, ConfirmSavedModalContent ],
    schemas:        [ CUSTOM_ELEMENTS_SCHEMA ],
    entryComponents:[ ConfirmDeleteModalContent, ConfirmSavedModalContent ]
})
export class ModalModule { }
