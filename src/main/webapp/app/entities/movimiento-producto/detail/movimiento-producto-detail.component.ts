import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMovimientoProducto } from '../movimiento-producto.model';

@Component({
  selector: 'jhi-movimiento-producto-detail',
  templateUrl: './movimiento-producto-detail.component.html',
})
export class MovimientoProductoDetailComponent implements OnInit {
  movimientoProducto: IMovimientoProducto | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ movimientoProducto }) => {
      this.movimientoProducto = movimientoProducto;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
