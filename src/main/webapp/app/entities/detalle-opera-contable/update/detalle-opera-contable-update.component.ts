import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { DetalleOperaContableFormService, DetalleOperaContableFormGroup } from './detalle-opera-contable-form.service';
import { IDetalleOperaContable } from '../detalle-opera-contable.model';
import { DetalleOperaContableService } from '../service/detalle-opera-contable.service';
import { IPlanContable } from 'app/entities/plan-contable/plan-contable.model';
import { PlanContableService } from 'app/entities/plan-contable/service/plan-contable.service';
import { IOperaContable } from 'app/entities/opera-contable/opera-contable.model';
import { OperaContableService } from 'app/entities/opera-contable/service/opera-contable.service';

@Component({
  selector: 'jhi-detalle-opera-contable-update',
  templateUrl: './detalle-opera-contable-update.component.html',
})
export class DetalleOperaContableUpdateComponent implements OnInit {
  isSaving = false;
  detalleOperaContable: IDetalleOperaContable | null = null;

  planContablesSharedCollection: IPlanContable[] = [];
  operaContablesSharedCollection: IOperaContable[] = [];

  editForm: DetalleOperaContableFormGroup = this.detalleOperaContableFormService.createDetalleOperaContableFormGroup();

  constructor(
    protected detalleOperaContableService: DetalleOperaContableService,
    protected detalleOperaContableFormService: DetalleOperaContableFormService,
    protected planContableService: PlanContableService,
    protected operaContableService: OperaContableService,
    protected activatedRoute: ActivatedRoute
  ) {}

  comparePlanContable = (o1: IPlanContable | null, o2: IPlanContable | null): boolean =>
    this.planContableService.comparePlanContable(o1, o2);

  compareOperaContable = (o1: IOperaContable | null, o2: IOperaContable | null): boolean =>
    this.operaContableService.compareOperaContable(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detalleOperaContable }) => {
      this.detalleOperaContable = detalleOperaContable;
      if (detalleOperaContable) {
        this.updateForm(detalleOperaContable);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const detalleOperaContable = this.detalleOperaContableFormService.getDetalleOperaContable(this.editForm);
    if (detalleOperaContable.id !== null) {
      this.subscribeToSaveResponse(this.detalleOperaContableService.update(detalleOperaContable));
    } else {
      this.subscribeToSaveResponse(this.detalleOperaContableService.create(detalleOperaContable));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDetalleOperaContable>>): void {
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

  protected updateForm(detalleOperaContable: IDetalleOperaContable): void {
    this.detalleOperaContable = detalleOperaContable;
    this.detalleOperaContableFormService.resetForm(this.editForm, detalleOperaContable);

    this.planContablesSharedCollection = this.planContableService.addPlanContableToCollectionIfMissing<IPlanContable>(
      this.planContablesSharedCollection,
      detalleOperaContable.planContable
    );
    this.operaContablesSharedCollection = this.operaContableService.addOperaContableToCollectionIfMissing<IOperaContable>(
      this.operaContablesSharedCollection,
      detalleOperaContable.operaContable
    );
  }

  protected loadRelationshipsOptions(): void {
    this.planContableService
      .query()
      .pipe(map((res: HttpResponse<IPlanContable[]>) => res.body ?? []))
      .pipe(
        map((planContables: IPlanContable[]) =>
          this.planContableService.addPlanContableToCollectionIfMissing<IPlanContable>(
            planContables,
            this.detalleOperaContable?.planContable
          )
        )
      )
      .subscribe((planContables: IPlanContable[]) => (this.planContablesSharedCollection = planContables));

    this.operaContableService
      .query()
      .pipe(map((res: HttpResponse<IOperaContable[]>) => res.body ?? []))
      .pipe(
        map((operaContables: IOperaContable[]) =>
          this.operaContableService.addOperaContableToCollectionIfMissing<IOperaContable>(
            operaContables,
            this.detalleOperaContable?.operaContable
          )
        )
      )
      .subscribe((operaContables: IOperaContable[]) => (this.operaContablesSharedCollection = operaContables));
  }
}
