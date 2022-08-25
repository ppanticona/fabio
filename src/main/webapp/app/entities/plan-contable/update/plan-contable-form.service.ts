import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IPlanContable, NewPlanContable } from '../plan-contable.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPlanContable for edit and NewPlanContableFormGroupInput for create.
 */
type PlanContableFormGroupInput = IPlanContable | PartialWithRequiredKeyOf<NewPlanContable>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IPlanContable | NewPlanContable> = Omit<T, 'fecCrea' | 'fecModif'> & {
  fecCrea?: string | null;
  fecModif?: string | null;
};

type PlanContableFormRawValue = FormValueOf<IPlanContable>;

type NewPlanContableFormRawValue = FormValueOf<NewPlanContable>;

type PlanContableFormDefaults = Pick<NewPlanContable, 'id' | 'fecCrea' | 'fecModif'>;

type PlanContableFormGroupContent = {
  id: FormControl<PlanContableFormRawValue['id'] | NewPlanContable['id']>;
  tipPlan: FormControl<PlanContableFormRawValue['tipPlan']>;
  nivPlan: FormControl<PlanContableFormRawValue['nivPlan']>;
  codPlan: FormControl<PlanContableFormRawValue['codPlan']>;
  descCuenta: FormControl<PlanContableFormRawValue['descCuenta']>;
  anhoPlan: FormControl<PlanContableFormRawValue['anhoPlan']>;
  resoPlan: FormControl<PlanContableFormRawValue['resoPlan']>;
  fecCrea: FormControl<PlanContableFormRawValue['fecCrea']>;
  usuCrea: FormControl<PlanContableFormRawValue['usuCrea']>;
  ipCrea: FormControl<PlanContableFormRawValue['ipCrea']>;
  fecModif: FormControl<PlanContableFormRawValue['fecModif']>;
  usuModif: FormControl<PlanContableFormRawValue['usuModif']>;
  ipModif: FormControl<PlanContableFormRawValue['ipModif']>;
};

export type PlanContableFormGroup = FormGroup<PlanContableFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PlanContableFormService {
  createPlanContableFormGroup(planContable: PlanContableFormGroupInput = { id: null }): PlanContableFormGroup {
    const planContableRawValue = this.convertPlanContableToPlanContableRawValue({
      ...this.getFormDefaults(),
      ...planContable,
    });
    return new FormGroup<PlanContableFormGroupContent>({
      id: new FormControl(
        { value: planContableRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      tipPlan: new FormControl(planContableRawValue.tipPlan),
      nivPlan: new FormControl(planContableRawValue.nivPlan, {
        validators: [Validators.required],
      }),
      codPlan: new FormControl(planContableRawValue.codPlan, {
        validators: [Validators.required],
      }),
      descCuenta: new FormControl(planContableRawValue.descCuenta, {
        validators: [Validators.required],
      }),
      anhoPlan: new FormControl(planContableRawValue.anhoPlan),
      resoPlan: new FormControl(planContableRawValue.resoPlan),
      fecCrea: new FormControl(planContableRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(planContableRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(planContableRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(planContableRawValue.fecModif),
      usuModif: new FormControl(planContableRawValue.usuModif),
      ipModif: new FormControl(planContableRawValue.ipModif),
    });
  }

  getPlanContable(form: PlanContableFormGroup): IPlanContable | NewPlanContable {
    return this.convertPlanContableRawValueToPlanContable(form.getRawValue() as PlanContableFormRawValue | NewPlanContableFormRawValue);
  }

  resetForm(form: PlanContableFormGroup, planContable: PlanContableFormGroupInput): void {
    const planContableRawValue = this.convertPlanContableToPlanContableRawValue({ ...this.getFormDefaults(), ...planContable });
    form.reset(
      {
        ...planContableRawValue,
        id: { value: planContableRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): PlanContableFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertPlanContableRawValueToPlanContable(
    rawPlanContable: PlanContableFormRawValue | NewPlanContableFormRawValue
  ): IPlanContable | NewPlanContable {
    return {
      ...rawPlanContable,
      fecCrea: dayjs(rawPlanContable.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawPlanContable.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertPlanContableToPlanContableRawValue(
    planContable: IPlanContable | (Partial<NewPlanContable> & PlanContableFormDefaults)
  ): PlanContableFormRawValue | PartialWithRequiredKeyOf<NewPlanContableFormRawValue> {
    return {
      ...planContable,
      fecCrea: planContable.fecCrea ? planContable.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: planContable.fecModif ? planContable.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
