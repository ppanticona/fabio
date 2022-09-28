import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { OrdenFormService, OrdenFormGroup } from './orden-form.service';
import { IOrden } from '../orden.model';
import { OrdenService } from '../service/orden.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { ICliente } from 'app/entities/cliente/cliente.model';
import { ClienteService } from 'app/entities/cliente/service/cliente.service';
import { IProveedor } from 'app/entities/proveedor/proveedor.model';
import { ProveedorService } from 'app/entities/proveedor/service/proveedor.service';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';
import { AutorizacionService } from 'app/entities/autorizacion/service/autorizacion.service';

@Component({
  selector: 'jhi-orden-update',
  templateUrl: './orden-update.component.html',
})
export class OrdenUpdateComponent implements OnInit {
  isSaving = false;
  orden: IOrden | null = null;

  clientesSharedCollection: ICliente[] = [];
  proveedorsSharedCollection: IProveedor[] = [];
  autorizacionsSharedCollection: IAutorizacion[] = [];

  editForm: OrdenFormGroup = this.ordenFormService.createOrdenFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected ordenService: OrdenService,
    protected ordenFormService: OrdenFormService,
    protected clienteService: ClienteService,
    protected proveedorService: ProveedorService,
    protected autorizacionService: AutorizacionService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareCliente = (o1: ICliente | null, o2: ICliente | null): boolean => this.clienteService.compareCliente(o1, o2);

  compareProveedor = (o1: IProveedor | null, o2: IProveedor | null): boolean => this.proveedorService.compareProveedor(o1, o2);

  compareAutorizacion = (o1: IAutorizacion | null, o2: IAutorizacion | null): boolean =>
    this.autorizacionService.compareAutorizacion(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orden }) => {
      this.orden = orden;
      if (orden) {
        this.updateForm(orden);
      }

      this.loadRelationshipsOptions();
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('fabioApp.error', { message: err.message })),
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const orden = this.ordenFormService.getOrden(this.editForm);
    if (orden.id !== null) {
      this.subscribeToSaveResponse(this.ordenService.update(orden));
    } else {
      this.subscribeToSaveResponse(this.ordenService.create(orden));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrden>>): void {
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

  protected updateForm(orden: IOrden): void {
    this.orden = orden;
    this.ordenFormService.resetForm(this.editForm, orden);

    this.clientesSharedCollection = this.clienteService.addClienteToCollectionIfMissing<ICliente>(
      this.clientesSharedCollection,
      orden.cliente
    );
    this.proveedorsSharedCollection = this.proveedorService.addProveedorToCollectionIfMissing<IProveedor>(
      this.proveedorsSharedCollection,
      orden.proveedor
    );
    this.autorizacionsSharedCollection = this.autorizacionService.addAutorizacionToCollectionIfMissing<IAutorizacion>(
      this.autorizacionsSharedCollection,
      orden.autorizacion
    );
  }

  protected loadRelationshipsOptions(): void {
    this.clienteService
      .query()
      .pipe(map((res: HttpResponse<ICliente[]>) => res.body ?? []))
      .pipe(map((clientes: ICliente[]) => this.clienteService.addClienteToCollectionIfMissing<ICliente>(clientes, this.orden?.cliente)))
      .subscribe((clientes: ICliente[]) => (this.clientesSharedCollection = clientes));

    this.proveedorService
      .query()
      .pipe(map((res: HttpResponse<IProveedor[]>) => res.body ?? []))
      .pipe(
        map((proveedors: IProveedor[]) =>
          this.proveedorService.addProveedorToCollectionIfMissing<IProveedor>(proveedors, this.orden?.proveedor)
        )
      )
      .subscribe((proveedors: IProveedor[]) => (this.proveedorsSharedCollection = proveedors));

    this.autorizacionService
      .query()
      .pipe(map((res: HttpResponse<IAutorizacion[]>) => res.body ?? []))
      .pipe(
        map((autorizacions: IAutorizacion[]) =>
          this.autorizacionService.addAutorizacionToCollectionIfMissing<IAutorizacion>(autorizacions, this.orden?.autorizacion)
        )
      )
      .subscribe((autorizacions: IAutorizacion[]) => (this.autorizacionsSharedCollection = autorizacions));
  }
}
