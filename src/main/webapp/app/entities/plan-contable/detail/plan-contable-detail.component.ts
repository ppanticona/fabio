import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlanContable } from '../plan-contable.model';

@Component({
  selector: 'jhi-plan-contable-detail',
  templateUrl: './plan-contable-detail.component.html',
})
export class PlanContableDetailComponent implements OnInit {
  planContable: IPlanContable | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ planContable }) => {
      this.planContable = planContable;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
