import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { MovimientoProductoFormService } from './movimiento-producto-form.service';
import { MovimientoProductoService } from '../service/movimiento-producto.service';
import { IMovimientoProducto } from '../movimiento-producto.model';
import { IProducto } from 'app/entities/producto/producto.model';
import { ProductoService } from 'app/entities/producto/service/producto.service';
import { IRegVenta } from 'app/entities/reg-venta/reg-venta.model';
import { RegVentaService } from 'app/entities/reg-venta/service/reg-venta.service';
import { IRegCompras } from 'app/entities/reg-compras/reg-compras.model';
import { RegComprasService } from 'app/entities/reg-compras/service/reg-compras.service';

import { MovimientoProductoUpdateComponent } from './movimiento-producto-update.component';

describe('MovimientoProducto Management Update Component', () => {
  let comp: MovimientoProductoUpdateComponent;
  let fixture: ComponentFixture<MovimientoProductoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let movimientoProductoFormService: MovimientoProductoFormService;
  let movimientoProductoService: MovimientoProductoService;
  let productoService: ProductoService;
  let regVentaService: RegVentaService;
  let regComprasService: RegComprasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [MovimientoProductoUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(MovimientoProductoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MovimientoProductoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    movimientoProductoFormService = TestBed.inject(MovimientoProductoFormService);
    movimientoProductoService = TestBed.inject(MovimientoProductoService);
    productoService = TestBed.inject(ProductoService);
    regVentaService = TestBed.inject(RegVentaService);
    regComprasService = TestBed.inject(RegComprasService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Producto query and add missing value', () => {
      const movimientoProducto: IMovimientoProducto = { id: 'CBA' };
      const producto: IProducto = { id: 'be0be8cc-e5ea-4600-ae99-770bae511496' };
      movimientoProducto.producto = producto;

      const productoCollection: IProducto[] = [{ id: 'cf86296b-180b-4a3c-87f5-a7a0d1612712' }];
      jest.spyOn(productoService, 'query').mockReturnValue(of(new HttpResponse({ body: productoCollection })));
      const additionalProductos = [producto];
      const expectedCollection: IProducto[] = [...additionalProductos, ...productoCollection];
      jest.spyOn(productoService, 'addProductoToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ movimientoProducto });
      comp.ngOnInit();

      expect(productoService.query).toHaveBeenCalled();
      expect(productoService.addProductoToCollectionIfMissing).toHaveBeenCalledWith(
        productoCollection,
        ...additionalProductos.map(expect.objectContaining)
      );
      expect(comp.productosSharedCollection).toEqual(expectedCollection);
    });

    it('Should call RegVenta query and add missing value', () => {
      const movimientoProducto: IMovimientoProducto = { id: 'CBA' };
      const regVenta: IRegVenta = { id: '5a8b83c4-4504-40bf-a5eb-896463a36c1d' };
      movimientoProducto.regVenta = regVenta;

      const regVentaCollection: IRegVenta[] = [{ id: '7f4bc207-bc8a-4b21-91e7-3245c0c8f950' }];
      jest.spyOn(regVentaService, 'query').mockReturnValue(of(new HttpResponse({ body: regVentaCollection })));
      const additionalRegVentas = [regVenta];
      const expectedCollection: IRegVenta[] = [...additionalRegVentas, ...regVentaCollection];
      jest.spyOn(regVentaService, 'addRegVentaToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ movimientoProducto });
      comp.ngOnInit();

      expect(regVentaService.query).toHaveBeenCalled();
      expect(regVentaService.addRegVentaToCollectionIfMissing).toHaveBeenCalledWith(
        regVentaCollection,
        ...additionalRegVentas.map(expect.objectContaining)
      );
      expect(comp.regVentasSharedCollection).toEqual(expectedCollection);
    });

    it('Should call RegCompras query and add missing value', () => {
      const movimientoProducto: IMovimientoProducto = { id: 'CBA' };
      const regCompras: IRegCompras = { id: '96a7f161-b431-4f00-957f-ff4d928aea90' };
      movimientoProducto.regCompras = regCompras;

      const regComprasCollection: IRegCompras[] = [{ id: '7163dbcc-b4af-45fd-b926-ceec75c055ce' }];
      jest.spyOn(regComprasService, 'query').mockReturnValue(of(new HttpResponse({ body: regComprasCollection })));
      const additionalRegCompras = [regCompras];
      const expectedCollection: IRegCompras[] = [...additionalRegCompras, ...regComprasCollection];
      jest.spyOn(regComprasService, 'addRegComprasToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ movimientoProducto });
      comp.ngOnInit();

      expect(regComprasService.query).toHaveBeenCalled();
      expect(regComprasService.addRegComprasToCollectionIfMissing).toHaveBeenCalledWith(
        regComprasCollection,
        ...additionalRegCompras.map(expect.objectContaining)
      );
      expect(comp.regComprasSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const movimientoProducto: IMovimientoProducto = { id: 'CBA' };
      const producto: IProducto = { id: '80538157-5312-4c1c-aeab-7c5ff7844b11' };
      movimientoProducto.producto = producto;
      const regVenta: IRegVenta = { id: 'c9f792ab-ffb4-4af6-953c-3dfd8456b889' };
      movimientoProducto.regVenta = regVenta;
      const regCompras: IRegCompras = { id: '85766275-eb9f-45b4-ad7c-be2fbe5551ad' };
      movimientoProducto.regCompras = regCompras;

      activatedRoute.data = of({ movimientoProducto });
      comp.ngOnInit();

      expect(comp.productosSharedCollection).toContain(producto);
      expect(comp.regVentasSharedCollection).toContain(regVenta);
      expect(comp.regComprasSharedCollection).toContain(regCompras);
      expect(comp.movimientoProducto).toEqual(movimientoProducto);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMovimientoProducto>>();
      const movimientoProducto = { id: 'ABC' };
      jest.spyOn(movimientoProductoFormService, 'getMovimientoProducto').mockReturnValue(movimientoProducto);
      jest.spyOn(movimientoProductoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ movimientoProducto });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: movimientoProducto }));
      saveSubject.complete();

      // THEN
      expect(movimientoProductoFormService.getMovimientoProducto).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(movimientoProductoService.update).toHaveBeenCalledWith(expect.objectContaining(movimientoProducto));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMovimientoProducto>>();
      const movimientoProducto = { id: 'ABC' };
      jest.spyOn(movimientoProductoFormService, 'getMovimientoProducto').mockReturnValue({ id: null });
      jest.spyOn(movimientoProductoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ movimientoProducto: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: movimientoProducto }));
      saveSubject.complete();

      // THEN
      expect(movimientoProductoFormService.getMovimientoProducto).toHaveBeenCalled();
      expect(movimientoProductoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMovimientoProducto>>();
      const movimientoProducto = { id: 'ABC' };
      jest.spyOn(movimientoProductoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ movimientoProducto });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(movimientoProductoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareProducto', () => {
      it('Should forward to productoService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(productoService, 'compareProducto');
        comp.compareProducto(entity, entity2);
        expect(productoService.compareProducto).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareRegVenta', () => {
      it('Should forward to regVentaService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(regVentaService, 'compareRegVenta');
        comp.compareRegVenta(entity, entity2);
        expect(regVentaService.compareRegVenta).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareRegCompras', () => {
      it('Should forward to regComprasService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(regComprasService, 'compareRegCompras');
        comp.compareRegCompras(entity, entity2);
        expect(regComprasService.compareRegCompras).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
