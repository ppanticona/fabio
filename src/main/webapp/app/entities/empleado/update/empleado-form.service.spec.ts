import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../empleado.test-samples';

import { EmpleadoFormService } from './empleado-form.service';

describe('Empleado Form Service', () => {
  let service: EmpleadoFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmpleadoFormService);
  });

  describe('Service methods', () => {
    describe('createEmpleadoFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createEmpleadoFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipDocEmp: expect.any(Object),
            nroDocEmp: expect.any(Object),
            nombresEmp: expect.any(Object),
            apellidosEmp: expect.any(Object),
            categoria: expect.any(Object),
            tel1: expect.any(Object),
            tel2: expect.any(Object),
            correo: expect.any(Object),
            direccion: expect.any(Object),
            refDirecc: expect.any(Object),
            distrito: expect.any(Object),
            fecIngreso: expect.any(Object),
            fecNac: expect.any(Object),
            userId: expect.any(Object),
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

      it('passing IEmpleado should create a new form with FormGroup', () => {
        const formGroup = service.createEmpleadoFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipDocEmp: expect.any(Object),
            nroDocEmp: expect.any(Object),
            nombresEmp: expect.any(Object),
            apellidosEmp: expect.any(Object),
            categoria: expect.any(Object),
            tel1: expect.any(Object),
            tel2: expect.any(Object),
            correo: expect.any(Object),
            direccion: expect.any(Object),
            refDirecc: expect.any(Object),
            distrito: expect.any(Object),
            fecIngreso: expect.any(Object),
            fecNac: expect.any(Object),
            userId: expect.any(Object),
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

    describe('getEmpleado', () => {
      it('should return NewEmpleado for default Empleado initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createEmpleadoFormGroup(sampleWithNewData);

        const empleado = service.getEmpleado(formGroup) as any;

        expect(empleado).toMatchObject(sampleWithNewData);
      });

      it('should return NewEmpleado for empty Empleado initial value', () => {
        const formGroup = service.createEmpleadoFormGroup();

        const empleado = service.getEmpleado(formGroup) as any;

        expect(empleado).toMatchObject({});
      });

      it('should return IEmpleado', () => {
        const formGroup = service.createEmpleadoFormGroup(sampleWithRequiredData);

        const empleado = service.getEmpleado(formGroup) as any;

        expect(empleado).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IEmpleado should not enable id FormControl', () => {
        const formGroup = service.createEmpleadoFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewEmpleado should disable id FormControl', () => {
        const formGroup = service.createEmpleadoFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
