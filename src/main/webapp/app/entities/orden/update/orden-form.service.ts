import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IOrden, NewOrden } from '../orden.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOrden for edit and NewOrdenFormGroupInput for create.
 */
type OrdenFormGroupInput = IOrden | PartialWithRequiredKeyOf<NewOrden>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IOrden | NewOrden> = Omit<T, 'fecEstiEnt' | 'fecRecog' | 'fecCrea' | 'fecModif'> & {
  fecEstiEnt?: string | null;
  fecRecog?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type OrdenFormRawValue = FormValueOf<IOrden>;

type NewOrdenFormRawValue = FormValueOf<NewOrden>;

type OrdenFormDefaults = Pick<NewOrden, 'id' | 'fecEstiEnt' | 'fecRecog' | 'indDel' | 'fecCrea' | 'fecModif'>;

type OrdenFormGroupContent = {
  id: FormControl<OrdenFormRawValue['id'] | NewOrden['id']>;
  numOrden: FormControl<OrdenFormRawValue['numOrden']>;
  fecEstiEnt: FormControl<OrdenFormRawValue['fecEstiEnt']>;
  fecRecog: FormControl<OrdenFormRawValue['fecRecog']>;
  observacion: FormControl<OrdenFormRawValue['observacion']>;
  tipOrden: FormControl<OrdenFormRawValue['tipOrden']>;
  estado: FormControl<OrdenFormRawValue['estado']>;
  version: FormControl<OrdenFormRawValue['version']>;
  indDel: FormControl<OrdenFormRawValue['indDel']>;
  fecCrea: FormControl<OrdenFormRawValue['fecCrea']>;
  usuCrea: FormControl<OrdenFormRawValue['usuCrea']>;
  ipCrea: FormControl<OrdenFormRawValue['ipCrea']>;
  fecModif: FormControl<OrdenFormRawValue['fecModif']>;
  usuModif: FormControl<OrdenFormRawValue['usuModif']>;
  ipModif: FormControl<OrdenFormRawValue['ipModif']>;
  cliente: FormControl<OrdenFormRawValue['cliente']>;
  proveedor: FormControl<OrdenFormRawValue['proveedor']>;
  autorizacion: FormControl<OrdenFormRawValue['autorizacion']>;
};

export type OrdenFormGroup = FormGroup<OrdenFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OrdenFormService {
  createOrdenFormGroup(orden: OrdenFormGroupInput = { id: null }): OrdenFormGroup {
    const ordenRawValue = this.convertOrdenToOrdenRawValue({
      ...this.getFormDefaults(),
      ...orden,
    });
    return new FormGroup<OrdenFormGroupContent>({
      id: new FormControl(
        { value: ordenRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      numOrden: new FormControl(ordenRawValue.numOrden, {
        validators: [Validators.required],
      }),
      fecEstiEnt: new FormControl(ordenRawValue.fecEstiEnt),
      fecRecog: new FormControl(ordenRawValue.fecRecog),
      observacion: new FormControl(ordenRawValue.observacion),
      tipOrden: new FormControl(ordenRawValue.tipOrden, {
        validators: [Validators.required],
      }),
      estado: new FormControl(ordenRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(ordenRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(ordenRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(ordenRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(ordenRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(ordenRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(ordenRawValue.fecModif),
      usuModif: new FormControl(ordenRawValue.usuModif),
      ipModif: new FormControl(ordenRawValue.ipModif),
      cliente: new FormControl(ordenRawValue.cliente),
      proveedor: new FormControl(ordenRawValue.proveedor),
      autorizacion: new FormControl(ordenRawValue.autorizacion),
    });
  }

  getOrden(form: OrdenFormGroup): IOrden | NewOrden {
    return this.convertOrdenRawValueToOrden(form.getRawValue() as OrdenFormRawValue | NewOrdenFormRawValue);
  }

  resetForm(form: OrdenFormGroup, orden: OrdenFormGroupInput): void {
    const ordenRawValue = this.convertOrdenToOrdenRawValue({ ...this.getFormDefaults(), ...orden });
    form.reset(
      {
        ...ordenRawValue,
        id: { value: ordenRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): OrdenFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecEstiEnt: currentTime,
      fecRecog: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertOrdenRawValueToOrden(rawOrden: OrdenFormRawValue | NewOrdenFormRawValue): IOrden | NewOrden {
    return {
      ...rawOrden,
      fecEstiEnt: dayjs(rawOrden.fecEstiEnt, DATE_TIME_FORMAT),
      fecRecog: dayjs(rawOrden.fecRecog, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawOrden.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawOrden.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertOrdenToOrdenRawValue(
    orden: IOrden | (Partial<NewOrden> & OrdenFormDefaults)
  ): OrdenFormRawValue | PartialWithRequiredKeyOf<NewOrdenFormRawValue> {
    return {
      ...orden,
      fecEstiEnt: orden.fecEstiEnt ? orden.fecEstiEnt.format(DATE_TIME_FORMAT) : undefined,
      fecRecog: orden.fecRecog ? orden.fecRecog.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: orden.fecCrea ? orden.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: orden.fecModif ? orden.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
