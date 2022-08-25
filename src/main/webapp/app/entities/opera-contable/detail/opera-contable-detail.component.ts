import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOperaContable } from '../opera-contable.model';

@Component({
  selector: 'jhi-opera-contable-detail',
  templateUrl: './opera-contable-detail.component.html',
})
export class OperaContableDetailComponent implements OnInit {
  operaContable: IOperaContable | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operaContable }) => {
      this.operaContable = operaContable;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
