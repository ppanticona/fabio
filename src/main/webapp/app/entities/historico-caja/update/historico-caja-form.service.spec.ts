import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../historico-caja.test-samples';

import { HistoricoCajaFormService } from './historico-caja-form.service';

describe('HistoricoCaja Form Service', () => {
  let service: HistoricoCajaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HistoricoCajaFormService);
  });

  describe('Service methods', () => {
    describe('createHistoricoCajaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createHistoricoCajaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            fecIniVig: expect.any(Object),
            fecFinVig: expect.any(Object),
            estado: expect.any(Object),
            montoInicialSoles: expect.any(Object),
            montoMaximoSoles: expect.any(Object),
            montoRealSoles: expect.any(Object),
            montoInicialDolares: expect.any(Object),
            montoMaximoDolares: expect.any(Object),
            montoRealDolares: expect.any(Object),
            montoRealOtros: expect.any(Object),
            usuarioAsignado: expect.any(Object),
            comentario: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            caja: expect.any(Object),
          })
        );
      });

      it('passing IHistoricoCaja should create a new form with FormGroup', () => {
        const formGroup = service.createHistoricoCajaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            fecIniVig: expect.any(Object),
            fecFinVig: expect.any(Object),
            estado: expect.any(Object),
            montoInicialSoles: expect.any(Object),
            montoMaximoSoles: expect.any(Object),
            montoRealSoles: expect.any(Object),
            montoInicialDolares: expect.any(Object),
            montoMaximoDolares: expect.any(Object),
            montoRealDolares: expect.any(Object),
            montoRealOtros: expect.any(Object),
            usuarioAsignado: expect.any(Object),
            comentario: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            caja: expect.any(Object),
          })
        );
      });
    });

    describe('getHistoricoCaja', () => {
      it('should return NewHistoricoCaja for default HistoricoCaja initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createHistoricoCajaFormGroup(sampleWithNewData);

        const historicoCaja = service.getHistoricoCaja(formGroup) as any;

        expect(historicoCaja).toMatchObject(sampleWithNewData);
      });

      it('should return NewHistoricoCaja for empty HistoricoCaja initial value', () => {
        const formGroup = service.createHistoricoCajaFormGroup();

        const historicoCaja = service.getHistoricoCaja(formGroup) as any;

        expect(historicoCaja).toMatchObject({});
      });

      it('should return IHistoricoCaja', () => {
        const formGroup = service.createHistoricoCajaFormGroup(sampleWithRequiredData);

        const historicoCaja = service.getHistoricoCaja(formGroup) as any;

        expect(historicoCaja).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IHistoricoCaja should not enable id FormControl', () => {
        const formGroup = service.createHistoricoCajaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewHistoricoCaja should disable id FormControl', () => {
        const formGroup = service.createHistoricoCajaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
