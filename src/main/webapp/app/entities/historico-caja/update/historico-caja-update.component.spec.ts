import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { HistoricoCajaFormService } from './historico-caja-form.service';
import { HistoricoCajaService } from '../service/historico-caja.service';
import { IHistoricoCaja } from '../historico-caja.model';
import { ICaja } from 'app/entities/caja/caja.model';
import { CajaService } from 'app/entities/caja/service/caja.service';

import { HistoricoCajaUpdateComponent } from './historico-caja-update.component';

describe('HistoricoCaja Management Update Component', () => {
  let comp: HistoricoCajaUpdateComponent;
  let fixture: ComponentFixture<HistoricoCajaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let historicoCajaFormService: HistoricoCajaFormService;
  let historicoCajaService: HistoricoCajaService;
  let cajaService: CajaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [HistoricoCajaUpdateComponent],
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
      .overrideTemplate(HistoricoCajaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(HistoricoCajaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    historicoCajaFormService = TestBed.inject(HistoricoCajaFormService);
    historicoCajaService = TestBed.inject(HistoricoCajaService);
    cajaService = TestBed.inject(CajaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Caja query and add missing value', () => {
      const historicoCaja: IHistoricoCaja = { id: 'CBA' };
      const caja: ICaja = { id: '00bdd067-b5f5-4aa0-abbe-f6845cb3343e' };
      historicoCaja.caja = caja;

      const cajaCollection: ICaja[] = [{ id: 'cfbf5370-18f2-484b-93ee-6ca19e938c00' }];
      jest.spyOn(cajaService, 'query').mockReturnValue(of(new HttpResponse({ body: cajaCollection })));
      const additionalCajas = [caja];
      const expectedCollection: ICaja[] = [...additionalCajas, ...cajaCollection];
      jest.spyOn(cajaService, 'addCajaToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ historicoCaja });
      comp.ngOnInit();

      expect(cajaService.query).toHaveBeenCalled();
      expect(cajaService.addCajaToCollectionIfMissing).toHaveBeenCalledWith(
        cajaCollection,
        ...additionalCajas.map(expect.objectContaining)
      );
      expect(comp.cajasSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const historicoCaja: IHistoricoCaja = { id: 'CBA' };
      const caja: ICaja = { id: 'f784e37e-a13c-4e65-aeca-d08ead1ab058' };
      historicoCaja.caja = caja;

      activatedRoute.data = of({ historicoCaja });
      comp.ngOnInit();

      expect(comp.cajasSharedCollection).toContain(caja);
      expect(comp.historicoCaja).toEqual(historicoCaja);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHistoricoCaja>>();
      const historicoCaja = { id: 'ABC' };
      jest.spyOn(historicoCajaFormService, 'getHistoricoCaja').mockReturnValue(historicoCaja);
      jest.spyOn(historicoCajaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ historicoCaja });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: historicoCaja }));
      saveSubject.complete();

      // THEN
      expect(historicoCajaFormService.getHistoricoCaja).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(historicoCajaService.update).toHaveBeenCalledWith(expect.objectContaining(historicoCaja));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHistoricoCaja>>();
      const historicoCaja = { id: 'ABC' };
      jest.spyOn(historicoCajaFormService, 'getHistoricoCaja').mockReturnValue({ id: null });
      jest.spyOn(historicoCajaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ historicoCaja: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: historicoCaja }));
      saveSubject.complete();

      // THEN
      expect(historicoCajaFormService.getHistoricoCaja).toHaveBeenCalled();
      expect(historicoCajaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IHistoricoCaja>>();
      const historicoCaja = { id: 'ABC' };
      jest.spyOn(historicoCajaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ historicoCaja });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(historicoCajaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareCaja', () => {
      it('Should forward to cajaService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(cajaService, 'compareCaja');
        comp.compareCaja(entity, entity2);
        expect(cajaService.compareCaja).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
