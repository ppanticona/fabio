import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import dayjs from 'dayjs/esm';
import { IProducto, NewProducto } from '../../entities/producto/producto.model';
import { ProductoService } from '../../entities/producto/service/producto.service';

@Component({
  templateUrl: './inventario-productos-dialog.component.html',
})
export class InventarioProductosDialogComponent {
  generandoOrden = false;
  productoExiste = false;
  registrandoProducto = false;
  isProcesando = false;
  usuCrea: any;
  producto: NewProducto = {
    id: null,
    codProducto: '1',
  };

  constructor(public activeModal: NgbActiveModal, protected productoService: ProductoService) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  registrar() {
    this.registrandoProducto = true;
    const today = dayjs();
    this.producto.fecCrea = dayjs(today, DATE_TIME_FORMAT);
    this.producto.estado = '01';
    this.producto.version = 1;
    this.producto.indDel = false;
    this.producto.ipCrea = '0.0.0.0';
    this.producto.urlImage = '-';
    this.subscribeToSaveResponse(this.productoService.create(this.producto));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProducto>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.activeModal.close('registrado');
  }

  protected onSaveError(): void {
    // Api for inheritance.
    this.registrandoProducto = false;
  }

  protected onSaveFinalize(): void {
    this.isProcesando = false;
  }
}
