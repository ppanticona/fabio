import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { HistoricoCajaFormService, HistoricoCajaFormGroup } from './historico-caja-form.service';
import { IHistoricoCaja } from '../historico-caja.model';
import { HistoricoCajaService } from '../service/historico-caja.service';
import { ICaja } from 'app/entities/caja/caja.model';
import { CajaService } from 'app/entities/caja/service/caja.service';

@Component({
  selector: 'jhi-historico-caja-update',
  templateUrl: './historico-caja-update.component.html',
})
export class HistoricoCajaUpdateComponent implements OnInit {
  isSaving = false;
  historicoCaja: IHistoricoCaja | null = null;

  cajasSharedCollection: ICaja[] = [];

  editForm: HistoricoCajaFormGroup = this.historicoCajaFormService.createHistoricoCajaFormGroup();

  constructor(
    protected historicoCajaService: HistoricoCajaService,
    protected historicoCajaFormService: HistoricoCajaFormService,
    protected cajaService: CajaService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareCaja = (o1: ICaja | null, o2: ICaja | null): boolean => this.cajaService.compareCaja(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ historicoCaja }) => {
      this.historicoCaja = historicoCaja;
      if (historicoCaja) {
        this.updateForm(historicoCaja);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const historicoCaja = this.historicoCajaFormService.getHistoricoCaja(this.editForm);
    if (historicoCaja.id !== null) {
      this.subscribeToSaveResponse(this.historicoCajaService.update(historicoCaja));
    } else {
      this.subscribeToSaveResponse(this.historicoCajaService.create(historicoCaja));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHistoricoCaja>>): void {
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

  protected updateForm(historicoCaja: IHistoricoCaja): void {
    this.historicoCaja = historicoCaja;
    this.historicoCajaFormService.resetForm(this.editForm, historicoCaja);

    this.cajasSharedCollection = this.cajaService.addCajaToCollectionIfMissing<ICaja>(this.cajasSharedCollection, historicoCaja.caja);
  }

  protected loadRelationshipsOptions(): void {
    this.cajaService
      .query()
      .pipe(map((res: HttpResponse<ICaja[]>) => res.body ?? []))
      .pipe(map((cajas: ICaja[]) => this.cajaService.addCajaToCollectionIfMissing<ICaja>(cajas, this.historicoCaja?.caja)))
      .subscribe((cajas: ICaja[]) => (this.cajasSharedCollection = cajas));
  }
}
