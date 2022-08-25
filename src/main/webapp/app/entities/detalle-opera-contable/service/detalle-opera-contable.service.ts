import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDetalleOperaContable, NewDetalleOperaContable } from '../detalle-opera-contable.model';

export type PartialUpdateDetalleOperaContable = Partial<IDetalleOperaContable> & Pick<IDetalleOperaContable, 'id'>;

type RestOf<T extends IDetalleOperaContable | NewDetalleOperaContable> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestDetalleOperaContable = RestOf<IDetalleOperaContable>;

export type NewRestDetalleOperaContable = RestOf<NewDetalleOperaContable>;

export type PartialUpdateRestDetalleOperaContable = RestOf<PartialUpdateDetalleOperaContable>;

export type EntityResponseType = HttpResponse<IDetalleOperaContable>;
export type EntityArrayResponseType = HttpResponse<IDetalleOperaContable[]>;

@Injectable({ providedIn: 'root' })
export class DetalleOperaContableService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/detalle-opera-contables');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(detalleOperaContable: NewDetalleOperaContable): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(detalleOperaContable);
    return this.http
      .post<RestDetalleOperaContable>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(detalleOperaContable: IDetalleOperaContable): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(detalleOperaContable);
    return this.http
      .put<RestDetalleOperaContable>(`${this.resourceUrl}/${this.getDetalleOperaContableIdentifier(detalleOperaContable)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(detalleOperaContable: PartialUpdateDetalleOperaContable): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(detalleOperaContable);
    return this.http
      .patch<RestDetalleOperaContable>(`${this.resourceUrl}/${this.getDetalleOperaContableIdentifier(detalleOperaContable)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestDetalleOperaContable>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestDetalleOperaContable[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getDetalleOperaContableIdentifier(detalleOperaContable: Pick<IDetalleOperaContable, 'id'>): string {
    return detalleOperaContable.id;
  }

  compareDetalleOperaContable(o1: Pick<IDetalleOperaContable, 'id'> | null, o2: Pick<IDetalleOperaContable, 'id'> | null): boolean {
    return o1 && o2 ? this.getDetalleOperaContableIdentifier(o1) === this.getDetalleOperaContableIdentifier(o2) : o1 === o2;
  }

  addDetalleOperaContableToCollectionIfMissing<Type extends Pick<IDetalleOperaContable, 'id'>>(
    detalleOperaContableCollection: Type[],
    ...detalleOperaContablesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const detalleOperaContables: Type[] = detalleOperaContablesToCheck.filter(isPresent);
    if (detalleOperaContables.length > 0) {
      const detalleOperaContableCollectionIdentifiers = detalleOperaContableCollection.map(
        detalleOperaContableItem => this.getDetalleOperaContableIdentifier(detalleOperaContableItem)!
      );
      const detalleOperaContablesToAdd = detalleOperaContables.filter(detalleOperaContableItem => {
        const detalleOperaContableIdentifier = this.getDetalleOperaContableIdentifier(detalleOperaContableItem);
        if (detalleOperaContableCollectionIdentifiers.includes(detalleOperaContableIdentifier)) {
          return false;
        }
        detalleOperaContableCollectionIdentifiers.push(detalleOperaContableIdentifier);
        return true;
      });
      return [...detalleOperaContablesToAdd, ...detalleOperaContableCollection];
    }
    return detalleOperaContableCollection;
  }

  protected convertDateFromClient<T extends IDetalleOperaContable | NewDetalleOperaContable | PartialUpdateDetalleOperaContable>(
    detalleOperaContable: T
  ): RestOf<T> {
    return {
      ...detalleOperaContable,
      fecCrea: detalleOperaContable.fecCrea?.toJSON() ?? null,
      fecModif: detalleOperaContable.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restDetalleOperaContable: RestDetalleOperaContable): IDetalleOperaContable {
    return {
      ...restDetalleOperaContable,
      fecCrea: restDetalleOperaContable.fecCrea ? dayjs(restDetalleOperaContable.fecCrea) : undefined,
      fecModif: restDetalleOperaContable.fecModif ? dayjs(restDetalleOperaContable.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestDetalleOperaContable>): HttpResponse<IDetalleOperaContable> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestDetalleOperaContable[]>): HttpResponse<IDetalleOperaContable[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
