import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IHistoricoCaja, NewHistoricoCaja } from '../historico-caja.model';

export type PartialUpdateHistoricoCaja = Partial<IHistoricoCaja> & Pick<IHistoricoCaja, 'id'>;

type RestOf<T extends IHistoricoCaja | NewHistoricoCaja> = Omit<T, 'fecIniVig' | 'fecFinVig' | 'fecCrea' | 'fecModif'> & {
  fecIniVig?: string | null;
  fecFinVig?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestHistoricoCaja = RestOf<IHistoricoCaja>;

export type NewRestHistoricoCaja = RestOf<NewHistoricoCaja>;

export type PartialUpdateRestHistoricoCaja = RestOf<PartialUpdateHistoricoCaja>;

export type EntityResponseType = HttpResponse<IHistoricoCaja>;
export type EntityArrayResponseType = HttpResponse<IHistoricoCaja[]>;

@Injectable({ providedIn: 'root' })
export class HistoricoCajaService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/historico-cajas');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(historicoCaja: NewHistoricoCaja): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(historicoCaja);
    return this.http
      .post<RestHistoricoCaja>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(historicoCaja: IHistoricoCaja): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(historicoCaja);
    return this.http
      .put<RestHistoricoCaja>(`${this.resourceUrl}/${this.getHistoricoCajaIdentifier(historicoCaja)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(historicoCaja: PartialUpdateHistoricoCaja): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(historicoCaja);
    return this.http
      .patch<RestHistoricoCaja>(`${this.resourceUrl}/${this.getHistoricoCajaIdentifier(historicoCaja)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestHistoricoCaja>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestHistoricoCaja[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getHistoricoCajaIdentifier(historicoCaja: Pick<IHistoricoCaja, 'id'>): string {
    return historicoCaja.id;
  }

  compareHistoricoCaja(o1: Pick<IHistoricoCaja, 'id'> | null, o2: Pick<IHistoricoCaja, 'id'> | null): boolean {
    return o1 && o2 ? this.getHistoricoCajaIdentifier(o1) === this.getHistoricoCajaIdentifier(o2) : o1 === o2;
  }

  addHistoricoCajaToCollectionIfMissing<Type extends Pick<IHistoricoCaja, 'id'>>(
    historicoCajaCollection: Type[],
    ...historicoCajasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const historicoCajas: Type[] = historicoCajasToCheck.filter(isPresent);
    if (historicoCajas.length > 0) {
      const historicoCajaCollectionIdentifiers = historicoCajaCollection.map(
        historicoCajaItem => this.getHistoricoCajaIdentifier(historicoCajaItem)!
      );
      const historicoCajasToAdd = historicoCajas.filter(historicoCajaItem => {
        const historicoCajaIdentifier = this.getHistoricoCajaIdentifier(historicoCajaItem);
        if (historicoCajaCollectionIdentifiers.includes(historicoCajaIdentifier)) {
          return false;
        }
        historicoCajaCollectionIdentifiers.push(historicoCajaIdentifier);
        return true;
      });
      return [...historicoCajasToAdd, ...historicoCajaCollection];
    }
    return historicoCajaCollection;
  }

  protected convertDateFromClient<T extends IHistoricoCaja | NewHistoricoCaja | PartialUpdateHistoricoCaja>(historicoCaja: T): RestOf<T> {
    return {
      ...historicoCaja,
      fecIniVig: historicoCaja.fecIniVig?.toJSON() ?? null,
      fecFinVig: historicoCaja.fecFinVig?.toJSON() ?? null,
      fecCrea: historicoCaja.fecCrea?.toJSON() ?? null,
      fecModif: historicoCaja.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restHistoricoCaja: RestHistoricoCaja): IHistoricoCaja {
    return {
      ...restHistoricoCaja,
      fecIniVig: restHistoricoCaja.fecIniVig ? dayjs(restHistoricoCaja.fecIniVig) : undefined,
      fecFinVig: restHistoricoCaja.fecFinVig ? dayjs(restHistoricoCaja.fecFinVig) : undefined,
      fecCrea: restHistoricoCaja.fecCrea ? dayjs(restHistoricoCaja.fecCrea) : undefined,
      fecModif: restHistoricoCaja.fecModif ? dayjs(restHistoricoCaja.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestHistoricoCaja>): HttpResponse<IHistoricoCaja> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestHistoricoCaja[]>): HttpResponse<IHistoricoCaja[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
