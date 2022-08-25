import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { LibDiarioFormService, LibDiarioFormGroup } from './lib-diario-form.service';
import { ILibDiario } from '../lib-diario.model';
import { LibDiarioService } from '../service/lib-diario.service';

@Component({
  selector: 'jhi-lib-diario-update',
  templateUrl: './lib-diario-update.component.html',
})
export class LibDiarioUpdateComponent implements OnInit {
  isSaving = false;
  libDiario: ILibDiario | null = null;

  editForm: LibDiarioFormGroup = this.libDiarioFormService.createLibDiarioFormGroup();

  constructor(
    protected libDiarioService: LibDiarioService,
    protected libDiarioFormService: LibDiarioFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ libDiario }) => {
      this.libDiario = libDiario;
      if (libDiario) {
        this.updateForm(libDiario);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const libDiario = this.libDiarioFormService.getLibDiario(this.editForm);
    if (libDiario.id !== null) {
      this.subscribeToSaveResponse(this.libDiarioService.update(libDiario));
    } else {
      this.subscribeToSaveResponse(this.libDiarioService.create(libDiario));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILibDiario>>): void {
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

  protected updateForm(libDiario: ILibDiario): void {
    this.libDiario = libDiario;
    this.libDiarioFormService.resetForm(this.editForm, libDiario);
  }
}
