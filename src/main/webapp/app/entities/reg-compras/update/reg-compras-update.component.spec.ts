import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { RegComprasFormService } from './reg-compras-form.service';
import { RegComprasService } from '../service/reg-compras.service';
import { IRegCompras } from '../reg-compras.model';
import { IOrden } from 'app/entities/orden/orden.model';
import { OrdenService } from 'app/entities/orden/service/orden.service';

import { RegComprasUpdateComponent } from './reg-compras-update.component';

describe('RegCompras Management Update Component', () => {
  let comp: RegComprasUpdateComponent;
  let fixture: ComponentFixture<RegComprasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let regComprasFormService: RegComprasFormService;
  let regComprasService: RegComprasService;
  let ordenService: OrdenService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [RegComprasUpdateComponent],
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
      .overrideTemplate(RegComprasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RegComprasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    regComprasFormService = TestBed.inject(RegComprasFormService);
    regComprasService = TestBed.inject(RegComprasService);
    ordenService = TestBed.inject(OrdenService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Orden query and add missing value', () => {
      const regCompras: IRegCompras = { id: 'CBA' };
      const orden: IOrden = { id: 'b4df515c-73c9-4f1d-8a74-1fcf4522aad4' };
      regCompras.orden = orden;

      const ordenCollection: IOrden[] = [{ id: '232f867b-361c-4fc4-a8a0-c6404a7b92dd' }];
      jest.spyOn(ordenService, 'query').mockReturnValue(of(new HttpResponse({ body: ordenCollection })));
      const additionalOrdens = [orden];
      const expectedCollection: IOrden[] = [...additionalOrdens, ...ordenCollection];
      jest.spyOn(ordenService, 'addOrdenToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ regCompras });
      comp.ngOnInit();

      expect(ordenService.query).toHaveBeenCalled();
      expect(ordenService.addOrdenToCollectionIfMissing).toHaveBeenCalledWith(
        ordenCollection,
        ...additionalOrdens.map(expect.objectContaining)
      );
      expect(comp.ordensSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const regCompras: IRegCompras = { id: 'CBA' };
      const orden: IOrden = { id: '77f5afe4-e54d-400b-8db2-e9ec0e722fff' };
      regCompras.orden = orden;

      activatedRoute.data = of({ regCompras });
      comp.ngOnInit();

      expect(comp.ordensSharedCollection).toContain(orden);
      expect(comp.regCompras).toEqual(regCompras);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRegCompras>>();
      const regCompras = { id: 'ABC' };
      jest.spyOn(regComprasFormService, 'getRegCompras').mockReturnValue(regCompras);
      jest.spyOn(regComprasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ regCompras });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: regCompras }));
      saveSubject.complete();

      // THEN
      expect(regComprasFormService.getRegCompras).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(regComprasService.update).toHaveBeenCalledWith(expect.objectContaining(regCompras));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRegCompras>>();
      const regCompras = { id: 'ABC' };
      jest.spyOn(regComprasFormService, 'getRegCompras').mockReturnValue({ id: null });
      jest.spyOn(regComprasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ regCompras: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: regCompras }));
      saveSubject.complete();

      // THEN
      expect(regComprasFormService.getRegCompras).toHaveBeenCalled();
      expect(regComprasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRegCompras>>();
      const regCompras = { id: 'ABC' };
      jest.spyOn(regComprasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ regCompras });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(regComprasService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareOrden', () => {
      it('Should forward to ordenService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(ordenService, 'compareOrden');
        comp.compareOrden(entity, entity2);
        expect(ordenService.compareOrden).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
