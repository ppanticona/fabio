import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IMovimientoProducto } from '../movimiento-producto.model';
import { MovimientoProductoService } from '../service/movimiento-producto.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './movimiento-producto-delete-dialog.component.html',
})
export class MovimientoProductoDeleteDialogComponent {
  movimientoProducto?: IMovimientoProducto;

  constructor(protected movimientoProductoService: MovimientoProductoService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.movimientoProductoService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
