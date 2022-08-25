import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IProveedor, NewProveedor } from '../proveedor.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IProveedor for edit and NewProveedorFormGroupInput for create.
 */
type ProveedorFormGroupInput = IProveedor | PartialWithRequiredKeyOf<NewProveedor>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IProveedor | NewProveedor> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

type ProveedorFormRawValue = FormValueOf<IProveedor>;

type NewProveedorFormRawValue = FormValueOf<NewProveedor>;

type ProveedorFormDefaults = Pick<NewProveedor, 'id' | 'indDel' | 'fecCrea' | 'fecModif'>;

type ProveedorFormGroupContent = {
  id: FormControl<ProveedorFormRawValue['id'] | NewProveedor['id']>;
  tipDocProv: FormControl<ProveedorFormRawValue['tipDocProv']>;
  nroDocProv: FormControl<ProveedorFormRawValue['nroDocProv']>;
  nombresRazonSocProv: FormControl<ProveedorFormRawValue['nombresRazonSocProv']>;
  tel1: FormControl<ProveedorFormRawValue['tel1']>;
  tel2: FormControl<ProveedorFormRawValue['tel2']>;
  correo: FormControl<ProveedorFormRawValue['correo']>;
  direccion: FormControl<ProveedorFormRawValue['direccion']>;
  refDireccion: FormControl<ProveedorFormRawValue['refDireccion']>;
  distrito: FormControl<ProveedorFormRawValue['distrito']>;
  estado: FormControl<ProveedorFormRawValue['estado']>;
  version: FormControl<ProveedorFormRawValue['version']>;
  indDel: FormControl<ProveedorFormRawValue['indDel']>;
  fecCrea: FormControl<ProveedorFormRawValue['fecCrea']>;
  usuCrea: FormControl<ProveedorFormRawValue['usuCrea']>;
  ipCrea: FormControl<ProveedorFormRawValue['ipCrea']>;
  fecModif: FormControl<ProveedorFormRawValue['fecModif']>;
  usuModif: FormControl<ProveedorFormRawValue['usuModif']>;
  ipModif: FormControl<ProveedorFormRawValue['ipModif']>;
};

export type ProveedorFormGroup = FormGroup<ProveedorFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ProveedorFormService {
  createProveedorFormGroup(proveedor: ProveedorFormGroupInput = { id: null }): ProveedorFormGroup {
    const proveedorRawValue = this.convertProveedorToProveedorRawValue({
      ...this.getFormDefaults(),
      ...proveedor,
    });
    return new FormGroup<ProveedorFormGroupContent>({
      id: new FormControl(
        { value: proveedorRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      tipDocProv: new FormControl(proveedorRawValue.tipDocProv, {
        validators: [Validators.required],
      }),
      nroDocProv: new FormControl(proveedorRawValue.nroDocProv, {
        validators: [Validators.required],
      }),
      nombresRazonSocProv: new FormControl(proveedorRawValue.nombresRazonSocProv, {
        validators: [Validators.required],
      }),
      tel1: new FormControl(proveedorRawValue.tel1),
      tel2: new FormControl(proveedorRawValue.tel2),
      correo: new FormControl(proveedorRawValue.correo),
      direccion: new FormControl(proveedorRawValue.direccion),
      refDireccion: new FormControl(proveedorRawValue.refDireccion),
      distrito: new FormControl(proveedorRawValue.distrito),
      estado: new FormControl(proveedorRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(proveedorRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(proveedorRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(proveedorRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(proveedorRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(proveedorRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(proveedorRawValue.fecModif),
      usuModif: new FormControl(proveedorRawValue.usuModif),
      ipModif: new FormControl(proveedorRawValue.ipModif),
    });
  }

  getProveedor(form: ProveedorFormGroup): IProveedor | NewProveedor {
    return this.convertProveedorRawValueToProveedor(form.getRawValue() as ProveedorFormRawValue | NewProveedorFormRawValue);
  }

  resetForm(form: ProveedorFormGroup, proveedor: ProveedorFormGroupInput): void {
    const proveedorRawValue = this.convertProveedorToProveedorRawValue({ ...this.getFormDefaults(), ...proveedor });
    form.reset(
      {
        ...proveedorRawValue,
        id: { value: proveedorRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ProveedorFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertProveedorRawValueToProveedor(rawProveedor: ProveedorFormRawValue | NewProveedorFormRawValue): IProveedor | NewProveedor {
    return {
      ...rawProveedor,
      fecCrea: dayjs(rawProveedor.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawProveedor.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertProveedorToProveedorRawValue(
    proveedor: IProveedor | (Partial<NewProveedor> & ProveedorFormDefaults)
  ): ProveedorFormRawValue | PartialWithRequiredKeyOf<NewProveedorFormRawValue> {
    return {
      ...proveedor,
      fecCrea: proveedor.fecCrea ? proveedor.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: proveedor.fecModif ? proveedor.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
