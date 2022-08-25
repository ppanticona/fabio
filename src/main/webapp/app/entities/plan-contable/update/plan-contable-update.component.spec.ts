import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { PlanContableFormService } from './plan-contable-form.service';
import { PlanContableService } from '../service/plan-contable.service';
import { IPlanContable } from '../plan-contable.model';

import { PlanContableUpdateComponent } from './plan-contable-update.component';

describe('PlanContable Management Update Component', () => {
  let comp: PlanContableUpdateComponent;
  let fixture: ComponentFixture<PlanContableUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let planContableFormService: PlanContableFormService;
  let planContableService: PlanContableService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [PlanContableUpdateComponent],
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
      .overrideTemplate(PlanContableUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PlanContableUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    planContableFormService = TestBed.inject(PlanContableFormService);
    planContableService = TestBed.inject(PlanContableService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const planContable: IPlanContable = { id: 'CBA' };

      activatedRoute.data = of({ planContable });
      comp.ngOnInit();

      expect(comp.planContable).toEqual(planContable);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPlanContable>>();
      const planContable = { id: 'ABC' };
      jest.spyOn(planContableFormService, 'getPlanContable').mockReturnValue(planContable);
      jest.spyOn(planContableService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ planContable });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: planContable }));
      saveSubject.complete();

      // THEN
      expect(planContableFormService.getPlanContable).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(planContableService.update).toHaveBeenCalledWith(expect.objectContaining(planContable));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPlanContable>>();
      const planContable = { id: 'ABC' };
      jest.spyOn(planContableFormService, 'getPlanContable').mockReturnValue({ id: null });
      jest.spyOn(planContableService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ planContable: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: planContable }));
      saveSubject.complete();

      // THEN
      expect(planContableFormService.getPlanContable).toHaveBeenCalled();
      expect(planContableService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPlanContable>>();
      const planContable = { id: 'ABC' };
      jest.spyOn(planContableService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ planContable });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(planContableService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
