import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../detalle-opera-contable.test-samples';

import { DetalleOperaContableFormService } from './detalle-opera-contable-form.service';

describe('DetalleOperaContable Form Service', () => {
  let service: DetalleOperaContableFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DetalleOperaContableFormService);
  });

  describe('Service methods', () => {
    describe('createDetalleOperaContableFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDetalleOperaContableFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            operador: expect.any(Object),
            valorOperacion: expect.any(Object),
            descripcion: expect.any(Object),
            tipMovimiento: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            planContable: expect.any(Object),
            operaContable: expect.any(Object),
          })
        );
      });

      it('passing IDetalleOperaContable should create a new form with FormGroup', () => {
        const formGroup = service.createDetalleOperaContableFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            operador: expect.any(Object),
            valorOperacion: expect.any(Object),
            descripcion: expect.any(Object),
            tipMovimiento: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            planContable: expect.any(Object),
            operaContable: expect.any(Object),
          })
        );
      });
    });

    describe('getDetalleOperaContable', () => {
      it('should return NewDetalleOperaContable for default DetalleOperaContable initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createDetalleOperaContableFormGroup(sampleWithNewData);

        const detalleOperaContable = service.getDetalleOperaContable(formGroup) as any;

        expect(detalleOperaContable).toMatchObject(sampleWithNewData);
      });

      it('should return NewDetalleOperaContable for empty DetalleOperaContable initial value', () => {
        const formGroup = service.createDetalleOperaContableFormGroup();

        const detalleOperaContable = service.getDetalleOperaContable(formGroup) as any;

        expect(detalleOperaContable).toMatchObject({});
      });

      it('should return IDetalleOperaContable', () => {
        const formGroup = service.createDetalleOperaContableFormGroup(sampleWithRequiredData);

        const detalleOperaContable = service.getDetalleOperaContable(formGroup) as any;

        expect(detalleOperaContable).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDetalleOperaContable should not enable id FormControl', () => {
        const formGroup = service.createDetalleOperaContableFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDetalleOperaContable should disable id FormControl', () => {
        const formGroup = service.createDetalleOperaContableFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
