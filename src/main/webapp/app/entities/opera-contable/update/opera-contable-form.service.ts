import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IOperaContable, NewOperaContable } from '../opera-contable.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOperaContable for edit and NewOperaContableFormGroupInput for create.
 */
type OperaContableFormGroupInput = IOperaContable | PartialWithRequiredKeyOf<NewOperaContable>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IOperaContable | NewOperaContable> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

type OperaContableFormRawValue = FormValueOf<IOperaContable>;

type NewOperaContableFormRawValue = FormValueOf<NewOperaContable>;

type OperaContableFormDefaults = Pick<NewOperaContable, 'id' | 'fecCrea' | 'fecModif'>;

type OperaContableFormGroupContent = {
  id: FormControl<OperaContableFormRawValue['id'] | NewOperaContable['id']>;
  descOpera: FormControl<OperaContableFormRawValue['descOpera']>;
  areaRela: FormControl<OperaContableFormRawValue['areaRela']>;
  codLib: FormControl<OperaContableFormRawValue['codLib']>;
  fecCrea: FormControl<OperaContableFormRawValue['fecCrea']>;
  usuCrea: FormControl<OperaContableFormRawValue['usuCrea']>;
  ipCrea: FormControl<OperaContableFormRawValue['ipCrea']>;
  fecModif: FormControl<OperaContableFormRawValue['fecModif']>;
  usuModif: FormControl<OperaContableFormRawValue['usuModif']>;
  ipModif: FormControl<OperaContableFormRawValue['ipModif']>;
};

export type OperaContableFormGroup = FormGroup<OperaContableFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OperaContableFormService {
  createOperaContableFormGroup(operaContable: OperaContableFormGroupInput = { id: null }): OperaContableFormGroup {
    const operaContableRawValue = this.convertOperaContableToOperaContableRawValue({
      ...this.getFormDefaults(),
      ...operaContable,
    });
    return new FormGroup<OperaContableFormGroupContent>({
      id: new FormControl(
        { value: operaContableRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      descOpera: new FormControl(operaContableRawValue.descOpera, {
        validators: [Validators.required],
      }),
      areaRela: new FormControl(operaContableRawValue.areaRela),
      codLib: new FormControl(operaContableRawValue.codLib),
      fecCrea: new FormControl(operaContableRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(operaContableRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(operaContableRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(operaContableRawValue.fecModif),
      usuModif: new FormControl(operaContableRawValue.usuModif),
      ipModif: new FormControl(operaContableRawValue.ipModif),
    });
  }

  getOperaContable(form: OperaContableFormGroup): IOperaContable | NewOperaContable {
    return this.convertOperaContableRawValueToOperaContable(form.getRawValue() as OperaContableFormRawValue | NewOperaContableFormRawValue);
  }

  resetForm(form: OperaContableFormGroup, operaContable: OperaContableFormGroupInput): void {
    const operaContableRawValue = this.convertOperaContableToOperaContableRawValue({ ...this.getFormDefaults(), ...operaContable });
    form.reset(
      {
        ...operaContableRawValue,
        id: { value: operaContableRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): OperaContableFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertOperaContableRawValueToOperaContable(
    rawOperaContable: OperaContableFormRawValue | NewOperaContableFormRawValue
  ): IOperaContable | NewOperaContable {
    return {
      ...rawOperaContable,
      fecCrea: dayjs(rawOperaContable.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawOperaContable.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertOperaContableToOperaContableRawValue(
    operaContable: IOperaContable | (Partial<NewOperaContable> & OperaContableFormDefaults)
  ): OperaContableFormRawValue | PartialWithRequiredKeyOf<NewOperaContableFormRawValue> {
    return {
      ...operaContable,
      fecCrea: operaContable.fecCrea ? operaContable.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: operaContable.fecModif ? operaContable.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
