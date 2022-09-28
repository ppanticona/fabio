import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { MovimientoProductoFormService, MovimientoProductoFormGroup } from './movimiento-producto-form.service';
import { IMovimientoProducto } from '../movimiento-producto.model';
import { MovimientoProductoService } from '../service/movimiento-producto.service';
import { IProducto } from 'app/entities/producto/producto.model';
import { ProductoService } from 'app/entities/producto/service/producto.service';
import { IRegVenta } from 'app/entities/reg-venta/reg-venta.model';
import { RegVentaService } from 'app/entities/reg-venta/service/reg-venta.service';
import { IOrden } from 'app/entities/orden/orden.model';
import { OrdenService } from 'app/entities/orden/service/orden.service';
import { IRegCompras } from 'app/entities/reg-compras/reg-compras.model';
import { RegComprasService } from 'app/entities/reg-compras/service/reg-compras.service';

@Component({
  selector: 'jhi-movimiento-producto-update',
  templateUrl: './movimiento-producto-update.component.html',
})
export class MovimientoProductoUpdateComponent implements OnInit {
  isSaving = false;
  movimientoProducto: IMovimientoProducto | null = null;

  productosSharedCollection: IProducto[] = [];
  regVentasSharedCollection: IRegVenta[] = [];
  ordensSharedCollection: IOrden[] = [];
  regComprasSharedCollection: IRegCompras[] = [];

  editForm: MovimientoProductoFormGroup = this.movimientoProductoFormService.createMovimientoProductoFormGroup();

  constructor(
    protected movimientoProductoService: MovimientoProductoService,
    protected movimientoProductoFormService: MovimientoProductoFormService,
    protected productoService: ProductoService,
    protected regVentaService: RegVentaService,
    protected ordenService: OrdenService,
    protected regComprasService: RegComprasService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareProducto = (o1: IProducto | null, o2: IProducto | null): boolean => this.productoService.compareProducto(o1, o2);

  compareRegVenta = (o1: IRegVenta | null, o2: IRegVenta | null): boolean => this.regVentaService.compareRegVenta(o1, o2);

  compareOrden = (o1: IOrden | null, o2: IOrden | null): boolean => this.ordenService.compareOrden(o1, o2);

  compareRegCompras = (o1: IRegCompras | null, o2: IRegCompras | null): boolean => this.regComprasService.compareRegCompras(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ movimientoProducto }) => {
      this.movimientoProducto = movimientoProducto;
      if (movimientoProducto) {
        this.updateForm(movimientoProducto);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const movimientoProducto = this.movimientoProductoFormService.getMovimientoProducto(this.editForm);
    if (movimientoProducto.id !== null) {
      this.subscribeToSaveResponse(this.movimientoProductoService.update(movimientoProducto));
    } else {
      this.subscribeToSaveResponse(this.movimientoProductoService.create(movimientoProducto));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMovimientoProducto>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(movimientoProducto: IMovimientoProducto): void {
    this.movimientoProducto = movimientoProducto;
    this.movimientoProductoFormService.resetForm(this.editForm, movimientoProducto);

    this.productosSharedCollection = this.productoService.addProductoToCollectionIfMissing<IProducto>(
      this.productosSharedCollection,
      movimientoProducto.producto
    );
    this.regVentasSharedCollection = this.regVentaService.addRegVentaToCollectionIfMissing<IRegVenta>(
      this.regVentasSharedCollection,
      movimientoProducto.regVenta
    );
    this.ordensSharedCollection = this.ordenService.addOrdenToCollectionIfMissing<IOrden>(
      this.ordensSharedCollection,
      movimientoProducto.orden
    );
    this.regComprasSharedCollection = this.regComprasService.addRegComprasToCollectionIfMissing<IRegCompras>(
      this.regComprasSharedCollection,
      movimientoProducto.regCompras
    );
  }

  protected loadRelationshipsOptions(): void {
    this.productoService
      .query()
      .pipe(map((res: HttpResponse<IProducto[]>) => res.body ?? []))
      .pipe(
        map((productos: IProducto[]) =>
          this.productoService.addProductoToCollectionIfMissing<IProducto>(productos, this.movimientoProducto?.producto)
        )
      )
      .subscribe((productos: IProducto[]) => (this.productosSharedCollection = productos));

    this.regVentaService
      .query()
      .pipe(map((res: HttpResponse<IRegVenta[]>) => res.body ?? []))
      .pipe(
        map((regVentas: IRegVenta[]) =>
          this.regVentaService.addRegVentaToCollectionIfMissing<IRegVenta>(regVentas, this.movimientoProducto?.regVenta)
        )
      )
      .subscribe((regVentas: IRegVenta[]) => (this.regVentasSharedCollection = regVentas));

    this.ordenService
      .query()
      .pipe(map((res: HttpResponse<IOrden[]>) => res.body ?? []))
      .pipe(map((ordens: IOrden[]) => this.ordenService.addOrdenToCollectionIfMissing<IOrden>(ordens, this.movimientoProducto?.orden)))
      .subscribe((ordens: IOrden[]) => (this.ordensSharedCollection = ordens));

    this.regComprasService
      .query()
      .pipe(map((res: HttpResponse<IRegCompras[]>) => res.body ?? []))
      .pipe(
        map((regCompras: IRegCompras[]) =>
          this.regComprasService.addRegComprasToCollectionIfMissing<IRegCompras>(regCompras, this.movimientoProducto?.regCompras)
        )
      )
      .subscribe((regCompras: IRegCompras[]) => (this.regComprasSharedCollection = regCompras));
  }
}
