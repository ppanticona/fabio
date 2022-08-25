import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IOperaContable } from '../opera-contable.model';
import { OperaContableService } from '../service/opera-contable.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './opera-contable-delete-dialog.component.html',
})
export class OperaContableDeleteDialogComponent {
  operaContable?: IOperaContable;

  constructor(protected operaContableService: OperaContableService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.operaContableService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
