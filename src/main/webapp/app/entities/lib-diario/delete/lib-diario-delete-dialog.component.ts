import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ILibDiario } from '../lib-diario.model';
import { LibDiarioService } from '../service/lib-diario.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './lib-diario-delete-dialog.component.html',
})
export class LibDiarioDeleteDialogComponent {
  libDiario?: ILibDiario;

  constructor(protected libDiarioService: LibDiarioService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.libDiarioService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
