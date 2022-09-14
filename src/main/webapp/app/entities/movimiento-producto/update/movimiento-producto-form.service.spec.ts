import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../movimiento-producto.test-samples';

import { MovimientoProductoFormService } from './movimiento-producto-form.service';

describe('MovimientoProducto Form Service', () => {
  let service: MovimientoProductoFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MovimientoProductoFormService);
  });

  describe('Service methods', () => {
    describe('createMovimientoProductoFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createMovimientoProductoFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipMovimiento: expect.any(Object),
            tip2Movimiento: expect.any(Object),
            precCompra: expect.any(Object),
            cnt: expect.any(Object),
            lote: expect.any(Object),
            fecMovimiento: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            producto: expect.any(Object),
            regVenta: expect.any(Object),
            regCompras: expect.any(Object),
          })
        );
      });

      it('passing IMovimientoProducto should create a new form with FormGroup', () => {
        const formGroup = service.createMovimientoProductoFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipMovimiento: expect.any(Object),
            tip2Movimiento: expect.any(Object),
            precCompra: expect.any(Object),
            cnt: expect.any(Object),
            lote: expect.any(Object),
            fecMovimiento: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            producto: expect.any(Object),
            regVenta: expect.any(Object),
            regCompras: expect.any(Object),
          })
        );
      });
    });

    describe('getMovimientoProducto', () => {
      it('should return NewMovimientoProducto for default MovimientoProducto initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createMovimientoProductoFormGroup(sampleWithNewData);

        const movimientoProducto = service.getMovimientoProducto(formGroup) as any;

        expect(movimientoProducto).toMatchObject(sampleWithNewData);
      });

      it('should return NewMovimientoProducto for empty MovimientoProducto initial value', () => {
        const formGroup = service.createMovimientoProductoFormGroup();

        const movimientoProducto = service.getMovimientoProducto(formGroup) as any;

        expect(movimientoProducto).toMatchObject({});
      });

      it('should return IMovimientoProducto', () => {
        const formGroup = service.createMovimientoProductoFormGroup(sampleWithRequiredData);

        const movimientoProducto = service.getMovimientoProducto(formGroup) as any;

        expect(movimientoProducto).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IMovimientoProducto should not enable id FormControl', () => {
        const formGroup = service.createMovimientoProductoFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewMovimientoProducto should disable id FormControl', () => {
        const formGroup = service.createMovimientoProductoFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
