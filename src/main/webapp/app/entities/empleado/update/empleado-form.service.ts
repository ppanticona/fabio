import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IEmpleado, NewEmpleado } from '../empleado.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IEmpleado for edit and NewEmpleadoFormGroupInput for create.
 */
type EmpleadoFormGroupInput = IEmpleado | PartialWithRequiredKeyOf<NewEmpleado>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IEmpleado | NewEmpleado> = Omit<T, 'fecIngreso' | 'fecNac' | 'fecCrea' | 'fecModif'> & {
  fecIngreso?: string | null;
  fecNac?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type EmpleadoFormRawValue = FormValueOf<IEmpleado>;

type NewEmpleadoFormRawValue = FormValueOf<NewEmpleado>;

type EmpleadoFormDefaults = Pick<NewEmpleado, 'id' | 'fecIngreso' | 'fecNac' | 'indDel' | 'fecCrea' | 'fecModif'>;

type EmpleadoFormGroupContent = {
  id: FormControl<EmpleadoFormRawValue['id'] | NewEmpleado['id']>;
  tipDocEmp: FormControl<EmpleadoFormRawValue['tipDocEmp']>;
  nroDocEmp: FormControl<EmpleadoFormRawValue['nroDocEmp']>;
  nombresEmp: FormControl<EmpleadoFormRawValue['nombresEmp']>;
  apellidosEmp: FormControl<EmpleadoFormRawValue['apellidosEmp']>;
  categoria: FormControl<EmpleadoFormRawValue['categoria']>;
  tel1: FormControl<EmpleadoFormRawValue['tel1']>;
  tel2: FormControl<EmpleadoFormRawValue['tel2']>;
  correo: FormControl<EmpleadoFormRawValue['correo']>;
  direccion: FormControl<EmpleadoFormRawValue['direccion']>;
  refDirecc: FormControl<EmpleadoFormRawValue['refDirecc']>;
  distrito: FormControl<EmpleadoFormRawValue['distrito']>;
  fecIngreso: FormControl<EmpleadoFormRawValue['fecIngreso']>;
  fecNac: FormControl<EmpleadoFormRawValue['fecNac']>;
  userId: FormControl<EmpleadoFormRawValue['userId']>;
  estado: FormControl<EmpleadoFormRawValue['estado']>;
  version: FormControl<EmpleadoFormRawValue['version']>;
  indDel: FormControl<EmpleadoFormRawValue['indDel']>;
  fecCrea: FormControl<EmpleadoFormRawValue['fecCrea']>;
  usuCrea: FormControl<EmpleadoFormRawValue['usuCrea']>;
  ipCrea: FormControl<EmpleadoFormRawValue['ipCrea']>;
  fecModif: FormControl<EmpleadoFormRawValue['fecModif']>;
  usuModif: FormControl<EmpleadoFormRawValue['usuModif']>;
  ipModif: FormControl<EmpleadoFormRawValue['ipModif']>;
};

export type EmpleadoFormGroup = FormGroup<EmpleadoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class EmpleadoFormService {
  createEmpleadoFormGroup(empleado: EmpleadoFormGroupInput = { id: null }): EmpleadoFormGroup {
    const empleadoRawValue = this.convertEmpleadoToEmpleadoRawValue({
      ...this.getFormDefaults(),
      ...empleado,
    });
    return new FormGroup<EmpleadoFormGroupContent>({
      id: new FormControl(
        { value: empleadoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      tipDocEmp: new FormControl(empleadoRawValue.tipDocEmp, {
        validators: [Validators.required],
      }),
      nroDocEmp: new FormControl(empleadoRawValue.nroDocEmp, {
        validators: [Validators.required],
      }),
      nombresEmp: new FormControl(empleadoRawValue.nombresEmp, {
        validators: [Validators.required],
      }),
      apellidosEmp: new FormControl(empleadoRawValue.apellidosEmp, {
        validators: [Validators.required],
      }),
      categoria: new FormControl(empleadoRawValue.categoria),
      tel1: new FormControl(empleadoRawValue.tel1),
      tel2: new FormControl(empleadoRawValue.tel2),
      correo: new FormControl(empleadoRawValue.correo),
      direccion: new FormControl(empleadoRawValue.direccion),
      refDirecc: new FormControl(empleadoRawValue.refDirecc),
      distrito: new FormControl(empleadoRawValue.distrito),
      fecIngreso: new FormControl(empleadoRawValue.fecIngreso),
      fecNac: new FormControl(empleadoRawValue.fecNac, {
        validators: [Validators.required],
      }),
      userId: new FormControl(empleadoRawValue.userId),
      estado: new FormControl(empleadoRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(empleadoRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(empleadoRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(empleadoRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(empleadoRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(empleadoRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(empleadoRawValue.fecModif),
      usuModif: new FormControl(empleadoRawValue.usuModif),
      ipModif: new FormControl(empleadoRawValue.ipModif),
    });
  }

  getEmpleado(form: EmpleadoFormGroup): IEmpleado | NewEmpleado {
    return this.convertEmpleadoRawValueToEmpleado(form.getRawValue() as EmpleadoFormRawValue | NewEmpleadoFormRawValue);
  }

  resetForm(form: EmpleadoFormGroup, empleado: EmpleadoFormGroupInput): void {
    const empleadoRawValue = this.convertEmpleadoToEmpleadoRawValue({ ...this.getFormDefaults(), ...empleado });
    form.reset(
      {
        ...empleadoRawValue,
        id: { value: empleadoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): EmpleadoFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecIngreso: currentTime,
      fecNac: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertEmpleadoRawValueToEmpleado(rawEmpleado: EmpleadoFormRawValue | NewEmpleadoFormRawValue): IEmpleado | NewEmpleado {
    return {
      ...rawEmpleado,
      fecIngreso: dayjs(rawEmpleado.fecIngreso, DATE_TIME_FORMAT),
      fecNac: dayjs(rawEmpleado.fecNac, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawEmpleado.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawEmpleado.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertEmpleadoToEmpleadoRawValue(
    empleado: IEmpleado | (Partial<NewEmpleado> & EmpleadoFormDefaults)
  ): EmpleadoFormRawValue | PartialWithRequiredKeyOf<NewEmpleadoFormRawValue> {
    return {
      ...empleado,
      fecIngreso: empleado.fecIngreso ? empleado.fecIngreso.format(DATE_TIME_FORMAT) : undefined,
      fecNac: empleado.fecNac ? empleado.fecNac.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: empleado.fecCrea ? empleado.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: empleado.fecModif ? empleado.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
