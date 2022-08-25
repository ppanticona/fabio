import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AutorizacionFormService } from './autorizacion-form.service';
import { AutorizacionService } from '../service/autorizacion.service';
import { IAutorizacion } from '../autorizacion.model';

import { AutorizacionUpdateComponent } from './autorizacion-update.component';

describe('Autorizacion Management Update Component', () => {
  let comp: AutorizacionUpdateComponent;
  let fixture: ComponentFixture<AutorizacionUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let autorizacionFormService: AutorizacionFormService;
  let autorizacionService: AutorizacionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AutorizacionUpdateComponent],
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
      .overrideTemplate(AutorizacionUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AutorizacionUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    autorizacionFormService = TestBed.inject(AutorizacionFormService);
    autorizacionService = TestBed.inject(AutorizacionService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const autorizacion: IAutorizacion = { id: 'CBA' };

      activatedRoute.data = of({ autorizacion });
      comp.ngOnInit();

      expect(comp.autorizacion).toEqual(autorizacion);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutorizacion>>();
      const autorizacion = { id: 'ABC' };
      jest.spyOn(autorizacionFormService, 'getAutorizacion').mockReturnValue(autorizacion);
      jest.spyOn(autorizacionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autorizacion });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autorizacion }));
      saveSubject.complete();

      // THEN
      expect(autorizacionFormService.getAutorizacion).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(autorizacionService.update).toHaveBeenCalledWith(expect.objectContaining(autorizacion));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutorizacion>>();
      const autorizacion = { id: 'ABC' };
      jest.spyOn(autorizacionFormService, 'getAutorizacion').mockReturnValue({ id: null });
      jest.spyOn(autorizacionService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autorizacion: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: autorizacion }));
      saveSubject.complete();

      // THEN
      expect(autorizacionFormService.getAutorizacion).toHaveBeenCalled();
      expect(autorizacionService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAutorizacion>>();
      const autorizacion = { id: 'ABC' };
      jest.spyOn(autorizacionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ autorizacion });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(autorizacionService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
