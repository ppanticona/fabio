import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { InventarioIngresoDialog2Component } from './inventario-ingreso-dialog-2.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { faMagnifyingGlass, faCirclePlus, faTrash } from '@fortawesome/free-solid-svg-icons';
import dayjs from 'dayjs/esm';
import { User } from 'app/admin/user-management/user-management.model';
import { IProveedor } from '../../entities/proveedor/proveedor.model';
import { ProveedorService } from '../../entities/proveedor/service/proveedor.service';
import { ProductoService } from '../../entities/producto/service/producto.service';
import { MovimientoProductoService } from '../../entities/movimiento-producto/service/movimiento-producto.service';

@Component({
  templateUrl: './inventario-ingreso-dialog.component.html',
})
export class InventarioIngresoDialogComponent {
  registrandoIngreso = false;
  descProd = '';
  codProd: any;
  tipIngreso: any;
  tipDocIngreso: any;
  numDocIngreso: any;
  lote: any;
  precCompraDet: any;
  precVentaDet: any;
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
  tipDocProv = '0';
  numDocProv = '99999999';
  productoSeleccionado: any;
  proveedor: IProveedor | null = null;
  isLoadingProveedor = false;

  detalleIngreso: any = [];
  datosAregistrar: any = {};

  constructor(
    public activeModal: NgbActiveModal,
    protected movimientoProductoService: MovimientoProductoService,
    protected proveedorService: ProveedorService,
    protected productoService: ProductoService,
    protected modalService2: NgbModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  buscarProveedor(): void {
    this.proveedor = { id: '' };
    this.isLoadingProveedor = true;

    this.proveedorService.findByTipAndNroCli(this.tipDocProv, this.numDocProv).subscribe(res => {
      this.isLoadingProveedor = false;
      if (res.body === null) {
        this.isLoadingProveedor = false;
      } else {
        this.proveedor = res.body ?? {};
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
    });
  }
  agregarProductoADetalle(): void {
    this.productoSeleccionado.cantidad = this.cantidadDet;
    this.productoSeleccionado.precCompraDet = this.precCompraDet;
    this.productoSeleccionado.precVentaDet = this.precVentaDet;

    this.detalleIngreso.push(this.productoSeleccionado);
    this.productoSeleccionado = null;
    this.cantidadDet = 0;
    this.precCompraDet = 0;
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
  registrarIngreso(): void {
    this.registrandoIngreso = true;
    const today = dayjs();
    this.datosAregistrar.fecCrea = dayjs(today, DATE_TIME_FORMAT);
    this.datosAregistrar.usuCrea = this.usuCrea;
    this.datosAregistrar.tipIngreso = this.tipIngreso;
    this.datosAregistrar.tipDocIngreso = this.tipDocIngreso;
    this.datosAregistrar.numDocIngreso = this.numDocIngreso;
    this.datosAregistrar.lote = this.lote;
    this.datosAregistrar.tipDocProv = this.tipDocProv;
    this.datosAregistrar.numDocProv = this.numDocProv;
    this.datosAregistrar.detalleIngreso = this.detalleIngreso;
    this.datosAregistrar.NomApeRazSocProv = this.proveedor?.nombresRazonSocProv;
    this.datosAregistrar.proveedorID = this.proveedor?.id;

    this.movimientoProductoService.registrarIngreso(this.datosAregistrar).subscribe(res => {
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
