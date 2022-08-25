import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IMovimientoProducto } from '../movimiento-producto.model';
import { MovimientoProductoService } from '../service/movimiento-producto.service';

@Injectable({ providedIn: 'root' })
export class MovimientoProductoRoutingResolveService implements Resolve<IMovimientoProducto | null> {
  constructor(protected service: MovimientoProductoService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMovimientoProducto | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((movimientoProducto: HttpResponse<IMovimientoProducto>) => {
          if (movimientoProducto.body) {
            return of(movimientoProducto.body);
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
