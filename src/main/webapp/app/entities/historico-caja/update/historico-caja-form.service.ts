import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IHistoricoCaja, NewHistoricoCaja } from '../historico-caja.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IHistoricoCaja for edit and NewHistoricoCajaFormGroupInput for create.
 */
type HistoricoCajaFormGroupInput = IHistoricoCaja | PartialWithRequiredKeyOf<NewHistoricoCaja>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IHistoricoCaja | NewHistoricoCaja> = Omit<T, 'fecIniVig' | 'fecFinVig' | 'fecCrea' | 'fecModif'> & {
  fecIniVig?: string | null;
  fecFinVig?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type HistoricoCajaFormRawValue = FormValueOf<IHistoricoCaja>;

type NewHistoricoCajaFormRawValue = FormValueOf<NewHistoricoCaja>;

type HistoricoCajaFormDefaults = Pick<NewHistoricoCaja, 'id' | 'fecIniVig' | 'fecFinVig' | 'indDel' | 'fecCrea' | 'fecModif'>;

type HistoricoCajaFormGroupContent = {
  id: FormControl<HistoricoCajaFormRawValue['id'] | NewHistoricoCaja['id']>;
  fecIniVig: FormControl<HistoricoCajaFormRawValue['fecIniVig']>;
  fecFinVig: FormControl<HistoricoCajaFormRawValue['fecFinVig']>;
  estado: FormControl<HistoricoCajaFormRawValue['estado']>;
  montoInicialSoles: FormControl<HistoricoCajaFormRawValue['montoInicialSoles']>;
  montoMaximoSoles: FormControl<HistoricoCajaFormRawValue['montoMaximoSoles']>;
  montoRealSoles: FormControl<HistoricoCajaFormRawValue['montoRealSoles']>;
  montoInicialDolares: FormControl<HistoricoCajaFormRawValue['montoInicialDolares']>;
  montoMaximoDolares: FormControl<HistoricoCajaFormRawValue['montoMaximoDolares']>;
  montoRealDolares: FormControl<HistoricoCajaFormRawValue['montoRealDolares']>;
  montoRealOtros: FormControl<HistoricoCajaFormRawValue['montoRealOtros']>;
  usuarioAsignado: FormControl<HistoricoCajaFormRawValue['usuarioAsignado']>;
  comentario: FormControl<HistoricoCajaFormRawValue['comentario']>;
  version: FormControl<HistoricoCajaFormRawValue['version']>;
  indDel: FormControl<HistoricoCajaFormRawValue['indDel']>;
  fecCrea: FormControl<HistoricoCajaFormRawValue['fecCrea']>;
  usuCrea: FormControl<HistoricoCajaFormRawValue['usuCrea']>;
  ipCrea: FormControl<HistoricoCajaFormRawValue['ipCrea']>;
  fecModif: FormControl<HistoricoCajaFormRawValue['fecModif']>;
  usuModif: FormControl<HistoricoCajaFormRawValue['usuModif']>;
  ipModif: FormControl<HistoricoCajaFormRawValue['ipModif']>;
  caja: FormControl<HistoricoCajaFormRawValue['caja']>;
};

export type HistoricoCajaFormGroup = FormGroup<HistoricoCajaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class HistoricoCajaFormService {
  createHistoricoCajaFormGroup(historicoCaja: HistoricoCajaFormGroupInput = { id: null }): HistoricoCajaFormGroup {
    const historicoCajaRawValue = this.convertHistoricoCajaToHistoricoCajaRawValue({
      ...this.getFormDefaults(),
      ...historicoCaja,
    });
    return new FormGroup<HistoricoCajaFormGroupContent>({
      id: new FormControl(
        { value: historicoCajaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      fecIniVig: new FormControl(historicoCajaRawValue.fecIniVig, {
        validators: [Validators.required],
      }),
      fecFinVig: new FormControl(historicoCajaRawValue.fecFinVig, {
        validators: [Validators.required],
      }),
      estado: new FormControl(historicoCajaRawValue.estado, {
        validators: [Validators.required],
      }),
      montoInicialSoles: new FormControl(historicoCajaRawValue.montoInicialSoles, {
        validators: [Validators.required],
      }),
      montoMaximoSoles: new FormControl(historicoCajaRawValue.montoMaximoSoles, {
        validators: [Validators.required],
      }),
      montoRealSoles: new FormControl(historicoCajaRawValue.montoRealSoles),
      montoInicialDolares: new FormControl(historicoCajaRawValue.montoInicialDolares, {
        validators: [Validators.required],
      }),
      montoMaximoDolares: new FormControl(historicoCajaRawValue.montoMaximoDolares, {
        validators: [Validators.required],
      }),
      montoRealDolares: new FormControl(historicoCajaRawValue.montoRealDolares),
      montoRealOtros: new FormControl(historicoCajaRawValue.montoRealOtros),
      usuarioAsignado: new FormControl(historicoCajaRawValue.usuarioAsignado),
      comentario: new FormControl(historicoCajaRawValue.comentario),
      version: new FormControl(historicoCajaRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(historicoCajaRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(historicoCajaRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(historicoCajaRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(historicoCajaRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(historicoCajaRawValue.fecModif),
      usuModif: new FormControl(historicoCajaRawValue.usuModif),
      ipModif: new FormControl(historicoCajaRawValue.ipModif),
      caja: new FormControl(historicoCajaRawValue.caja),
    });
  }

  getHistoricoCaja(form: HistoricoCajaFormGroup): IHistoricoCaja | NewHistoricoCaja {
    return this.convertHistoricoCajaRawValueToHistoricoCaja(form.getRawValue() as HistoricoCajaFormRawValue | NewHistoricoCajaFormRawValue);
  }

  resetForm(form: HistoricoCajaFormGroup, historicoCaja: HistoricoCajaFormGroupInput): void {
    const historicoCajaRawValue = this.convertHistoricoCajaToHistoricoCajaRawValue({ ...this.getFormDefaults(), ...historicoCaja });
    form.reset(
      {
        ...historicoCajaRawValue,
        id: { value: historicoCajaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): HistoricoCajaFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecIniVig: currentTime,
      fecFinVig: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertHistoricoCajaRawValueToHistoricoCaja(
    rawHistoricoCaja: HistoricoCajaFormRawValue | NewHistoricoCajaFormRawValue
  ): IHistoricoCaja | NewHistoricoCaja {
    return {
      ...rawHistoricoCaja,
      fecIniVig: dayjs(rawHistoricoCaja.fecIniVig, DATE_TIME_FORMAT),
      fecFinVig: dayjs(rawHistoricoCaja.fecFinVig, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawHistoricoCaja.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawHistoricoCaja.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertHistoricoCajaToHistoricoCajaRawValue(
    historicoCaja: IHistoricoCaja | (Partial<NewHistoricoCaja> & HistoricoCajaFormDefaults)
  ): HistoricoCajaFormRawValue | PartialWithRequiredKeyOf<NewHistoricoCajaFormRawValue> {
    return {
      ...historicoCaja,
      fecIniVig: historicoCaja.fecIniVig ? historicoCaja.fecIniVig.format(DATE_TIME_FORMAT) : undefined,
      fecFinVig: historicoCaja.fecFinVig ? historicoCaja.fecFinVig.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: historicoCaja.fecCrea ? historicoCaja.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: historicoCaja.fecModif ? historicoCaja.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
