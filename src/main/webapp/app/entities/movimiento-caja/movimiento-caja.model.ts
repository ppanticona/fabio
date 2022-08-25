import dayjs from 'dayjs/esm';
import { IHistoricoCaja } from 'app/entities/historico-caja/historico-caja.model';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';

export interface IMovimientoCaja {
  id: string;
  tipMovimiento?: string | null;
  monto?: number | null;
  tipMoneda?: string | null;
  fecMovimiento?: dayjs.Dayjs | null;
  version?: number | null;
  indDel?: boolean | null;
  fecCrea?: dayjs.Dayjs | null;
  usuCrea?: string | null;
  ipCrea?: string | null;
  fecModif?: dayjs.Dayjs | null;
  usuModif?: string | null;
  ipModif?: string | null;
  historicoCaja?: Pick<IHistoricoCaja, 'id'> | null;
  autorizacion?: Pick<IAutorizacion, 'id'> | null;
}

export type NewMovimientoCaja = Omit<IMovimientoCaja, 'id'> & { id: null };
