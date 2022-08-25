import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IOperaContable, NewOperaContable } from '../opera-contable.model';

export type PartialUpdateOperaContable = Partial<IOperaContable> & Pick<IOperaContable, 'id'>;

type RestOf<T extends IOperaContable | NewOperaContable> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestOperaContable = RestOf<IOperaContable>;

export type NewRestOperaContable = RestOf<NewOperaContable>;

export type PartialUpdateRestOperaContable = RestOf<PartialUpdateOperaContable>;

export type EntityResponseType = HttpResponse<IOperaContable>;
export type EntityArrayResponseType = HttpResponse<IOperaContable[]>;

@Injectable({ providedIn: 'root' })
export class OperaContableService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/opera-contables');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(operaContable: NewOperaContable): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(operaContable);
    return this.http
      .post<RestOperaContable>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(operaContable: IOperaContable): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(operaContable);
    return this.http
      .put<RestOperaContable>(`${this.resourceUrl}/${this.getOperaContableIdentifier(operaContable)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(operaContable: PartialUpdateOperaContable): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(operaContable);
    return this.http
      .patch<RestOperaContable>(`${this.resourceUrl}/${this.getOperaContableIdentifier(operaContable)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestOperaContable>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOperaContable[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getOperaContableIdentifier(operaContable: Pick<IOperaContable, 'id'>): string {
    return operaContable.id;
  }

  compareOperaContable(o1: Pick<IOperaContable, 'id'> | null, o2: Pick<IOperaContable, 'id'> | null): boolean {
    return o1 && o2 ? this.getOperaContableIdentifier(o1) === this.getOperaContableIdentifier(o2) : o1 === o2;
  }

  addOperaContableToCollectionIfMissing<Type extends Pick<IOperaContable, 'id'>>(
    operaContableCollection: Type[],
    ...operaContablesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const operaContables: Type[] = operaContablesToCheck.filter(isPresent);
    if (operaContables.length > 0) {
      const operaContableCollectionIdentifiers = operaContableCollection.map(
        operaContableItem => this.getOperaContableIdentifier(operaContableItem)!
      );
      const operaContablesToAdd = operaContables.filter(operaContableItem => {
        const operaContableIdentifier = this.getOperaContableIdentifier(operaContableItem);
        if (operaContableCollectionIdentifiers.includes(operaContableIdentifier)) {
          return false;
        }
        operaContableCollectionIdentifiers.push(operaContableIdentifier);
        return true;
      });
      return [...operaContablesToAdd, ...operaContableCollection];
    }
    return operaContableCollection;
  }

  protected convertDateFromClient<T extends IOperaContable | NewOperaContable | PartialUpdateOperaContable>(operaContable: T): RestOf<T> {
    return {
      ...operaContable,
      fecCrea: operaContable.fecCrea?.toJSON() ?? null,
      fecModif: operaContable.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restOperaContable: RestOperaContable): IOperaContable {
    return {
      ...restOperaContable,
      fecCrea: restOperaContable.fecCrea ? dayjs(restOperaContable.fecCrea) : undefined,
      fecModif: restOperaContable.fecModif ? dayjs(restOperaContable.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOperaContable>): HttpResponse<IOperaContable> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestOperaContable[]>): HttpResponse<IOperaContable[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
