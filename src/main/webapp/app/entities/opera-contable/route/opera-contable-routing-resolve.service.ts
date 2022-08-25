import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOperaContable } from '../opera-contable.model';
import { OperaContableService } from '../service/opera-contable.service';

@Injectable({ providedIn: 'root' })
export class OperaContableRoutingResolveService implements Resolve<IOperaContable | null> {
  constructor(protected service: OperaContableService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOperaContable | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((operaContable: HttpResponse<IOperaContable>) => {
          if (operaContable.body) {
            return of(operaContable.body);
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
