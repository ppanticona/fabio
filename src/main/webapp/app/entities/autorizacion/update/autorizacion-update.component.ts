import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { AutorizacionFormService, AutorizacionFormGroup } from './autorizacion-form.service';
import { IAutorizacion } from '../autorizacion.model';
import { AutorizacionService } from '../service/autorizacion.service';

@Component({
  selector: 'jhi-autorizacion-update',
  templateUrl: './autorizacion-update.component.html',
})
export class AutorizacionUpdateComponent implements OnInit {
  isSaving = false;
  autorizacion: IAutorizacion | null = null;

  editForm: AutorizacionFormGroup = this.autorizacionFormService.createAutorizacionFormGroup();

  constructor(
    protected autorizacionService: AutorizacionService,
    protected autorizacionFormService: AutorizacionFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autorizacion }) => {
      this.autorizacion = autorizacion;
      if (autorizacion) {
        this.updateForm(autorizacion);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const autorizacion = this.autorizacionFormService.getAutorizacion(this.editForm);
    if (autorizacion.id !== null) {
      this.subscribeToSaveResponse(this.autorizacionService.update(autorizacion));
    } else {
      this.subscribeToSaveResponse(this.autorizacionService.create(autorizacion));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAutorizacion>>): void {
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

  protected updateForm(autorizacion: IAutorizacion): void {
    this.autorizacion = autorizacion;
    this.autorizacionFormService.resetForm(this.editForm, autorizacion);
  }
}
