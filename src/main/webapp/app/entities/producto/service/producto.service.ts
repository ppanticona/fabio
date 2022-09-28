import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IProducto, NewProducto } from '../producto.model';
import { RestProveedor } from '../../proveedor/service/proveedor.service';

export type PartialUpdateProducto = Partial<IProducto> & Pick<IProducto, 'id'>;

type RestOf<T extends IProducto | NewProducto> = Omit<T, 'fecIniVig' | 'fecFinVig' | 'fecCrea' | 'fecModif'> & {
  fecIniVig?: string | null;
  fecFinVig?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

export type RestProducto = RestOf<IProducto>;

export type NewRestProducto = RestOf<NewProducto>;

export type PartialUpdateRestProducto = RestOf<PartialUpdateProducto>;

export type EntityResponseType = HttpResponse<IProducto>;
export type EntityArrayResponseType = HttpResponse<IProducto[]>;

@Injectable({ providedIn: 'root' })
export class ProductoService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/productos');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(producto: NewProducto): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(producto);
    return this.http
      .post<RestProducto>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(producto: IProducto): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(producto);
    return this.http
      .put<RestProducto>(`${this.resourceUrl}/${this.getProductoIdentifier(producto)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(producto: PartialUpdateProducto): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(producto);
    return this.http
      .patch<RestProducto>(`${this.resourceUrl}/${this.getProductoIdentifier(producto)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestProducto>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestProducto[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getProductoIdentifier(producto: Pick<IProducto, 'id'>): string {
    return producto.id;
  }

  compareProducto(o1: Pick<IProducto, 'id'> | null, o2: Pick<IProducto, 'id'> | null): boolean {
    return o1 && o2 ? this.getProductoIdentifier(o1) === this.getProductoIdentifier(o2) : o1 === o2;
  }

  findByCodProd(codProd: string): Observable<HttpResponse<any>> {
    const httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http
      .get<IProducto>(`${this.resourceUrl}/codprod/${codProd}`, {
        headers: httpHeaders,
        observe: 'response',
      })
      .pipe(map((res: HttpResponse<any>) => res));
  }

  findCadenaInDescripcion(cadena: string): Observable<HttpResponse<any>> {
    const httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http
      .get<RestProveedor>(`/api/productosPorDescripcion/${cadena}`, {
        headers: httpHeaders,
        observe: 'response',
      })
      .pipe(map((res: HttpResponse<any>) => res));
  }

  findByCodProducto(cadena: string): Observable<HttpResponse<any>> {
    const httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http
      .get<RestProveedor>(`/api/productosPorCodigo/${cadena}`, {
        headers: httpHeaders,
        observe: 'response',
      })
      .pipe(map((res: HttpResponse<any>) => res));
  }

  listProductosInventario(): Observable<HttpResponse<any>> {
    const httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http
      .get<RestProveedor>(`/api/productos/inventarioProductos`, {
        headers: httpHeaders,
        observe: 'response',
      })
      .pipe(map((res: HttpResponse<any>) => res));
  }

  addProductoToCollectionIfMissing<Type extends Pick<IProducto, 'id'>>(
    productoCollection: Type[],
    ...productosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const productos: Type[] = productosToCheck.filter(isPresent);
    if (productos.length > 0) {
      const productoCollectionIdentifiers = productoCollection.map(productoItem => this.getProductoIdentifier(productoItem)!);
      const productosToAdd = productos.filter(productoItem => {
        const productoIdentifier = this.getProductoIdentifier(productoItem);
        if (productoCollectionIdentifiers.includes(productoIdentifier)) {
          return false;
        }
        productoCollectionIdentifiers.push(productoIdentifier);
        return true;
      });
      return [...productosToAdd, ...productoCollection];
    }
    return productoCollection;
  }
  registrarProducto(data: any): Observable<HttpResponse<any>> {
    const body = JSON.stringify(data);

    const httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

    return this.http
      .post<any>(`${this.resourceUrl}/registrarProducto`, body, {
        headers: httpHeaders,
        observe: 'response',
      })
      .pipe(map((res: HttpResponse<any>) => res));
  }
  protected convertDateFromClient<T extends IProducto | NewProducto | PartialUpdateProducto>(producto: T): RestOf<T> {
    return {
      ...producto,
      fecIniVig: producto.fecIniVig?.toJSON() ?? null,
      fecFinVig: producto.fecFinVig?.toJSON() ?? null,
      fecCrea: producto.fecCrea?.toJSON() ?? null,
      fecModif: producto.fecModif?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restProducto: RestProducto): IProducto {
    return {
      ...restProducto,
      fecIniVig: restProducto.fecIniVig ? dayjs(restProducto.fecIniVig) : undefined,
      fecFinVig: restProducto.fecFinVig ? dayjs(restProducto.fecFinVig) : undefined,
      fecCrea: restProducto.fecCrea ? dayjs(restProducto.fecCrea) : undefined,
      fecModif: restProducto.fecModif ? dayjs(restProducto.fecModif) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestProducto>): HttpResponse<IProducto> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestProducto[]>): HttpResponse<IProducto[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
