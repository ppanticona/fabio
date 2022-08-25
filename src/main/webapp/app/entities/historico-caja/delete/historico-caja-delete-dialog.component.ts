import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IHistoricoCaja } from '../historico-caja.model';
import { HistoricoCajaService } from '../service/historico-caja.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './historico-caja-delete-dialog.component.html',
})
export class HistoricoCajaDeleteDialogComponent {
  historicoCaja?: IHistoricoCaja;

  constructor(protected historicoCajaService: HistoricoCajaService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.historicoCajaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
