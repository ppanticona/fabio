import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IMovimientoProducto, NewMovimientoProducto } from '../movimiento-producto.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMovimientoProducto for edit and NewMovimientoProductoFormGroupInput for create.
 */
type MovimientoProductoFormGroupInput = IMovimientoProducto | PartialWithRequiredKeyOf<NewMovimientoProducto>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IMovimientoProducto | NewMovimientoProducto> = Omit<T, 'fecMovimiento' | 'fecCrea' | 'fecModif'> & {
  fecMovimiento?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type MovimientoProductoFormRawValue = FormValueOf<IMovimientoProducto>;

type NewMovimientoProductoFormRawValue = FormValueOf<NewMovimientoProducto>;

type MovimientoProductoFormDefaults = Pick<NewMovimientoProducto, 'id' | 'fecMovimiento' | 'indDel' | 'fecCrea' | 'fecModif'>;

type MovimientoProductoFormGroupContent = {
  id: FormControl<MovimientoProductoFormRawValue['id'] | NewMovimientoProducto['id']>;
  tipMovimiento: FormControl<MovimientoProductoFormRawValue['tipMovimiento']>;
  tip2Movimiento: FormControl<MovimientoProductoFormRawValue['tip2Movimiento']>;
  cnt: FormControl<MovimientoProductoFormRawValue['cnt']>;
  lote: FormControl<MovimientoProductoFormRawValue['lote']>;
  fecMovimiento: FormControl<MovimientoProductoFormRawValue['fecMovimiento']>;
  version: FormControl<MovimientoProductoFormRawValue['version']>;
  indDel: FormControl<MovimientoProductoFormRawValue['indDel']>;
  fecCrea: FormControl<MovimientoProductoFormRawValue['fecCrea']>;
  usuCrea: FormControl<MovimientoProductoFormRawValue['usuCrea']>;
  ipCrea: FormControl<MovimientoProductoFormRawValue['ipCrea']>;
  fecModif: FormControl<MovimientoProductoFormRawValue['fecModif']>;
  usuModif: FormControl<MovimientoProductoFormRawValue['usuModif']>;
  ipModif: FormControl<MovimientoProductoFormRawValue['ipModif']>;
  producto: FormControl<MovimientoProductoFormRawValue['producto']>;
  regVenta: FormControl<MovimientoProductoFormRawValue['regVenta']>;
  regCompras: FormControl<MovimientoProductoFormRawValue['regCompras']>;
};

export type MovimientoProductoFormGroup = FormGroup<MovimientoProductoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MovimientoProductoFormService {
  createMovimientoProductoFormGroup(movimientoProducto: MovimientoProductoFormGroupInput = { id: null }): MovimientoProductoFormGroup {
    const movimientoProductoRawValue = this.convertMovimientoProductoToMovimientoProductoRawValue({
      ...this.getFormDefaults(),
      ...movimientoProducto,
    });
    return new FormGroup<MovimientoProductoFormGroupContent>({
      id: new FormControl(
        { value: movimientoProductoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      tipMovimiento: new FormControl(movimientoProductoRawValue.tipMovimiento),
      tip2Movimiento: new FormControl(movimientoProductoRawValue.tip2Movimiento),
      cnt: new FormControl(movimientoProductoRawValue.cnt),
      lote: new FormControl(movimientoProductoRawValue.lote),
      fecMovimiento: new FormControl(movimientoProductoRawValue.fecMovimiento),
      version: new FormControl(movimientoProductoRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(movimientoProductoRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(movimientoProductoRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(movimientoProductoRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(movimientoProductoRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(movimientoProductoRawValue.fecModif),
      usuModif: new FormControl(movimientoProductoRawValue.usuModif),
      ipModif: new FormControl(movimientoProductoRawValue.ipModif),
      producto: new FormControl(movimientoProductoRawValue.producto),
      regVenta: new FormControl(movimientoProductoRawValue.regVenta),
      regCompras: new FormControl(movimientoProductoRawValue.regCompras),
    });
  }

  getMovimientoProducto(form: MovimientoProductoFormGroup): IMovimientoProducto | NewMovimientoProducto {
    return this.convertMovimientoProductoRawValueToMovimientoProducto(
      form.getRawValue() as MovimientoProductoFormRawValue | NewMovimientoProductoFormRawValue
    );
  }

  resetForm(form: MovimientoProductoFormGroup, movimientoProducto: MovimientoProductoFormGroupInput): void {
    const movimientoProductoRawValue = this.convertMovimientoProductoToMovimientoProductoRawValue({
      ...this.getFormDefaults(),
      ...movimientoProducto,
    });
    form.reset(
      {
        ...movimientoProductoRawValue,
        id: { value: movimientoProductoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): MovimientoProductoFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecMovimiento: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertMovimientoProductoRawValueToMovimientoProducto(
    rawMovimientoProducto: MovimientoProductoFormRawValue | NewMovimientoProductoFormRawValue
  ): IMovimientoProducto | NewMovimientoProducto {
    return {
      ...rawMovimientoProducto,
      fecMovimiento: dayjs(rawMovimientoProducto.fecMovimiento, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawMovimientoProducto.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawMovimientoProducto.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertMovimientoProductoToMovimientoProductoRawValue(
    movimientoProducto: IMovimientoProducto | (Partial<NewMovimientoProducto> & MovimientoProductoFormDefaults)
  ): MovimientoProductoFormRawValue | PartialWithRequiredKeyOf<NewMovimientoProductoFormRawValue> {
    return {
      ...movimientoProducto,
      fecMovimiento: movimientoProducto.fecMovimiento ? movimientoProducto.fecMovimiento.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: movimientoProducto.fecCrea ? movimientoProducto.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: movimientoProducto.fecModif ? movimientoProducto.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
