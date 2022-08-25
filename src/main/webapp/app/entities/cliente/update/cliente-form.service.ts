import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ICliente, NewCliente } from '../cliente.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICliente for edit and NewClienteFormGroupInput for create.
 */
type ClienteFormGroupInput = ICliente | PartialWithRequiredKeyOf<NewCliente>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ICliente | NewCliente> = Omit<T, 'fecNac' | 'fecCrea' | 'fecModif'> & {
  fecNac?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type ClienteFormRawValue = FormValueOf<ICliente>;

type NewClienteFormRawValue = FormValueOf<NewCliente>;

type ClienteFormDefaults = Pick<NewCliente, 'id' | 'fecNac' | 'indDel' | 'fecCrea' | 'fecModif'>;

type ClienteFormGroupContent = {
  id: FormControl<ClienteFormRawValue['id'] | NewCliente['id']>;
  tipDocCli: FormControl<ClienteFormRawValue['tipDocCli']>;
  nroDocCli: FormControl<ClienteFormRawValue['nroDocCli']>;
  nombresCli: FormControl<ClienteFormRawValue['nombresCli']>;
  apellidosCli: FormControl<ClienteFormRawValue['apellidosCli']>;
  tel1: FormControl<ClienteFormRawValue['tel1']>;
  tel2: FormControl<ClienteFormRawValue['tel2']>;
  correo: FormControl<ClienteFormRawValue['correo']>;
  direccion: FormControl<ClienteFormRawValue['direccion']>;
  refDireccion: FormControl<ClienteFormRawValue['refDireccion']>;
  distrito: FormControl<ClienteFormRawValue['distrito']>;
  fecNac: FormControl<ClienteFormRawValue['fecNac']>;
  estado: FormControl<ClienteFormRawValue['estado']>;
  version: FormControl<ClienteFormRawValue['version']>;
  indDel: FormControl<ClienteFormRawValue['indDel']>;
  fecCrea: FormControl<ClienteFormRawValue['fecCrea']>;
  usuCrea: FormControl<ClienteFormRawValue['usuCrea']>;
  ipCrea: FormControl<ClienteFormRawValue['ipCrea']>;
  fecModif: FormControl<ClienteFormRawValue['fecModif']>;
  usuModif: FormControl<ClienteFormRawValue['usuModif']>;
  ipModif: FormControl<ClienteFormRawValue['ipModif']>;
};

export type ClienteFormGroup = FormGroup<ClienteFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ClienteFormService {
  createClienteFormGroup(cliente: ClienteFormGroupInput = { id: null }): ClienteFormGroup {
    const clienteRawValue = this.convertClienteToClienteRawValue({
      ...this.getFormDefaults(),
      ...cliente,
    });
    return new FormGroup<ClienteFormGroupContent>({
      id: new FormControl(
        { value: clienteRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      tipDocCli: new FormControl(clienteRawValue.tipDocCli, {
        validators: [Validators.required],
      }),
      nroDocCli: new FormControl(clienteRawValue.nroDocCli, {
        validators: [Validators.required],
      }),
      nombresCli: new FormControl(clienteRawValue.nombresCli, {
        validators: [Validators.required],
      }),
      apellidosCli: new FormControl(clienteRawValue.apellidosCli, {
        validators: [Validators.required],
      }),
      tel1: new FormControl(clienteRawValue.tel1, {
        validators: [Validators.required],
      }),
      tel2: new FormControl(clienteRawValue.tel2),
      correo: new FormControl(clienteRawValue.correo),
      direccion: new FormControl(clienteRawValue.direccion),
      refDireccion: new FormControl(clienteRawValue.refDireccion),
      distrito: new FormControl(clienteRawValue.distrito),
      fecNac: new FormControl(clienteRawValue.fecNac, {
        validators: [Validators.required],
      }),
      estado: new FormControl(clienteRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(clienteRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(clienteRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(clienteRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(clienteRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(clienteRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(clienteRawValue.fecModif),
      usuModif: new FormControl(clienteRawValue.usuModif),
      ipModif: new FormControl(clienteRawValue.ipModif),
    });
  }

  getCliente(form: ClienteFormGroup): ICliente | NewCliente {
    return this.convertClienteRawValueToCliente(form.getRawValue() as ClienteFormRawValue | NewClienteFormRawValue);
  }

  resetForm(form: ClienteFormGroup, cliente: ClienteFormGroupInput): void {
    const clienteRawValue = this.convertClienteToClienteRawValue({ ...this.getFormDefaults(), ...cliente });
    form.reset(
      {
        ...clienteRawValue,
        id: { value: clienteRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ClienteFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecNac: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertClienteRawValueToCliente(rawCliente: ClienteFormRawValue | NewClienteFormRawValue): ICliente | NewCliente {
    return {
      ...rawCliente,
      fecNac: dayjs(rawCliente.fecNac, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawCliente.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawCliente.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertClienteToClienteRawValue(
    cliente: ICliente | (Partial<NewCliente> & ClienteFormDefaults)
  ): ClienteFormRawValue | PartialWithRequiredKeyOf<NewClienteFormRawValue> {
    return {
      ...cliente,
      fecNac: cliente.fecNac ? cliente.fecNac.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: cliente.fecCrea ? cliente.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: cliente.fecModif ? cliente.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
