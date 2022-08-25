import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ILibDiario } from '../lib-diario.model';
import { LibDiarioService } from '../service/lib-diario.service';

@Injectable({ providedIn: 'root' })
export class LibDiarioRoutingResolveService implements Resolve<ILibDiario | null> {
  constructor(protected service: LibDiarioService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILibDiario | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((libDiario: HttpResponse<ILibDiario>) => {
          if (libDiario.body) {
            return of(libDiario.body);
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
