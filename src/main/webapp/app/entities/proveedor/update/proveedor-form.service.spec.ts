import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../proveedor.test-samples';

import { ProveedorFormService } from './proveedor-form.service';

describe('Proveedor Form Service', () => {
  let service: ProveedorFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProveedorFormService);
  });

  describe('Service methods', () => {
    describe('createProveedorFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createProveedorFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipDocProv: expect.any(Object),
            nroDocProv: expect.any(Object),
            nombresRazonSocProv: expect.any(Object),
            tel1: expect.any(Object),
            tel2: expect.any(Object),
            correo: expect.any(Object),
            direccion: expect.any(Object),
            refDireccion: expect.any(Object),
            distrito: expect.any(Object),
            estado: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
          })
        );
      });

      it('passing IProveedor should create a new form with FormGroup', () => {
        const formGroup = service.createProveedorFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipDocProv: expect.any(Object),
            nroDocProv: expect.any(Object),
            nombresRazonSocProv: expect.any(Object),
            tel1: expect.any(Object),
            tel2: expect.any(Object),
            correo: expect.any(Object),
            direccion: expect.any(Object),
            refDireccion: expect.any(Object),
            distrito: expect.any(Object),
            estado: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
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

    describe('getProveedor', () => {
      it('should return NewProveedor for default Proveedor initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createProveedorFormGroup(sampleWithNewData);

        const proveedor = service.getProveedor(formGroup) as any;

        expect(proveedor).toMatchObject(sampleWithNewData);
      });

      it('should return NewProveedor for empty Proveedor initial value', () => {
        const formGroup = service.createProveedorFormGroup();

        const proveedor = service.getProveedor(formGroup) as any;

        expect(proveedor).toMatchObject({});
      });

      it('should return IProveedor', () => {
        const formGroup = service.createProveedorFormGroup(sampleWithRequiredData);

        const proveedor = service.getProveedor(formGroup) as any;

        expect(proveedor).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IProveedor should not enable id FormControl', () => {
        const formGroup = service.createProveedorFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewProveedor should disable id FormControl', () => {
        const formGroup = service.createProveedorFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
