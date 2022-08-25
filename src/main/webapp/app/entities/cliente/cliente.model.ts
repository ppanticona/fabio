import dayjs from 'dayjs/esm';

export interface ICliente {
  id: string;
  tipDocCli?: string | null;
  nroDocCli?: string | null;
  nombresCli?: string | null;
  apellidosCli?: string | null;
  tel1?: string | null;
  tel2?: string | null;
  correo?: string | null;
  direccion?: string | null;
  refDireccion?: string | null;
  distrito?: string | null;
  fecNac?: dayjs.Dayjs | null;
  estado?: string | null;
  version?: number | null;
  indDel?: boolean | null;
  fecCrea?: dayjs.Dayjs | null;
  usuCrea?: string | null;
  ipCrea?: string | null;
  fecModif?: dayjs.Dayjs | null;
  usuModif?: string | null;
  ipModif?: string | null;
}

export type NewCliente = Omit<ICliente, 'id'> & { id: null };
