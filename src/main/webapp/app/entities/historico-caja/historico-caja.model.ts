import dayjs from 'dayjs/esm';
import { ICaja } from 'app/entities/caja/caja.model';

export interface IHistoricoCaja {
  id: string;
  fecIniVig?: dayjs.Dayjs | null;
  fecFinVig?: dayjs.Dayjs | null;
  estado?: string | null;
  montoInicialSoles?: number | null;
  montoMaximoSoles?: number | null;
  montoRealSoles?: number | null;
  montoInicialDolares?: number | null;
  montoMaximoDolares?: number | null;
  montoRealDolares?: number | null;
  montoRealOtros?: number | null;
  usuarioAsignado?: string | null;
  comentario?: string | null;
  version?: number | null;
  indDel?: boolean | null;
  fecCrea?: dayjs.Dayjs | null;
  usuCrea?: string | null;
  ipCrea?: string | null;
  fecModif?: dayjs.Dayjs | null;
  usuModif?: string | null;
  ipModif?: string | null;
  caja?: Pick<ICaja, 'id'> | null;
}

export type NewHistoricoCaja = Omit<IHistoricoCaja, 'id'> & { id: null };
