import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDetalleOperaContable } from '../detalle-opera-contable.model';

@Component({
  selector: 'jhi-detalle-opera-contable-detail',
  templateUrl: './detalle-opera-contable-detail.component.html',
})
export class DetalleOperaContableDetailComponent implements OnInit {
  detalleOperaContable: IDetalleOperaContable | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detalleOperaContable }) => {
      this.detalleOperaContable = detalleOperaContable;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
