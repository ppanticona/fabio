import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { MovimientoCajaFormService, MovimientoCajaFormGroup } from './movimiento-caja-form.service';
import { IMovimientoCaja } from '../movimiento-caja.model';
import { MovimientoCajaService } from '../service/movimiento-caja.service';
import { IHistoricoCaja } from 'app/entities/historico-caja/historico-caja.model';
import { HistoricoCajaService } from 'app/entities/historico-caja/service/historico-caja.service';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';
import { AutorizacionService } from 'app/entities/autorizacion/service/autorizacion.service';

@Component({
  selector: 'jhi-movimiento-caja-update',
  templateUrl: './movimiento-caja-update.component.html',
})
export class MovimientoCajaUpdateComponent implements OnInit {
  isSaving = false;
  movimientoCaja: IMovimientoCaja | null = null;

  historicoCajasSharedCollection: IHistoricoCaja[] = [];
  autorizacionsSharedCollection: IAutorizacion[] = [];

  editForm: MovimientoCajaFormGroup = this.movimientoCajaFormService.createMovimientoCajaFormGroup();

  constructor(
    protected movimientoCajaService: MovimientoCajaService,
    protected movimientoCajaFormService: MovimientoCajaFormService,
    protected historicoCajaService: HistoricoCajaService,
    protected autorizacionService: AutorizacionService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareHistoricoCaja = (o1: IHistoricoCaja | null, o2: IHistoricoCaja | null): boolean =>
    this.historicoCajaService.compareHistoricoCaja(o1, o2);

  compareAutorizacion = (o1: IAutorizacion | null, o2: IAutorizacion | null): boolean =>
    this.autorizacionService.compareAutorizacion(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ movimientoCaja }) => {
      this.movimientoCaja = movimientoCaja;
      if (movimientoCaja) {
        this.updateForm(movimientoCaja);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const movimientoCaja = this.movimientoCajaFormService.getMovimientoCaja(this.editForm);
    if (movimientoCaja.id !== null) {
      this.subscribeToSaveResponse(this.movimientoCajaService.update(movimientoCaja));
    } else {
      this.subscribeToSaveResponse(this.movimientoCajaService.create(movimientoCaja));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMovimientoCaja>>): void {
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

  protected updateForm(movimientoCaja: IMovimientoCaja): void {
    this.movimientoCaja = movimientoCaja;
    this.movimientoCajaFormService.resetForm(this.editForm, movimientoCaja);

    this.historicoCajasSharedCollection = this.historicoCajaService.addHistoricoCajaToCollectionIfMissing<IHistoricoCaja>(
      this.historicoCajasSharedCollection,
      movimientoCaja.historicoCaja
    );
    this.autorizacionsSharedCollection = this.autorizacionService.addAutorizacionToCollectionIfMissing<IAutorizacion>(
      this.autorizacionsSharedCollection,
      movimientoCaja.autorizacion
    );
  }

  protected loadRelationshipsOptions(): void {
    this.historicoCajaService
      .query()
      .pipe(map((res: HttpResponse<IHistoricoCaja[]>) => res.body ?? []))
      .pipe(
        map((historicoCajas: IHistoricoCaja[]) =>
          this.historicoCajaService.addHistoricoCajaToCollectionIfMissing<IHistoricoCaja>(
            historicoCajas,
            this.movimientoCaja?.historicoCaja
          )
        )
      )
      .subscribe((historicoCajas: IHistoricoCaja[]) => (this.historicoCajasSharedCollection = historicoCajas));

    this.autorizacionService
      .query()
      .pipe(map((res: HttpResponse<IAutorizacion[]>) => res.body ?? []))
      .pipe(
        map((autorizacions: IAutorizacion[]) =>
          this.autorizacionService.addAutorizacionToCollectionIfMissing<IAutorizacion>(autorizacions, this.movimientoCaja?.autorizacion)
        )
      )
      .subscribe((autorizacions: IAutorizacion[]) => (this.autorizacionsSharedCollection = autorizacions));
  }
}
