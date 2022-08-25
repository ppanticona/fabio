import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { PlanContableFormService, PlanContableFormGroup } from './plan-contable-form.service';
import { IPlanContable } from '../plan-contable.model';
import { PlanContableService } from '../service/plan-contable.service';

@Component({
  selector: 'jhi-plan-contable-update',
  templateUrl: './plan-contable-update.component.html',
})
export class PlanContableUpdateComponent implements OnInit {
  isSaving = false;
  planContable: IPlanContable | null = null;

  editForm: PlanContableFormGroup = this.planContableFormService.createPlanContableFormGroup();

  constructor(
    protected planContableService: PlanContableService,
    protected planContableFormService: PlanContableFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ planContable }) => {
      this.planContable = planContable;
      if (planContable) {
        this.updateForm(planContable);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const planContable = this.planContableFormService.getPlanContable(this.editForm);
    if (planContable.id !== null) {
      this.subscribeToSaveResponse(this.planContableService.update(planContable));
    } else {
      this.subscribeToSaveResponse(this.planContableService.create(planContable));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanContable>>): void {
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

  protected updateForm(planContable: IPlanContable): void {
    this.planContable = planContable;
    this.planContableFormService.resetForm(this.editForm, planContable);
  }
}
