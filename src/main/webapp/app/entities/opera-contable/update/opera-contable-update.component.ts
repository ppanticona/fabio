import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { OperaContableFormService, OperaContableFormGroup } from './opera-contable-form.service';
import { IOperaContable } from '../opera-contable.model';
import { OperaContableService } from '../service/opera-contable.service';

@Component({
  selector: 'jhi-opera-contable-update',
  templateUrl: './opera-contable-update.component.html',
})
export class OperaContableUpdateComponent implements OnInit {
  isSaving = false;
  operaContable: IOperaContable | null = null;

  editForm: OperaContableFormGroup = this.operaContableFormService.createOperaContableFormGroup();

  constructor(
    protected operaContableService: OperaContableService,
    protected operaContableFormService: OperaContableFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operaContable }) => {
      this.operaContable = operaContable;
      if (operaContable) {
        this.updateForm(operaContable);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const operaContable = this.operaContableFormService.getOperaContable(this.editForm);
    if (operaContable.id !== null) {
      this.subscribeToSaveResponse(this.operaContableService.update(operaContable));
    } else {
      this.subscribeToSaveResponse(this.operaContableService.create(operaContable));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOperaContable>>): void {
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

  protected updateForm(operaContable: IOperaContable): void {
    this.operaContable = operaContable;
    this.operaContableFormService.resetForm(this.editForm, operaContable);
  }
}
