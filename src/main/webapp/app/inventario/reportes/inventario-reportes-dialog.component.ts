import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import * as dayjs from 'dayjs';
import { User } from 'app/admin/user-management/user-management.model';
import { CajaService } from 'app/entities/caja/service/caja.service';

@Component({
  templateUrl: './inventario-reportes-dialog.component.html',
})
export class InventarioReportesDialogComponent {
  generandoOrden = false;
  aperturaCaja = false;
  cierreCaja = false;
  isProcesando = false;
  userCajeros: User[] | null = null;
  usuCrea: any;
  datosAregistrar: any = {
    montoInicialSoles: 0.0,
    montoMaximoSoles: 0.0,
    montoInicialDolares: 0.0,
    montoMaximoDolares: 0.0,
    usuarioAsignado: '',
  };

  constructor(public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  protected onSaveSuccess(): void {
    this.activeModal.close('registrado');
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isProcesando = false;
  }
}
