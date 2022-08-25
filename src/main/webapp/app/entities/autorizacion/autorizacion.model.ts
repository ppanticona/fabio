import dayjs from 'dayjs/esm';

export interface IAutorizacion {
  id: string;
  tipAutorizacion?: string | null;
  token?: string | null;
  fecIniVig?: dayjs.Dayjs | null;
  fecFinVig?: dayjs.Dayjs | null;
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

export type NewAutorizacion = Omit<IAutorizacion, 'id'> & { id: null };
