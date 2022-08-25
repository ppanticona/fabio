import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IPlanContable } from '../plan-contable.model';
import { PlanContableService } from '../service/plan-contable.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './plan-contable-delete-dialog.component.html',
})
export class PlanContableDeleteDialogComponent {
  planContable?: IPlanContable;

  constructor(protected planContableService: PlanContableService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.planContableService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
