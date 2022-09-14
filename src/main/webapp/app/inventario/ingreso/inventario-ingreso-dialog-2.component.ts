import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import dayjs from 'dayjs/esm';
import { IProducto, NewProducto } from '../../entities/producto/producto.model';
import { ProductoService } from '../../entities/producto/service/producto.service';

@Component({
  templateUrl: './inventario-ingreso-dialog-2.component.html',
})
export class InventarioIngresoDialog2Component {
  faMagnifyingGlass = faMagnifyingGlass;
  agregarProducto = false;
  nuevoProveedor = false;
  generandoOrden = false;
  productoExiste = false;
  buscandoProducto = false;
  cadenaBusqueda = '';
  tipoBusqueda = 'desc';
  productoSeleccionado: any;
  resultadoBusqueda: any;
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
  trackByFn(i: number): any {
    return i;
  }
  buscarProducto() {
    this.buscandoProducto = true;
    this.resultadoBusqueda = [];
    if (this.tipoBusqueda == 'desc') {
      this.productoService.findCadenaInDescripcion(this.cadenaBusqueda).subscribe(res => {
        this.buscandoProducto = false;
        if (res.body === null) {
          this.buscandoProducto = false;
        } else {
          this.resultadoBusqueda = res.body.productos ?? {};
          this.buscandoProducto = false;
        }
      });
    } else {
      this.productoService.findByCodProducto(this.cadenaBusqueda).subscribe(res => {
        this.buscandoProducto = false;
        if (res.body === null) {
          this.buscandoProducto = false;
        } else {
          this.resultadoBusqueda = res.body.productos ?? {};
          this.buscandoProducto = false;
        }
      });
    }
  }

  seleccionaProducto(producto: any): any {
    this.productoSeleccionado = producto;
    this.activeModal.close(this.productoSeleccionado);
  }

  registrar() {
    this.buscandoProducto = true;
    const today = dayjs();

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
    this.buscandoProducto = false;
  }

  protected onSaveFinalize(): void {
    this.isProcesando = false;
  }
}
