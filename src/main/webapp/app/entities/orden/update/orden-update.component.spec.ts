import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { OrdenFormService } from './orden-form.service';
import { OrdenService } from '../service/orden.service';
import { IOrden } from '../orden.model';
import { ICliente } from 'app/entities/cliente/cliente.model';
import { ClienteService } from 'app/entities/cliente/service/cliente.service';
import { IProveedor } from 'app/entities/proveedor/proveedor.model';
import { ProveedorService } from 'app/entities/proveedor/service/proveedor.service';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';
import { AutorizacionService } from 'app/entities/autorizacion/service/autorizacion.service';

import { OrdenUpdateComponent } from './orden-update.component';

describe('Orden Management Update Component', () => {
  let comp: OrdenUpdateComponent;
  let fixture: ComponentFixture<OrdenUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ordenFormService: OrdenFormService;
  let ordenService: OrdenService;
  let clienteService: ClienteService;
  let proveedorService: ProveedorService;
  let autorizacionService: AutorizacionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [OrdenUpdateComponent],
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
      .overrideTemplate(OrdenUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OrdenUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ordenFormService = TestBed.inject(OrdenFormService);
    ordenService = TestBed.inject(OrdenService);
    clienteService = TestBed.inject(ClienteService);
    proveedorService = TestBed.inject(ProveedorService);
    autorizacionService = TestBed.inject(AutorizacionService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Cliente query and add missing value', () => {
      const orden: IOrden = { id: 'CBA' };
      const cliente: ICliente = { id: 'e18fbb8a-341a-4c2e-b14e-4c1f53dfa07a' };
      orden.cliente = cliente;

      const clienteCollection: ICliente[] = [{ id: '7e902b1f-0203-4cb9-adc9-6f25d4cdeddc' }];
      jest.spyOn(clienteService, 'query').mockReturnValue(of(new HttpResponse({ body: clienteCollection })));
      const additionalClientes = [cliente];
      const expectedCollection: ICliente[] = [...additionalClientes, ...clienteCollection];
      jest.spyOn(clienteService, 'addClienteToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ orden });
      comp.ngOnInit();

      expect(clienteService.query).toHaveBeenCalled();
      expect(clienteService.addClienteToCollectionIfMissing).toHaveBeenCalledWith(
        clienteCollection,
        ...additionalClientes.map(expect.objectContaining)
      );
      expect(comp.clientesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Proveedor query and add missing value', () => {
      const orden: IOrden = { id: 'CBA' };
      const proveedor: IProveedor = { id: '38abfe0e-d797-4549-a071-3a9c23b34685' };
      orden.proveedor = proveedor;

      const proveedorCollection: IProveedor[] = [{ id: '21f39181-12c4-4fdd-adc1-a99c70b80caa' }];
      jest.spyOn(proveedorService, 'query').mockReturnValue(of(new HttpResponse({ body: proveedorCollection })));
      const additionalProveedors = [proveedor];
      const expectedCollection: IProveedor[] = [...additionalProveedors, ...proveedorCollection];
      jest.spyOn(proveedorService, 'addProveedorToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ orden });
      comp.ngOnInit();

      expect(proveedorService.query).toHaveBeenCalled();
      expect(proveedorService.addProveedorToCollectionIfMissing).toHaveBeenCalledWith(
        proveedorCollection,
        ...additionalProveedors.map(expect.objectContaining)
      );
      expect(comp.proveedorsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Autorizacion query and add missing value', () => {
      const orden: IOrden = { id: 'CBA' };
      const autorizacion: IAutorizacion = { id: '12593e6e-8a24-41d1-9d6f-662d29fbdc7e' };
      orden.autorizacion = autorizacion;

      const autorizacionCollection: IAutorizacion[] = [{ id: 'b5ec060a-8afd-41b0-9b31-54abb9723205' }];
      jest.spyOn(autorizacionService, 'query').mockReturnValue(of(new HttpResponse({ body: autorizacionCollection })));
      const additionalAutorizacions = [autorizacion];
      const expectedCollection: IAutorizacion[] = [...additionalAutorizacions, ...autorizacionCollection];
      jest.spyOn(autorizacionService, 'addAutorizacionToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ orden });
      comp.ngOnInit();

      expect(autorizacionService.query).toHaveBeenCalled();
      expect(autorizacionService.addAutorizacionToCollectionIfMissing).toHaveBeenCalledWith(
        autorizacionCollection,
        ...additionalAutorizacions.map(expect.objectContaining)
      );
      expect(comp.autorizacionsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const orden: IOrden = { id: 'CBA' };
      const cliente: ICliente = { id: '59410a40-d345-4a77-9eb3-20eefbc30f69' };
      orden.cliente = cliente;
      const proveedor: IProveedor = { id: '8da29751-94fb-450f-aa97-d51eddf868a7' };
      orden.proveedor = proveedor;
      const autorizacion: IAutorizacion = { id: '8974471c-d5e2-4717-9b31-0a04ac8fc54e' };
      orden.autorizacion = autorizacion;

      activatedRoute.data = of({ orden });
      comp.ngOnInit();

      expect(comp.clientesSharedCollection).toContain(cliente);
      expect(comp.proveedorsSharedCollection).toContain(proveedor);
      expect(comp.autorizacionsSharedCollection).toContain(autorizacion);
      expect(comp.orden).toEqual(orden);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOrden>>();
      const orden = { id: 'ABC' };
      jest.spyOn(ordenFormService, 'getOrden').mockReturnValue(orden);
      jest.spyOn(ordenService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ orden });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: orden }));
      saveSubject.complete();

      // THEN
      expect(ordenFormService.getOrden).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(ordenService.update).toHaveBeenCalledWith(expect.objectContaining(orden));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOrden>>();
      const orden = { id: 'ABC' };
      jest.spyOn(ordenFormService, 'getOrden').mockReturnValue({ id: null });
      jest.spyOn(ordenService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ orden: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: orden }));
      saveSubject.complete();

      // THEN
      expect(ordenFormService.getOrden).toHaveBeenCalled();
      expect(ordenService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOrden>>();
      const orden = { id: 'ABC' };
      jest.spyOn(ordenService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ orden });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ordenService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareCliente', () => {
      it('Should forward to clienteService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(clienteService, 'compareCliente');
        comp.compareCliente(entity, entity2);
        expect(clienteService.compareCliente).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareProveedor', () => {
      it('Should forward to proveedorService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(proveedorService, 'compareProveedor');
        comp.compareProveedor(entity, entity2);
        expect(proveedorService.compareProveedor).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareAutorizacion', () => {
      it('Should forward to autorizacionService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(autorizacionService, 'compareAutorizacion');
        comp.compareAutorizacion(entity, entity2);
        expect(autorizacionService.compareAutorizacion).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
