import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { RegComprasFormService, RegComprasFormGroup } from './reg-compras-form.service';
import { IRegCompras } from '../reg-compras.model';
import { RegComprasService } from '../service/reg-compras.service';
import { IOrden } from 'app/entities/orden/orden.model';
import { OrdenService } from 'app/entities/orden/service/orden.service';

@Component({
  selector: 'jhi-reg-compras-update',
  templateUrl: './reg-compras-update.component.html',
})
export class RegComprasUpdateComponent implements OnInit {
  isSaving = false;
  regCompras: IRegCompras | null = null;

  ordensSharedCollection: IOrden[] = [];

  editForm: RegComprasFormGroup = this.regComprasFormService.createRegComprasFormGroup();

  constructor(
    protected regComprasService: RegComprasService,
    protected regComprasFormService: RegComprasFormService,
    protected ordenService: OrdenService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareOrden = (o1: IOrden | null, o2: IOrden | null): boolean => this.ordenService.compareOrden(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ regCompras }) => {
      this.regCompras = regCompras;
      if (regCompras) {
        this.updateForm(regCompras);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const regCompras = this.regComprasFormService.getRegCompras(this.editForm);
    if (regCompras.id !== null) {
      this.subscribeToSaveResponse(this.regComprasService.update(regCompras));
    } else {
      this.subscribeToSaveResponse(this.regComprasService.create(regCompras));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRegCompras>>): void {
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

  protected updateForm(regCompras: IRegCompras): void {
    this.regCompras = regCompras;
    this.regComprasFormService.resetForm(this.editForm, regCompras);

    this.ordensSharedCollection = this.ordenService.addOrdenToCollectionIfMissing<IOrden>(this.ordensSharedCollection, regCompras.orden);
  }

  protected loadRelationshipsOptions(): void {
    this.ordenService
      .query()
      .pipe(map((res: HttpResponse<IOrden[]>) => res.body ?? []))
      .pipe(map((ordens: IOrden[]) => this.ordenService.addOrdenToCollectionIfMissing<IOrden>(ordens, this.regCompras?.orden)))
      .subscribe((ordens: IOrden[]) => (this.ordensSharedCollection = ordens));
  }
}
