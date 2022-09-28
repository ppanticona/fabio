import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { faMagnifyingGlass, faCirclePlus, faTrash } from '@fortawesome/free-solid-svg-icons';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import dayjs from 'dayjs/esm';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'app/admin/user-management/user-management.model';
import { InventarioIngresoDialog2Component } from '../ingreso/inventario-ingreso-dialog-2.component';
import { MovimientoProductoService } from '../../entities/movimiento-producto/service/movimiento-producto.service';
import { ClienteService } from '../../entities/cliente/service/cliente.service';
import { ProductoService } from '../../entities/producto/service/producto.service';
import { ICliente } from '../../entities/cliente/cliente.model';

@Component({
  templateUrl: './inventario-salida-dialog.component.html',
})
export class InventarioSalidaDialogComponent {
  registrandoIngreso = false;
  descProd = '';
  codProd: any;
  tipSalida: any;
  tipDocIngreso: any;
  numDocIngreso: any;
  lote: any;
  precVentaDet: any;
  precCompraDet: any;

  cantidadDet: any;
  faMagnifyingGlass = faMagnifyingGlass;
  faCirclePlus = faCirclePlus;
  faTrash = faTrash;
  aperturaCaja = false;
  cierreCaja = false;
  isProcesando = false;
  isbuscandoProducto = false;
  userCajeros: User[] | null = null;
  usuCrea: any;
  tipDocCli = '0';
  numDocCli = '99999999';
  productoSeleccionado: any;
  cliente: ICliente | null = null;
  isLoadingProveedor = false;

  detalleIngreso: any = [];
  datosAregistrar: any = {};

  constructor(
    public activeModal: NgbActiveModal,
    protected movimientoProductoService: MovimientoProductoService,
    protected clienteService: ClienteService,
    protected productoService: ProductoService,
    protected modalService2: NgbModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  buscarCliente(): void {
    this.cliente = { id: '' };
    this.isLoadingProveedor = true;

    this.clienteService.findByTipAndNroCli(this.tipDocCli, this.numDocCli).subscribe(res => {
      this.isLoadingProveedor = false;
      if (res.body === null) {
        this.isLoadingProveedor = false;
      } else {
        this.cliente = res.body ?? {};
        this.isLoadingProveedor = false;
      }
    });
  }

  buscarYasignarProductoxCod(): void {
    this.isbuscandoProducto = true;

    this.productoService.findByCodProd(this.codProd).subscribe(res => {
      this.isbuscandoProducto = false;
    });
  }

  trackByFn(i: number): any {
    return i;
  }
  removeDetalle(detalle: any): void {
    this.detalleIngreso.splice(this.detalleIngreso.indexOf(detalle), 1);
  }
  buscarProductoModal(): void {
    const modalRef = this.modalService2.open(InventarioIngresoDialog2Component, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.agregarProducto = true;
    modalRef.componentInstance.nuevoProveedor = false;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      this.productoSeleccionado = reason;
      this.descProd = reason.descripcion;
      this.codProd = reason.codProducto;
      this.precVentaDet = reason.valor;
      this.precCompraDet = reason.costoProd;
    });
  }
  agregarProductoADetalle(): void {
    this.productoSeleccionado.cantidad = this.cantidadDet;
    this.productoSeleccionado.precVentaDet = this.precVentaDet;
    this.productoSeleccionado.precCompraDet = this.precCompraDet;

    this.detalleIngreso.push(this.productoSeleccionado);
    this.productoSeleccionado = null;
    this.cantidadDet = 0;
    this.precVentaDet = 0;
    this.descProd = '';
    this.codProd = '';
  }

  registrarProveedorModal(): void {
    const modalRef = this.modalService2.open(InventarioIngresoDialog2Component, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.agregarProducto = false;
    modalRef.componentInstance.nuevoProveedor = true;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'registrado') {
      }
    });
  }
  registrarSalida(): void {
    this.registrandoIngreso = true;
    const today = dayjs();
    this.datosAregistrar.fecCrea = dayjs(today, DATE_TIME_FORMAT);
    this.datosAregistrar.usuCrea = this.usuCrea;
    this.datosAregistrar.tipSalida = this.tipSalida;
    this.datosAregistrar.tipDocIngreso = this.tipDocIngreso;
    this.datosAregistrar.numDocIngreso = this.numDocIngreso;
    this.datosAregistrar.lote = this.lote;
    this.datosAregistrar.tipDocCli = this.tipDocCli;
    this.datosAregistrar.numDocCli = this.numDocCli;
    this.datosAregistrar.detalleIngreso = this.detalleIngreso;
    this.datosAregistrar.nombresCli = this.cliente?.nombresCli;
    this.datosAregistrar.clienteId = this.cliente?.id;

    this.movimientoProductoService.registrarSalida(this.datosAregistrar).subscribe(res => {
      if (res.body.Result === 'OK') {
        this.activeModal.close('registrado');
        this.registrandoIngreso = false;
      } else {
        this.onSaveError();
        this.registrandoIngreso = false;
      }
    });
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
