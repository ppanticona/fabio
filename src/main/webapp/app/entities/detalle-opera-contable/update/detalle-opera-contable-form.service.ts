import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IDetalleOperaContable, NewDetalleOperaContable } from '../detalle-opera-contable.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDetalleOperaContable for edit and NewDetalleOperaContableFormGroupInput for create.
 */
type DetalleOperaContableFormGroupInput = IDetalleOperaContable | PartialWithRequiredKeyOf<NewDetalleOperaContable>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IDetalleOperaContable | NewDetalleOperaContable> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

type DetalleOperaContableFormRawValue = FormValueOf<IDetalleOperaContable>;

type NewDetalleOperaContableFormRawValue = FormValueOf<NewDetalleOperaContable>;

type DetalleOperaContableFormDefaults = Pick<NewDetalleOperaContable, 'id' | 'fecCrea' | 'fecModif'>;

type DetalleOperaContableFormGroupContent = {
  id: FormControl<DetalleOperaContableFormRawValue['id'] | NewDetalleOperaContable['id']>;
  operador: FormControl<DetalleOperaContableFormRawValue['operador']>;
  valorOperacion: FormControl<DetalleOperaContableFormRawValue['valorOperacion']>;
  descripcion: FormControl<DetalleOperaContableFormRawValue['descripcion']>;
  tipMovimiento: FormControl<DetalleOperaContableFormRawValue['tipMovimiento']>;
  fecCrea: FormControl<DetalleOperaContableFormRawValue['fecCrea']>;
  usuCrea: FormControl<DetalleOperaContableFormRawValue['usuCrea']>;
  ipCrea: FormControl<DetalleOperaContableFormRawValue['ipCrea']>;
  fecModif: FormControl<DetalleOperaContableFormRawValue['fecModif']>;
  usuModif: FormControl<DetalleOperaContableFormRawValue['usuModif']>;
  ipModif: FormControl<DetalleOperaContableFormRawValue['ipModif']>;
  planContable: FormControl<DetalleOperaContableFormRawValue['planContable']>;
  operaContable: FormControl<DetalleOperaContableFormRawValue['operaContable']>;
};

export type DetalleOperaContableFormGroup = FormGroup<DetalleOperaContableFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DetalleOperaContableFormService {
  createDetalleOperaContableFormGroup(
    detalleOperaContable: DetalleOperaContableFormGroupInput = { id: null }
  ): DetalleOperaContableFormGroup {
    const detalleOperaContableRawValue = this.convertDetalleOperaContableToDetalleOperaContableRawValue({
      ...this.getFormDefaults(),
      ...detalleOperaContable,
    });
    return new FormGroup<DetalleOperaContableFormGroupContent>({
      id: new FormControl(
        { value: detalleOperaContableRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      operador: new FormControl(detalleOperaContableRawValue.operador),
      valorOperacion: new FormControl(detalleOperaContableRawValue.valorOperacion),
      descripcion: new FormControl(detalleOperaContableRawValue.descripcion),
      tipMovimiento: new FormControl(detalleOperaContableRawValue.tipMovimiento),
      fecCrea: new FormControl(detalleOperaContableRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(detalleOperaContableRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(detalleOperaContableRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(detalleOperaContableRawValue.fecModif),
      usuModif: new FormControl(detalleOperaContableRawValue.usuModif),
      ipModif: new FormControl(detalleOperaContableRawValue.ipModif),
      planContable: new FormControl(detalleOperaContableRawValue.planContable),
      operaContable: new FormControl(detalleOperaContableRawValue.operaContable),
    });
  }

  getDetalleOperaContable(form: DetalleOperaContableFormGroup): IDetalleOperaContable | NewDetalleOperaContable {
    return this.convertDetalleOperaContableRawValueToDetalleOperaContable(
      form.getRawValue() as DetalleOperaContableFormRawValue | NewDetalleOperaContableFormRawValue
    );
  }

  resetForm(form: DetalleOperaContableFormGroup, detalleOperaContable: DetalleOperaContableFormGroupInput): void {
    const detalleOperaContableRawValue = this.convertDetalleOperaContableToDetalleOperaContableRawValue({
      ...this.getFormDefaults(),
      ...detalleOperaContable,
    });
    form.reset(
      {
        ...detalleOperaContableRawValue,
        id: { value: detalleOperaContableRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DetalleOperaContableFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertDetalleOperaContableRawValueToDetalleOperaContable(
    rawDetalleOperaContable: DetalleOperaContableFormRawValue | NewDetalleOperaContableFormRawValue
  ): IDetalleOperaContable | NewDetalleOperaContable {
    return {
      ...rawDetalleOperaContable,
      fecCrea: dayjs(rawDetalleOperaContable.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawDetalleOperaContable.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertDetalleOperaContableToDetalleOperaContableRawValue(
    detalleOperaContable: IDetalleOperaContable | (Partial<NewDetalleOperaContable> & DetalleOperaContableFormDefaults)
  ): DetalleOperaContableFormRawValue | PartialWithRequiredKeyOf<NewDetalleOperaContableFormRawValue> {
    return {
      ...detalleOperaContable,
      fecCrea: detalleOperaContable.fecCrea ? detalleOperaContable.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: detalleOperaContable.fecModif ? detalleOperaContable.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
