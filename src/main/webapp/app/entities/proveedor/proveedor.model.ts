import dayjs from 'dayjs/esm';

export interface IProveedor {
  id: string;
  tipDocProv?: string | null;
  nroDocProv?: string | null;
  nombresRazonSocProv?: string | null;
  tel1?: string | null;
  tel2?: string | null;
  correo?: string | null;
  direccion?: string | null;
  refDireccion?: string | null;
  distrito?: string | null;
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

export type NewProveedor = Omit<IProveedor, 'id'> & { id: null };
