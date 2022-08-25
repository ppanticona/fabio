import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IPlanContable, NewPlanContable } from '../plan-contable.model';

export type PartialUpdatePlanContable = Partial<IPlanContable> & Pick<IPlanContable, 'id'>;

type RestOf<T extends IPlanContable | NewPlanContable> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestPlanContable = RestOf<IPlanContable>;

export type NewRestPlanContable = RestOf<NewPlanContable>;

export type PartialUpdateRestPlanContable = RestOf<PartialUpdatePlanContable>;

export type EntityResponseType = HttpResponse<IPlanContable>;
export type EntityArrayResponseType = HttpResponse<IPlanContable[]>;

@Injectable({ providedIn: 'root' })
export class PlanContableService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/plan-contables');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(planContable: NewPlanContable): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(planContable);
    return this.http
      .post<RestPlanContable>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(planContable: IPlanContable): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(planContable);
    return this.http
      .put<RestPlanContable>(`${this.resourceUrl}/${this.getPlanContableIdentifier(planContable)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(planContable: PartialUpdatePlanContable): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(planContable);
    return this.http
      .patch<RestPlanContable>(`${this.resourceUrl}/${this.getPlanContableIdentifier(planContable)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestPlanContable>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestPlanContable[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getPlanContableIdentifier(planContable: Pick<IPlanContable, 'id'>): string {
    return planContable.id;
  }

  comparePlanContable(o1: Pick<IPlanContable, 'id'> | null, o2: Pick<IPlanContable, 'id'> | null): boolean {
    return o1 && o2 ? this.getPlanContableIdentifier(o1) === this.getPlanContableIdentifier(o2) : o1 === o2;
  }

  addPlanContableToCollectionIfMissing<Type extends Pick<IPlanContable, 'id'>>(
    planContableCollection: Type[],
    ...planContablesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const planContables: Type[] = planContablesToCheck.filter(isPresent);
    if (planContables.length > 0) {
      const planContableCollectionIdentifiers = planContableCollection.map(
        planContableItem => this.getPlanContableIdentifier(planContableItem)!
      );
      const planContablesToAdd = planContables.filter(planContableItem => {
        const planContableIdentifier = this.getPlanContableIdentifier(planContableItem);
        if (planContableCollectionIdentifiers.includes(planContableIdentifier)) {
          return false;
        }
        planContableCollectionIdentifiers.push(planContableIdentifier);
        return true;
      });
      return [...planContablesToAdd, ...planContableCollection];
    }
    return planContableCollection;
  }

  protected convertDateFromClient<T extends IPlanContable | NewPlanContable | PartialUpdatePlanContable>(planContable: T): RestOf<T> {
    return {
      ...planContable,
      fecCrea: planContable.fecCrea?.toJSON() ?? null,
      fecModif: planContable.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restPlanContable: RestPlanContable): IPlanContable {
    return {
      ...restPlanContable,
      fecCrea: restPlanContable.fecCrea ? dayjs(restPlanContable.fecCrea) : undefined,
      fecModif: restPlanContable.fecModif ? dayjs(restPlanContable.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestPlanContable>): HttpResponse<IPlanContable> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestPlanContable[]>): HttpResponse<IPlanContable[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
