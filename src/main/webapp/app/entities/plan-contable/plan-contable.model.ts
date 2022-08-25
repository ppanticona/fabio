import dayjs from 'dayjs/esm';

export interface IPlanContable {
  id: string;
  tipPlan?: string | null;
  nivPlan?: number | null;
  codPlan?: string | null;
  descCuenta?: string | null;
  anhoPlan?: string | null;
  resoPlan?: string | null;
  fecCrea?: dayjs.Dayjs | null;
  usuCrea?: string | null;
  ipCrea?: string | null;
  fecModif?: dayjs.Dayjs | null;
  usuModif?: string | null;
  ipModif?: string | null;
}

export type NewPlanContable = Omit<IPlanContable, 'id'> & { id: null };
