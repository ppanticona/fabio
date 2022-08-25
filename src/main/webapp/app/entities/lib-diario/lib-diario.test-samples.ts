import dayjs from 'dayjs/esm';

import { ILibDiario, NewLibDiario } from './lib-diario.model';

export const sampleWithRequiredData: ILibDiario = {
  id: 'e9bc86d6-b486-4308-9e65-067251f66d17',
  periodo: 'Accounts',
  cuo: 'Granite Bedfordshire Computer',
  codCtaContable: 56567,
  tipCompDocAsoc: 'Washington transition ubiquitous',
  nroCompDocAsoc: 'system-worthy',
  fecOpeEmi: dayjs('2022-08-25T00:04'),
  descOperac: 'Administrator',
  debe: 41999,
  haber: 34959,
  indEstOpe: 44110,
  fecCrea: dayjs('2022-08-24T20:48'),
  usuCrea: 'application Shoes Industrial',
  ipCrea: 'Producer Avon Lake',
};

export const sampleWithPartialData: ILibDiario = {
  id: 'bda53deb-d5ba-47f0-8ee8-b159fab14431',
  periodo: 'Configuration',
  cuo: 'Investment Dynamic Tools',
  asientContab: 'Metal Agent',
  codCtaContable: 44654,
  tipDocIdenEmi: 'online',
  nroDocIdenEmi: 'Music Licensed maximize',
  tipCompDocAsoc: 'hack',
  nroSerCompDocAsoc: 'seize extend',
  nroCompDocAsoc: '1080p schemas multi-state',
  fecOpeEmi: dayjs('2022-08-25T17:12'),
  descOperac: 'Refined',
  debe: 23411,
  haber: 51801,
  datoEstruct: 'harness',
  indEstOpe: 77983,
  fecCrea: dayjs('2022-08-24T21:29'),
  usuCrea: 'solution-oriented',
  ipCrea: 'auxiliary Synergized THX',
  usuModif: 'system-worthy',
  ipModif: 'Shoes interface',
};

export const sampleWithFullData: ILibDiario = {
  id: '4d176a3d-d783-493b-b3c7-41764ba91dd3',
  periodo: 'Lodge synthesizing generate',
  cuo: 'Maryland Idaho Mountain',
  asientContab: 'digital',
  codCtaContable: 17116,
  codUnidOper: 'Granite Director Savings',
  codCentroCui: 'teal interactive gold',
  tipMonedaOri: 'auxiliary navigate next-generation',
  tipDocIdenEmi: 'Frozen',
  nroDocIdenEmi: 'Industrial',
  tipCompDocAsoc: 'solution-oriented withdrawal knowledge',
  nroSerCompDocAsoc: 'Unbranded',
  nroCompDocAsoc: 'Strategist Dynamic Gloves',
  fecContable: dayjs('2022-08-25T13:12'),
  fecVenc: dayjs('2022-08-25T14:45'),
  fecOpeEmi: dayjs('2022-08-25T10:36'),
  descOperac: 'models District',
  glosaRef: 'PNG wireless orchid',
  debe: 13859,
  haber: 27499,
  datoEstruct: 'initiatives',
  indEstOpe: 86946,
  campoLibre: 'bypass',
  fecCrea: dayjs('2022-08-25T15:12'),
  usuCrea: 'Pizza Principal',
  ipCrea: 'logistical',
  fecModif: dayjs('2022-08-25T08:15'),
  usuModif: 'Program Agent Market',
  ipModif: 'Checking',
};

export const sampleWithNewData: NewLibDiario = {
  periodo: 'Directives Integration',
  cuo: 'orange mint',
  codCtaContable: 86359,
  tipCompDocAsoc: 'lavender Alabama Customer',
  nroCompDocAsoc: 'Regional Metal',
  fecOpeEmi: dayjs('2022-08-25T13:57'),
  descOperac: 'Shoes iterate withdrawal',
  debe: 83141,
  haber: 49588,
  indEstOpe: 3227,
  fecCrea: dayjs('2022-08-25T05:22'),
  usuCrea: 'JSON Internal HDD',
  ipCrea: 'Bike 24/365 e-enable',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
