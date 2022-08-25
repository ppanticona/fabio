import dayjs from 'dayjs/esm';
import { ICliente } from 'app/entities/cliente/cliente.model';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';

export interface IOrden {
  id: string;
  numOrden?: number | null;
  fecEstiEnt?: dayjs.Dayjs | null;
  fecRecog?: dayjs.Dayjs | null;
  observacion?: string | null;
  tipOrden?: string | null;
  estado?: string | null;
  version?: number | null;
  indDel?: boolean | null;
  fecCrea?: dayjs.Dayjs | null;
  usuCrea?: string | null;
  ipCrea?: string | null;
  fecModif?: dayjs.Dayjs | null;
  usuModif?: string | null;
  ipModif?: string | null;
  cliente?: Pick<ICliente, 'id'> | null;
  autorizacion?: Pick<IAutorizacion, 'id'> | null;
}

export type NewOrden = Omit<IOrden, 'id'> & { id: null };
