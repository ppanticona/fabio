import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IHistoricoCaja } from '../historico-caja.model';
import { HistoricoCajaService } from '../service/historico-caja.service';

@Injectable({ providedIn: 'root' })
export class HistoricoCajaRoutingResolveService implements Resolve<IHistoricoCaja | null> {
  constructor(protected service: HistoricoCajaService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IHistoricoCaja | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((historicoCaja: HttpResponse<IHistoricoCaja>) => {
          if (historicoCaja.body) {
            return of(historicoCaja.body);
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
