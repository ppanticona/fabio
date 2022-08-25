import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DetalleOperaContableFormService } from './detalle-opera-contable-form.service';
import { DetalleOperaContableService } from '../service/detalle-opera-contable.service';
import { IDetalleOperaContable } from '../detalle-opera-contable.model';
import { IPlanContable } from 'app/entities/plan-contable/plan-contable.model';
import { PlanContableService } from 'app/entities/plan-contable/service/plan-contable.service';
import { IOperaContable } from 'app/entities/opera-contable/opera-contable.model';
import { OperaContableService } from 'app/entities/opera-contable/service/opera-contable.service';

import { DetalleOperaContableUpdateComponent } from './detalle-opera-contable-update.component';

describe('DetalleOperaContable Management Update Component', () => {
  let comp: DetalleOperaContableUpdateComponent;
  let fixture: ComponentFixture<DetalleOperaContableUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let detalleOperaContableFormService: DetalleOperaContableFormService;
  let detalleOperaContableService: DetalleOperaContableService;
  let planContableService: PlanContableService;
  let operaContableService: OperaContableService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DetalleOperaContableUpdateComponent],
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
      .overrideTemplate(DetalleOperaContableUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DetalleOperaContableUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    detalleOperaContableFormService = TestBed.inject(DetalleOperaContableFormService);
    detalleOperaContableService = TestBed.inject(DetalleOperaContableService);
    planContableService = TestBed.inject(PlanContableService);
    operaContableService = TestBed.inject(OperaContableService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call PlanContable query and add missing value', () => {
      const detalleOperaContable: IDetalleOperaContable = { id: 'CBA' };
      const planContable: IPlanContable = { id: 'acc1356d-2b68-4d60-b0c0-8848d9a552f7' };
      detalleOperaContable.planContable = planContable;

      const planContableCollection: IPlanContable[] = [{ id: '590de897-7d36-49ef-8e05-a8a3c50804bb' }];
      jest.spyOn(planContableService, 'query').mockReturnValue(of(new HttpResponse({ body: planContableCollection })));
      const additionalPlanContables = [planContable];
      const expectedCollection: IPlanContable[] = [...additionalPlanContables, ...planContableCollection];
      jest.spyOn(planContableService, 'addPlanContableToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ detalleOperaContable });
      comp.ngOnInit();

      expect(planContableService.query).toHaveBeenCalled();
      expect(planContableService.addPlanContableToCollectionIfMissing).toHaveBeenCalledWith(
        planContableCollection,
        ...additionalPlanContables.map(expect.objectContaining)
      );
      expect(comp.planContablesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call OperaContable query and add missing value', () => {
      const detalleOperaContable: IDetalleOperaContable = { id: 'CBA' };
      const operaContable: IOperaContable = { id: 'd8d15553-e9fb-4abe-a334-368a1ee5e92d' };
      detalleOperaContable.operaContable = operaContable;

      const operaContableCollection: IOperaContable[] = [{ id: 'c647c8a9-7334-4244-8464-47abae0edddb' }];
      jest.spyOn(operaContableService, 'query').mockReturnValue(of(new HttpResponse({ body: operaContableCollection })));
      const additionalOperaContables = [operaContable];
      const expectedCollection: IOperaContable[] = [...additionalOperaContables, ...operaContableCollection];
      jest.spyOn(operaContableService, 'addOperaContableToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ detalleOperaContable });
      comp.ngOnInit();

      expect(operaContableService.query).toHaveBeenCalled();
      expect(operaContableService.addOperaContableToCollectionIfMissing).toHaveBeenCalledWith(
        operaContableCollection,
        ...additionalOperaContables.map(expect.objectContaining)
      );
      expect(comp.operaContablesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const detalleOperaContable: IDetalleOperaContable = { id: 'CBA' };
      const planContable: IPlanContable = { id: '5b58fdb7-2183-45b2-ba50-3b3c7dd545e3' };
      detalleOperaContable.planContable = planContable;
      const operaContable: IOperaContable = { id: '6a87f679-ddcf-4b3c-8425-4d539055c48b' };
      detalleOperaContable.operaContable = operaContable;

      activatedRoute.data = of({ detalleOperaContable });
      comp.ngOnInit();

      expect(comp.planContablesSharedCollection).toContain(planContable);
      expect(comp.operaContablesSharedCollection).toContain(operaContable);
      expect(comp.detalleOperaContable).toEqual(detalleOperaContable);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDetalleOperaContable>>();
      const detalleOperaContable = { id: 'ABC' };
      jest.spyOn(detalleOperaContableFormService, 'getDetalleOperaContable').mockReturnValue(detalleOperaContable);
      jest.spyOn(detalleOperaContableService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ detalleOperaContable });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: detalleOperaContable }));
      saveSubject.complete();

      // THEN
      expect(detalleOperaContableFormService.getDetalleOperaContable).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(detalleOperaContableService.update).toHaveBeenCalledWith(expect.objectContaining(detalleOperaContable));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDetalleOperaContable>>();
      const detalleOperaContable = { id: 'ABC' };
      jest.spyOn(detalleOperaContableFormService, 'getDetalleOperaContable').mockReturnValue({ id: null });
      jest.spyOn(detalleOperaContableService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ detalleOperaContable: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: detalleOperaContable }));
      saveSubject.complete();

      // THEN
      expect(detalleOperaContableFormService.getDetalleOperaContable).toHaveBeenCalled();
      expect(detalleOperaContableService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDetalleOperaContable>>();
      const detalleOperaContable = { id: 'ABC' };
      jest.spyOn(detalleOperaContableService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ detalleOperaContable });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(detalleOperaContableService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('comparePlanContable', () => {
      it('Should forward to planContableService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(planContableService, 'comparePlanContable');
        comp.comparePlanContable(entity, entity2);
        expect(planContableService.comparePlanContable).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareOperaContable', () => {
      it('Should forward to operaContableService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(operaContableService, 'compareOperaContable');
        comp.compareOperaContable(entity, entity2);
        expect(operaContableService.compareOperaContable).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
