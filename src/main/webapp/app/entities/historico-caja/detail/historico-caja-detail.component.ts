import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHistoricoCaja } from '../historico-caja.model';

@Component({
  selector: 'jhi-historico-caja-detail',
  templateUrl: './historico-caja-detail.component.html',
})
export class HistoricoCajaDetailComponent implements OnInit {
  historicoCaja: IHistoricoCaja | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ historicoCaja }) => {
      this.historicoCaja = historicoCaja;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
