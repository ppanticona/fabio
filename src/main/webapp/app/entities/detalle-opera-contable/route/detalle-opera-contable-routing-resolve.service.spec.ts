import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IDetalleOperaContable } from '../detalle-opera-contable.model';
import { DetalleOperaContableService } from '../service/detalle-opera-contable.service';

import { DetalleOperaContableRoutingResolveService } from './detalle-opera-contable-routing-resolve.service';

describe('DetalleOperaContable routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: DetalleOperaContableRoutingResolveService;
  let service: DetalleOperaContableService;
  let resultDetalleOperaContable: IDetalleOperaContable | null | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(DetalleOperaContableRoutingResolveService);
    service = TestBed.inject(DetalleOperaContableService);
    resultDetalleOperaContable = undefined;
  });

  describe('resolve', () => {
    it('should return IDetalleOperaContable returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDetalleOperaContable = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultDetalleOperaContable).toEqual({ id: 'ABC' });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDetalleOperaContable = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultDetalleOperaContable).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IDetalleOperaContable>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDetalleOperaContable = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultDetalleOperaContable).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
