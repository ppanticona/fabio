import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { OperaContableFormService } from './opera-contable-form.service';
import { OperaContableService } from '../service/opera-contable.service';
import { IOperaContable } from '../opera-contable.model';

import { OperaContableUpdateComponent } from './opera-contable-update.component';

describe('OperaContable Management Update Component', () => {
  let comp: OperaContableUpdateComponent;
  let fixture: ComponentFixture<OperaContableUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let operaContableFormService: OperaContableFormService;
  let operaContableService: OperaContableService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [OperaContableUpdateComponent],
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
      .overrideTemplate(OperaContableUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OperaContableUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    operaContableFormService = TestBed.inject(OperaContableFormService);
    operaContableService = TestBed.inject(OperaContableService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const operaContable: IOperaContable = { id: 'CBA' };

      activatedRoute.data = of({ operaContable });
      comp.ngOnInit();

      expect(comp.operaContable).toEqual(operaContable);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOperaContable>>();
      const operaContable = { id: 'ABC' };
      jest.spyOn(operaContableFormService, 'getOperaContable').mockReturnValue(operaContable);
      jest.spyOn(operaContableService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ operaContable });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: operaContable }));
      saveSubject.complete();

      // THEN
      expect(operaContableFormService.getOperaContable).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(operaContableService.update).toHaveBeenCalledWith(expect.objectContaining(operaContable));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOperaContable>>();
      const operaContable = { id: 'ABC' };
      jest.spyOn(operaContableFormService, 'getOperaContable').mockReturnValue({ id: null });
      jest.spyOn(operaContableService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ operaContable: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: operaContable }));
      saveSubject.complete();

      // THEN
      expect(operaContableFormService.getOperaContable).toHaveBeenCalled();
      expect(operaContableService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOperaContable>>();
      const operaContable = { id: 'ABC' };
      jest.spyOn(operaContableService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ operaContable });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(operaContableService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
