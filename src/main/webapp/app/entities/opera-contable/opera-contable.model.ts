import dayjs from 'dayjs/esm';

export interface IOperaContable {
  id: string;
  descOpera?: string | null;
  areaRela?: string | null;
  codLib?: string | null;
  fecCrea?: dayjs.Dayjs | null;
  usuCrea?: string | null;
  ipCrea?: string | null;
  fecModif?: dayjs.Dayjs | null;
  usuModif?: string | null;
  ipModif?: string | null;
}

export type NewOperaContable = Omit<IOperaContable, 'id'> & { id: null };
