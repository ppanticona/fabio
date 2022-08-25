import dayjs from 'dayjs/esm';

export interface IPromocion {
  id: string;
  tipPromocion?: string | null;
  val1?: string | null;
  val2?: string | null;
  val3?: string | null;
  maxProm?: string | null;
  fecIniVig?: dayjs.Dayjs | null;
  fecFinVig?: dayjs.Dayjs | null;
  comentarios?: string | null;
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

export type NewPromocion = Omit<IPromocion, 'id'> & { id: null };
