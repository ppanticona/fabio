import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDetalleOperaContable } from '../detalle-opera-contable.model';
import { DetalleOperaContableService } from '../service/detalle-opera-contable.service';

@Injectable({ providedIn: 'root' })
export class DetalleOperaContableRoutingResolveService implements Resolve<IDetalleOperaContable | null> {
  constructor(protected service: DetalleOperaContableService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDetalleOperaContable | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((detalleOperaContable: HttpResponse<IDetalleOperaContable>) => {
          if (detalleOperaContable.body) {
            return of(detalleOperaContable.body);
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
