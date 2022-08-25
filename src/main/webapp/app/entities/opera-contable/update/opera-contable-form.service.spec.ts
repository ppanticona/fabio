import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../opera-contable.test-samples';

import { OperaContableFormService } from './opera-contable-form.service';

describe('OperaContable Form Service', () => {
  let service: OperaContableFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OperaContableFormService);
  });

  describe('Service methods', () => {
    describe('createOperaContableFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOperaContableFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            descOpera: expect.any(Object),
            areaRela: expect.any(Object),
            codLib: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
          })
        );
      });

      it('passing IOperaContable should create a new form with FormGroup', () => {
        const formGroup = service.createOperaContableFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            descOpera: expect.any(Object),
            areaRela: expect.any(Object),
            codLib: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
          })
        );
      });
    });

    describe('getOperaContable', () => {
      it('should return NewOperaContable for default OperaContable initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createOperaContableFormGroup(sampleWithNewData);

        const operaContable = service.getOperaContable(formGroup) as any;

        expect(operaContable).toMatchObject(sampleWithNewData);
      });

      it('should return NewOperaContable for empty OperaContable initial value', () => {
        const formGroup = service.createOperaContableFormGroup();

        const operaContable = service.getOperaContable(formGroup) as any;

        expect(operaContable).toMatchObject({});
      });

      it('should return IOperaContable', () => {
        const formGroup = service.createOperaContableFormGroup(sampleWithRequiredData);

        const operaContable = service.getOperaContable(formGroup) as any;

        expect(operaContable).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOperaContable should not enable id FormControl', () => {
        const formGroup = service.createOperaContableFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOperaContable should disable id FormControl', () => {
        const formGroup = service.createOperaContableFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
