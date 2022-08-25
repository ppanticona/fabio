import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IPlanContable } from '../plan-contable.model';
import { PlanContableService } from '../service/plan-contable.service';

@Injectable({ providedIn: 'root' })
export class PlanContableRoutingResolveService implements Resolve<IPlanContable | null> {
  constructor(protected service: PlanContableService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPlanContable | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((planContable: HttpResponse<IPlanContable>) => {
          if (planContable.body) {
            return of(planContable.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
