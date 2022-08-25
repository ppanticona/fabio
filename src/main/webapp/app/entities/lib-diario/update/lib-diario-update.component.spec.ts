import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { LibDiarioFormService } from './lib-diario-form.service';
import { LibDiarioService } from '../service/lib-diario.service';
import { ILibDiario } from '../lib-diario.model';

import { LibDiarioUpdateComponent } from './lib-diario-update.component';

describe('LibDiario Management Update Component', () => {
  let comp: LibDiarioUpdateComponent;
  let fixture: ComponentFixture<LibDiarioUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let libDiarioFormService: LibDiarioFormService;
  let libDiarioService: LibDiarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [LibDiarioUpdateComponent],
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
      .overrideTemplate(LibDiarioUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(LibDiarioUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    libDiarioFormService = TestBed.inject(LibDiarioFormService);
    libDiarioService = TestBed.inject(LibDiarioService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const libDiario: ILibDiario = { id: 'CBA' };

      activatedRoute.data = of({ libDiario });
      comp.ngOnInit();

      expect(comp.libDiario).toEqual(libDiario);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILibDiario>>();
      const libDiario = { id: 'ABC' };
      jest.spyOn(libDiarioFormService, 'getLibDiario').mockReturnValue(libDiario);
      jest.spyOn(libDiarioService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ libDiario });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: libDiario }));
      saveSubject.complete();

      // THEN
      expect(libDiarioFormService.getLibDiario).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(libDiarioService.update).toHaveBeenCalledWith(expect.objectContaining(libDiario));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILibDiario>>();
      const libDiario = { id: 'ABC' };
      jest.spyOn(libDiarioFormService, 'getLibDiario').mockReturnValue({ id: null });
      jest.spyOn(libDiarioService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ libDiario: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: libDiario }));
      saveSubject.complete();

      // THEN
      expect(libDiarioFormService.getLibDiario).toHaveBeenCalled();
      expect(libDiarioService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILibDiario>>();
      const libDiario = { id: 'ABC' };
      jest.spyOn(libDiarioService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ libDiario });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(libDiarioService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
