import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IMovimientoProducto, NewMovimientoProducto } from '../movimiento-producto.model';

export type PartialUpdateMovimientoProducto = Partial<IMovimientoProducto> & Pick<IMovimientoProducto, 'id'>;

type RestOf<T extends IMovimientoProducto | NewMovimientoProducto> = Omit<T, 'fecMovimiento' | 'fecCrea' | 'fecModif'> & {
  fecMovimiento?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestMovimientoProducto = RestOf<IMovimientoProducto>;

export type NewRestMovimientoProducto = RestOf<NewMovimientoProducto>;

export type PartialUpdateRestMovimientoProducto = RestOf<PartialUpdateMovimientoProducto>;

export type EntityResponseType = HttpResponse<IMovimientoProducto>;
export type EntityArrayResponseType = HttpResponse<IMovimientoProducto[]>;

@Injectable({ providedIn: 'root' })
export class MovimientoProductoService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/movimiento-productos');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(movimientoProducto: NewMovimientoProducto): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(movimientoProducto);
    return this.http
      .post<RestMovimientoProducto>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(movimientoProducto: IMovimientoProducto): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(movimientoProducto);
    return this.http
      .put<RestMovimientoProducto>(`${this.resourceUrl}/${this.getMovimientoProductoIdentifier(movimientoProducto)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(movimientoProducto: PartialUpdateMovimientoProducto): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(movimientoProducto);
    return this.http
      .patch<RestMovimientoProducto>(`${this.resourceUrl}/${this.getMovimientoProductoIdentifier(movimientoProducto)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestMovimientoProducto>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestMovimientoProducto[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getMovimientoProductoIdentifier(movimientoProducto: Pick<IMovimientoProducto, 'id'>): string {
    return movimientoProducto.id;
  }

  compareMovimientoProducto(o1: Pick<IMovimientoProducto, 'id'> | null, o2: Pick<IMovimientoProducto, 'id'> | null): boolean {
    return o1 && o2 ? this.getMovimientoProductoIdentifier(o1) === this.getMovimientoProductoIdentifier(o2) : o1 === o2;
  }

  addMovimientoProductoToCollectionIfMissing<Type extends Pick<IMovimientoProducto, 'id'>>(
    movimientoProductoCollection: Type[],
    ...movimientoProductosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const movimientoProductos: Type[] = movimientoProductosToCheck.filter(isPresent);
    if (movimientoProductos.length > 0) {
      const movimientoProductoCollectionIdentifiers = movimientoProductoCollection.map(
        movimientoProductoItem => this.getMovimientoProductoIdentifier(movimientoProductoItem)!
      );
      const movimientoProductosToAdd = movimientoProductos.filter(movimientoProductoItem => {
        const movimientoProductoIdentifier = this.getMovimientoProductoIdentifier(movimientoProductoItem);
        if (movimientoProductoCollectionIdentifiers.includes(movimientoProductoIdentifier)) {
          return false;
        }
        movimientoProductoCollectionIdentifiers.push(movimientoProductoIdentifier);
        return true;
      });
      return [...movimientoProductosToAdd, ...movimientoProductoCollection];
    }
    return movimientoProductoCollection;
  }

  protected convertDateFromClient<T extends IMovimientoProducto | NewMovimientoProducto | PartialUpdateMovimientoProducto>(
    movimientoProducto: T
  ): RestOf<T> {
    return {
      ...movimientoProducto,
      fecMovimiento: movimientoProducto.fecMovimiento?.toJSON() ?? null,
      fecCrea: movimientoProducto.fecCrea?.toJSON() ?? null,
      fecModif: movimientoProducto.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restMovimientoProducto: RestMovimientoProducto): IMovimientoProducto {
    return {
      ...restMovimientoProducto,
      fecMovimiento: restMovimientoProducto.fecMovimiento ? dayjs(restMovimientoProducto.fecMovimiento) : undefined,
      fecCrea: restMovimientoProducto.fecCrea ? dayjs(restMovimientoProducto.fecCrea) : undefined,
      fecModif: restMovimientoProducto.fecModif ? dayjs(restMovimientoProducto.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestMovimientoProducto>): HttpResponse<IMovimientoProducto> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestMovimientoProducto[]>): HttpResponse<IMovimientoProducto[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
