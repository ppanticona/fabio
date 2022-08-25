import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../lib-diario.test-samples';

import { LibDiarioFormService } from './lib-diario-form.service';

describe('LibDiario Form Service', () => {
  let service: LibDiarioFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LibDiarioFormService);
  });

  describe('Service methods', () => {
    describe('createLibDiarioFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createLibDiarioFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            periodo: expect.any(Object),
            cuo: expect.any(Object),
            asientContab: expect.any(Object),
            codCtaContable: expect.any(Object),
            codUnidOper: expect.any(Object),
            codCentroCui: expect.any(Object),
            tipMonedaOri: expect.any(Object),
            tipDocIdenEmi: expect.any(Object),
            nroDocIdenEmi: expect.any(Object),
            tipCompDocAsoc: expect.any(Object),
            nroSerCompDocAsoc: expect.any(Object),
            nroCompDocAsoc: expect.any(Object),
            fecContable: expect.any(Object),
            fecVenc: expect.any(Object),
            fecOpeEmi: expect.any(Object),
            descOperac: expect.any(Object),
            glosaRef: expect.any(Object),
            debe: expect.any(Object),
            haber: expect.any(Object),
            datoEstruct: expect.any(Object),
            indEstOpe: expect.any(Object),
            campoLibre: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
          })
        );
      });

      it('passing ILibDiario should create a new form with FormGroup', () => {
        const formGroup = service.createLibDiarioFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            periodo: expect.any(Object),
            cuo: expect.any(Object),
            asientContab: expect.any(Object),
            codCtaContable: expect.any(Object),
            codUnidOper: expect.any(Object),
            codCentroCui: expect.any(Object),
            tipMonedaOri: expect.any(Object),
            tipDocIdenEmi: expect.any(Object),
            nroDocIdenEmi: expect.any(Object),
            tipCompDocAsoc: expect.any(Object),
            nroSerCompDocAsoc: expect.any(Object),
            nroCompDocAsoc: expect.any(Object),
            fecContable: expect.any(Object),
            fecVenc: expect.any(Object),
            fecOpeEmi: expect.any(Object),
            descOperac: expect.any(Object),
            glosaRef: expect.any(Object),
            debe: expect.any(Object),
            haber: expect.any(Object),
            datoEstruct: expect.any(Object),
            indEstOpe: expect.any(Object),
            campoLibre: expect.any(Object),
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

    describe('getLibDiario', () => {
      it('should return NewLibDiario for default LibDiario initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createLibDiarioFormGroup(sampleWithNewData);

        const libDiario = service.getLibDiario(formGroup) as any;

        expect(libDiario).toMatchObject(sampleWithNewData);
      });

      it('should return NewLibDiario for empty LibDiario initial value', () => {
        const formGroup = service.createLibDiarioFormGroup();

        const libDiario = service.getLibDiario(formGroup) as any;

        expect(libDiario).toMatchObject({});
      });

      it('should return ILibDiario', () => {
        const formGroup = service.createLibDiarioFormGroup(sampleWithRequiredData);

        const libDiario = service.getLibDiario(formGroup) as any;

        expect(libDiario).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ILibDiario should not enable id FormControl', () => {
        const formGroup = service.createLibDiarioFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewLibDiario should disable id FormControl', () => {
        const formGroup = service.createLibDiarioFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
