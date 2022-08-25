import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICliente, NewCliente } from '../cliente.model';

export type PartialUpdateCliente = Partial<ICliente> & Pick<ICliente, 'id'>;

type RestOf<T extends ICliente | NewCliente> = Omit<T, 'fecNac' | 'fecCrea' | 'fecModif'> & {
  fecNac?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestCliente = RestOf<ICliente>;

export type NewRestCliente = RestOf<NewCliente>;

export type PartialUpdateRestCliente = RestOf<PartialUpdateCliente>;

export type EntityResponseType = HttpResponse<ICliente>;
export type EntityArrayResponseType = HttpResponse<ICliente[]>;

@Injectable({ providedIn: 'root' })
export class ClienteService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/clientes');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(cliente: NewCliente): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cliente);
    return this.http
      .post<RestCliente>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(cliente: ICliente): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cliente);
    return this.http
      .put<RestCliente>(`${this.resourceUrl}/${this.getClienteIdentifier(cliente)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(cliente: PartialUpdateCliente): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cliente);
    return this.http
      .patch<RestCliente>(`${this.resourceUrl}/${this.getClienteIdentifier(cliente)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestCliente>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestCliente[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getClienteIdentifier(cliente: Pick<ICliente, 'id'>): string {
    return cliente.id;
  }

  compareCliente(o1: Pick<ICliente, 'id'> | null, o2: Pick<ICliente, 'id'> | null): boolean {
    return o1 && o2 ? this.getClienteIdentifier(o1) === this.getClienteIdentifier(o2) : o1 === o2;
  }

  addClienteToCollectionIfMissing<Type extends Pick<ICliente, 'id'>>(
    clienteCollection: Type[],
    ...clientesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const clientes: Type[] = clientesToCheck.filter(isPresent);
    if (clientes.length > 0) {
      const clienteCollectionIdentifiers = clienteCollection.map(clienteItem => this.getClienteIdentifier(clienteItem)!);
      const clientesToAdd = clientes.filter(clienteItem => {
        const clienteIdentifier = this.getClienteIdentifier(clienteItem);
        if (clienteCollectionIdentifiers.includes(clienteIdentifier)) {
          return false;
        }
        clienteCollectionIdentifiers.push(clienteIdentifier);
        return true;
      });
      return [...clientesToAdd, ...clienteCollection];
    }
    return clienteCollection;
  }

  protected convertDateFromClient<T extends ICliente | NewCliente | PartialUpdateCliente>(cliente: T): RestOf<T> {
    return {
      ...cliente,
      fecNac: cliente.fecNac?.toJSON() ?? null,
      fecCrea: cliente.fecCrea?.toJSON() ?? null,
      fecModif: cliente.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restCliente: RestCliente): ICliente {
    return {
      ...restCliente,
      fecNac: restCliente.fecNac ? dayjs(restCliente.fecNac) : undefined,
      fecCrea: restCliente.fecCrea ? dayjs(restCliente.fecCrea) : undefined,
      fecModif: restCliente.fecModif ? dayjs(restCliente.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestCliente>): HttpResponse<ICliente> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestCliente[]>): HttpResponse<ICliente[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
