import dayjs from 'dayjs/esm';

export interface ILibDiario {
  id: string;
  periodo?: string | null;
  cuo?: string | null;
  asientContab?: string | null;
  codCtaContable?: number | null;
  codUnidOper?: string | null;
  codCentroCui?: string | null;
  tipMonedaOri?: string | null;
  tipDocIdenEmi?: string | null;
  nroDocIdenEmi?: string | null;
  tipCompDocAsoc?: string | null;
  nroSerCompDocAsoc?: string | null;
  nroCompDocAsoc?: string | null;
  fecContable?: dayjs.Dayjs | null;
  fecVenc?: dayjs.Dayjs | null;
  fecOpeEmi?: dayjs.Dayjs | null;
  descOperac?: string | null;
  glosaRef?: string | null;
  debe?: number | null;
  haber?: number | null;
  datoEstruct?: string | null;
  indEstOpe?: number | null;
  campoLibre?: string | null;
  fecCrea?: dayjs.Dayjs | null;
  usuCrea?: string | null;
  ipCrea?: string | null;
  fecModif?: dayjs.Dayjs | null;
  usuModif?: string | null;
  ipModif?: string | null;
}

export type NewLibDiario = Omit<ILibDiario, 'id'> & { id: null };
