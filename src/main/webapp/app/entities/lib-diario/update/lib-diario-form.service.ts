import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ILibDiario, NewLibDiario } from '../lib-diario.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ILibDiario for edit and NewLibDiarioFormGroupInput for create.
 */
type LibDiarioFormGroupInput = ILibDiario | PartialWithRequiredKeyOf<NewLibDiario>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ILibDiario | NewLibDiario> = Omit<T, 'fecContable' | 'fecVenc' | 'fecOpeEmi' | 'fecCrea' | 'fecModif'> & {
  fecContable?: string | null;
  fecVenc?: string | null;
  fecOpeEmi?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type LibDiarioFormRawValue = FormValueOf<ILibDiario>;

type NewLibDiarioFormRawValue = FormValueOf<NewLibDiario>;

type LibDiarioFormDefaults = Pick<NewLibDiario, 'id' | 'fecContable' | 'fecVenc' | 'fecOpeEmi' | 'fecCrea' | 'fecModif'>;

type LibDiarioFormGroupContent = {
  id: FormControl<LibDiarioFormRawValue['id'] | NewLibDiario['id']>;
  periodo: FormControl<LibDiarioFormRawValue['periodo']>;
  cuo: FormControl<LibDiarioFormRawValue['cuo']>;
  asientContab: FormControl<LibDiarioFormRawValue['asientContab']>;
  codCtaContable: FormControl<LibDiarioFormRawValue['codCtaContable']>;
  codUnidOper: FormControl<LibDiarioFormRawValue['codUnidOper']>;
  codCentroCui: FormControl<LibDiarioFormRawValue['codCentroCui']>;
  tipMonedaOri: FormControl<LibDiarioFormRawValue['tipMonedaOri']>;
  tipDocIdenEmi: FormControl<LibDiarioFormRawValue['tipDocIdenEmi']>;
  nroDocIdenEmi: FormControl<LibDiarioFormRawValue['nroDocIdenEmi']>;
  tipCompDocAsoc: FormControl<LibDiarioFormRawValue['tipCompDocAsoc']>;
  nroSerCompDocAsoc: FormControl<LibDiarioFormRawValue['nroSerCompDocAsoc']>;
  nroCompDocAsoc: FormControl<LibDiarioFormRawValue['nroCompDocAsoc']>;
  fecContable: FormControl<LibDiarioFormRawValue['fecContable']>;
  fecVenc: FormControl<LibDiarioFormRawValue['fecVenc']>;
  fecOpeEmi: FormControl<LibDiarioFormRawValue['fecOpeEmi']>;
  descOperac: FormControl<LibDiarioFormRawValue['descOperac']>;
  glosaRef: FormControl<LibDiarioFormRawValue['glosaRef']>;
  debe: FormControl<LibDiarioFormRawValue['debe']>;
  haber: FormControl<LibDiarioFormRawValue['haber']>;
  datoEstruct: FormControl<LibDiarioFormRawValue['datoEstruct']>;
  indEstOpe: FormControl<LibDiarioFormRawValue['indEstOpe']>;
  campoLibre: FormControl<LibDiarioFormRawValue['campoLibre']>;
  fecCrea: FormControl<LibDiarioFormRawValue['fecCrea']>;
  usuCrea: FormControl<LibDiarioFormRawValue['usuCrea']>;
  ipCrea: FormControl<LibDiarioFormRawValue['ipCrea']>;
  fecModif: FormControl<LibDiarioFormRawValue['fecModif']>;
  usuModif: FormControl<LibDiarioFormRawValue['usuModif']>;
  ipModif: FormControl<LibDiarioFormRawValue['ipModif']>;
};

export type LibDiarioFormGroup = FormGroup<LibDiarioFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class LibDiarioFormService {
  createLibDiarioFormGroup(libDiario: LibDiarioFormGroupInput = { id: null }): LibDiarioFormGroup {
    const libDiarioRawValue = this.convertLibDiarioToLibDiarioRawValue({
      ...this.getFormDefaults(),
      ...libDiario,
    });
    return new FormGroup<LibDiarioFormGroupContent>({
      id: new FormControl(
        { value: libDiarioRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      periodo: new FormControl(libDiarioRawValue.periodo, {
        validators: [Validators.required],
      }),
      cuo: new FormControl(libDiarioRawValue.cuo, {
        validators: [Validators.required],
      }),
      asientContab: new FormControl(libDiarioRawValue.asientContab),
      codCtaContable: new FormControl(libDiarioRawValue.codCtaContable, {
        validators: [Validators.required],
      }),
      codUnidOper: new FormControl(libDiarioRawValue.codUnidOper),
      codCentroCui: new FormControl(libDiarioRawValue.codCentroCui),
      tipMonedaOri: new FormControl(libDiarioRawValue.tipMonedaOri),
      tipDocIdenEmi: new FormControl(libDiarioRawValue.tipDocIdenEmi),
      nroDocIdenEmi: new FormControl(libDiarioRawValue.nroDocIdenEmi),
      tipCompDocAsoc: new FormControl(libDiarioRawValue.tipCompDocAsoc, {
        validators: [Validators.required],
      }),
      nroSerCompDocAsoc: new FormControl(libDiarioRawValue.nroSerCompDocAsoc),
      nroCompDocAsoc: new FormControl(libDiarioRawValue.nroCompDocAsoc, {
        validators: [Validators.required],
      }),
      fecContable: new FormControl(libDiarioRawValue.fecContable),
      fecVenc: new FormControl(libDiarioRawValue.fecVenc),
      fecOpeEmi: new FormControl(libDiarioRawValue.fecOpeEmi, {
        validators: [Validators.required],
      }),
      descOperac: new FormControl(libDiarioRawValue.descOperac, {
        validators: [Validators.required],
      }),
      glosaRef: new FormControl(libDiarioRawValue.glosaRef),
      debe: new FormControl(libDiarioRawValue.debe, {
        validators: [Validators.required],
      }),
      haber: new FormControl(libDiarioRawValue.haber, {
        validators: [Validators.required],
      }),
      datoEstruct: new FormControl(libDiarioRawValue.datoEstruct),
      indEstOpe: new FormControl(libDiarioRawValue.indEstOpe, {
        validators: [Validators.required],
      }),
      campoLibre: new FormControl(libDiarioRawValue.campoLibre),
      fecCrea: new FormControl(libDiarioRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(libDiarioRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(libDiarioRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(libDiarioRawValue.fecModif),
      usuModif: new FormControl(libDiarioRawValue.usuModif),
      ipModif: new FormControl(libDiarioRawValue.ipModif),
    });
  }

  getLibDiario(form: LibDiarioFormGroup): ILibDiario | NewLibDiario {
    return this.convertLibDiarioRawValueToLibDiario(form.getRawValue() as LibDiarioFormRawValue | NewLibDiarioFormRawValue);
  }

  resetForm(form: LibDiarioFormGroup, libDiario: LibDiarioFormGroupInput): void {
    const libDiarioRawValue = this.convertLibDiarioToLibDiarioRawValue({ ...this.getFormDefaults(), ...libDiario });
    form.reset(
      {
        ...libDiarioRawValue,
        id: { value: libDiarioRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): LibDiarioFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecContable: currentTime,
      fecVenc: currentTime,
      fecOpeEmi: currentTime,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertLibDiarioRawValueToLibDiario(rawLibDiario: LibDiarioFormRawValue | NewLibDiarioFormRawValue): ILibDiario | NewLibDiario {
    return {
      ...rawLibDiario,
      fecContable: dayjs(rawLibDiario.fecContable, DATE_TIME_FORMAT),
      fecVenc: dayjs(rawLibDiario.fecVenc, DATE_TIME_FORMAT),
      fecOpeEmi: dayjs(rawLibDiario.fecOpeEmi, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawLibDiario.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawLibDiario.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertLibDiarioToLibDiarioRawValue(
    libDiario: ILibDiario | (Partial<NewLibDiario> & LibDiarioFormDefaults)
  ): LibDiarioFormRawValue | PartialWithRequiredKeyOf<NewLibDiarioFormRawValue> {
    return {
      ...libDiario,
      fecContable: libDiario.fecContable ? libDiario.fecContable.format(DATE_TIME_FORMAT) : undefined,
      fecVenc: libDiario.fecVenc ? libDiario.fecVenc.format(DATE_TIME_FORMAT) : undefined,
      fecOpeEmi: libDiario.fecOpeEmi ? libDiario.fecOpeEmi.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: libDiario.fecCrea ? libDiario.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: libDiario.fecModif ? libDiario.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
