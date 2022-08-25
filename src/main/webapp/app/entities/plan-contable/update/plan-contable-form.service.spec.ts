import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../plan-contable.test-samples';

import { PlanContableFormService } from './plan-contable-form.service';

describe('PlanContable Form Service', () => {
  let service: PlanContableFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlanContableFormService);
  });

  describe('Service methods', () => {
    describe('createPlanContableFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createPlanContableFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipPlan: expect.any(Object),
            nivPlan: expect.any(Object),
            codPlan: expect.any(Object),
            descCuenta: expect.any(Object),
            anhoPlan: expect.any(Object),
            resoPlan: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
          })
        );
      });

      it('passing IPlanContable should create a new form with FormGroup', () => {
        const formGroup = service.createPlanContableFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipPlan: expect.any(Object),
            nivPlan: expect.any(Object),
            codPlan: expect.any(Object),
            descCuenta: expect.any(Object),
            anhoPlan: expect.any(Object),
            resoPlan: expect.any(Object),
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

    describe('getPlanContable', () => {
      it('should return NewPlanContable for default PlanContable initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createPlanContableFormGroup(sampleWithNewData);

        const planContable = service.getPlanContable(formGroup) as any;

        expect(planContable).toMatchObject(sampleWithNewData);
      });

      it('should return NewPlanContable for empty PlanContable initial value', () => {
        const formGroup = service.createPlanContableFormGroup();

        const planContable = service.getPlanContable(formGroup) as any;

        expect(planContable).toMatchObject({});
      });

      it('should return IPlanContable', () => {
        const formGroup = service.createPlanContableFormGroup(sampleWithRequiredData);

        const planContable = service.getPlanContable(formGroup) as any;

        expect(planContable).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IPlanContable should not enable id FormControl', () => {
        const formGroup = service.createPlanContableFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewPlanContable should disable id FormControl', () => {
        const formGroup = service.createPlanContableFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
