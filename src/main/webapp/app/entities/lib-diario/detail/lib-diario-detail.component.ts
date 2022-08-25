import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILibDiario } from '../lib-diario.model';

@Component({
  selector: 'jhi-lib-diario-detail',
  templateUrl: './lib-diario-detail.component.html',
})
export class LibDiarioDetailComponent implements OnInit {
  libDiario: ILibDiario | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ libDiario }) => {
      this.libDiario = libDiario;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
