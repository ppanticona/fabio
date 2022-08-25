import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ProveedorFormService } from './proveedor-form.service';
import { ProveedorService } from '../service/proveedor.service';
import { IProveedor } from '../proveedor.model';

import { ProveedorUpdateComponent } from './proveedor-update.component';

describe('Proveedor Management Update Component', () => {
  let comp: ProveedorUpdateComponent;
  let fixture: ComponentFixture<ProveedorUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let proveedorFormService: ProveedorFormService;
  let proveedorService: ProveedorService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ProveedorUpdateComponent],
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
      .overrideTemplate(ProveedorUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ProveedorUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    proveedorFormService = TestBed.inject(ProveedorFormService);
    proveedorService = TestBed.inject(ProveedorService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const proveedor: IProveedor = { id: 'CBA' };

      activatedRoute.data = of({ proveedor });
      comp.ngOnInit();

      expect(comp.proveedor).toEqual(proveedor);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IProveedor>>();
      const proveedor = { id: 'ABC' };
      jest.spyOn(proveedorFormService, 'getProveedor').mockReturnValue(proveedor);
      jest.spyOn(proveedorService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ proveedor });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: proveedor }));
      saveSubject.complete();

      // THEN
      expect(proveedorFormService.getProveedor).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(proveedorService.update).toHaveBeenCalledWith(expect.objectContaining(proveedor));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IProveedor>>();
      const proveedor = { id: 'ABC' };
      jest.spyOn(proveedorFormService, 'getProveedor').mockReturnValue({ id: null });
      jest.spyOn(proveedorService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ proveedor: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: proveedor }));
      saveSubject.complete();

      // THEN
      expect(proveedorFormService.getProveedor).toHaveBeenCalled();
      expect(proveedorService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IProveedor>>();
      const proveedor = { id: 'ABC' };
      jest.spyOn(proveedorService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ proveedor });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(proveedorService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
