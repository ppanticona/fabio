import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDetalleOperaContable } from '../detalle-opera-contable.model';
import { DetalleOperaContableService } from '../service/detalle-opera-contable.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './detalle-opera-contable-delete-dialog.component.html',
})
export class DetalleOperaContableDeleteDialogComponent {
  detalleOperaContable?: IDetalleOperaContable;

  constructor(protected detalleOperaContableService: DetalleOperaContableService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.detalleOperaContableService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
