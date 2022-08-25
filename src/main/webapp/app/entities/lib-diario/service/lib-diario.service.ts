import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ILibDiario, NewLibDiario } from '../lib-diario.model';

export type PartialUpdateLibDiario = Partial<ILibDiario> & Pick<ILibDiario, 'id'>;

type RestOf<T extends ILibDiario | NewLibDiario> = Omit<T, 'fecContable' | 'fecVenc' | 'fecOpeEmi' | 'fecCrea' | 'fecModif'> & {
  fecContable?: string | null;
  fecVenc?: string | null;
  fecOpeEmi?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestLibDiario = RestOf<ILibDiario>;

export type NewRestLibDiario = RestOf<NewLibDiario>;

export type PartialUpdateRestLibDiario = RestOf<PartialUpdateLibDiario>;

export type EntityResponseType = HttpResponse<ILibDiario>;
export type EntityArrayResponseType = HttpResponse<ILibDiario[]>;

@Injectable({ providedIn: 'root' })
export class LibDiarioService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/lib-diarios');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(libDiario: NewLibDiario): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(libDiario);
    return this.http
      .post<RestLibDiario>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(libDiario: ILibDiario): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(libDiario);
    return this.http
      .put<RestLibDiario>(`${this.resourceUrl}/${this.getLibDiarioIdentifier(libDiario)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(libDiario: PartialUpdateLibDiario): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(libDiario);
    return this.http
      .patch<RestLibDiario>(`${this.resourceUrl}/${this.getLibDiarioIdentifier(libDiario)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestLibDiario>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestLibDiario[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getLibDiarioIdentifier(libDiario: Pick<ILibDiario, 'id'>): string {
    return libDiario.id;
  }

  compareLibDiario(o1: Pick<ILibDiario, 'id'> | null, o2: Pick<ILibDiario, 'id'> | null): boolean {
    return o1 && o2 ? this.getLibDiarioIdentifier(o1) === this.getLibDiarioIdentifier(o2) : o1 === o2;
  }

  addLibDiarioToCollectionIfMissing<Type extends Pick<ILibDiario, 'id'>>(
    libDiarioCollection: Type[],
    ...libDiariosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const libDiarios: Type[] = libDiariosToCheck.filter(isPresent);
    if (libDiarios.length > 0) {
      const libDiarioCollectionIdentifiers = libDiarioCollection.map(libDiarioItem => this.getLibDiarioIdentifier(libDiarioItem)!);
      const libDiariosToAdd = libDiarios.filter(libDiarioItem => {
        const libDiarioIdentifier = this.getLibDiarioIdentifier(libDiarioItem);
        if (libDiarioCollectionIdentifiers.includes(libDiarioIdentifier)) {
          return false;
        }
        libDiarioCollectionIdentifiers.push(libDiarioIdentifier);
        return true;
      });
      return [...libDiariosToAdd, ...libDiarioCollection];
    }
    return libDiarioCollection;
  }

  protected convertDateFromClient<T extends ILibDiario | NewLibDiario | PartialUpdateLibDiario>(libDiario: T): RestOf<T> {
    return {
      ...libDiario,
      fecContable: libDiario.fecContable?.toJSON() ?? null,
      fecVenc: libDiario.fecVenc?.toJSON() ?? null,
      fecOpeEmi: libDiario.fecOpeEmi?.toJSON() ?? null,
      fecCrea: libDiario.fecCrea?.toJSON() ?? null,
      fecModif: libDiario.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restLibDiario: RestLibDiario): ILibDiario {
    return {
      ...restLibDiario,
      fecContable: restLibDiario.fecContable ? dayjs(restLibDiario.fecContable) : undefined,
      fecVenc: restLibDiario.fecVenc ? dayjs(restLibDiario.fecVenc) : undefined,
      fecOpeEmi: restLibDiario.fecOpeEmi ? dayjs(restLibDiario.fecOpeEmi) : undefined,
      fecCrea: restLibDiario.fecCrea ? dayjs(restLibDiario.fecCrea) : undefined,
      fecModif: restLibDiario.fecModif ? dayjs(restLibDiario.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestLibDiario>): HttpResponse<ILibDiario> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestLibDiario[]>): HttpResponse<ILibDiario[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
