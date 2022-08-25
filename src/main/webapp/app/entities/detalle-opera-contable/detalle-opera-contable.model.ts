import dayjs from 'dayjs/esm';
import { IPlanContable } from 'app/entities/plan-contable/plan-contable.model';
import { IOperaContable } from 'app/entities/opera-contable/opera-contable.model';

export interface IDetalleOperaContable {
  id: string;
  operador?: string | null;
  valorOperacion?: number | null;
  descripcion?: string | null;
  tipMovimiento?: string | null;
  fecCrea?: dayjs.Dayjs | null;
  usuCrea?: string | null;
  ipCrea?: string | null;
  fecModif?: dayjs.Dayjs | null;
  usuModif?: string | null;
  ipModif?: string | null;
  planContable?: Pick<IPlanContable, 'id'> | null;
  operaContable?: Pick<IOperaContable, 'id'> | null;
}

export type NewDetalleOperaContable = Omit<IDetalleOperaContable, 'id'> & { id: null };
