import dayjs from 'dayjs/esm';

import { IRegCompras, NewRegCompras } from './reg-compras.model';

export const sampleWithRequiredData: IRegCompras = {
  id: '50a2b668-facb-476c-b651-249b10fb87e2',
  periodo: 'override',
  cuo: 'system invoice circuit',
  fecEmiComp: dayjs('2022-08-25T00:11'),
  tipComp: 'exuding',
  nroSerieComp: 'Rial',
  nroComp: 'AGP De-engineered invoice',
  tipDocProv: 'Practical',
  nroDocProv: 'Response',
  nomApeRazSocProv: 'Reduced Officer index',
  baseImponible: 21137,
  montoIgv: 55092,
  baseImponible2: 96057,
  montoIgv2: 56810,
  montoIgv3: 3610,
  indDel: false,
  fecCrea: dayjs('2022-08-24T19:02'),
  usuCrea: 'digital online Zambian',
  ipCrea: 'Handmade Designer',
};

export const sampleWithPartialData: IRegCompras = {
  id: '1746221f-ad31-4598-a04d-89471b939497',
  periodo: 'Investment Unbranded',
  cuo: 'ADP',
  fecEmiComp: dayjs('2022-08-25T11:35'),
  tipComp: 'Futuna',
  nroSerieComp: 'Cape Chips Berkshire',
  anhoEmisionDua: 'lime forecast array',
  nroComp: 'networks bandwidth',
  tipDocProv: 'overriding',
  nroDocProv: 'Mauritius quantify',
  nomApeRazSocProv: 'clear-thinking Planner',
  baseImponible: 81480,
  montoIgv: 2242,
  baseImponible2: 29556,
  montoIgv2: 78111,
  montoIgv3: 67852,
  montoIsc: 71093,
  importeTotal: 13498,
  codMoneda: 'Jersey connecting',
  tipCompModif: 'Liberia',
  nroSerieCompModif: 'Ghana Vermont quantify',
  nroCompModif: 'Hampshire Tools',
  fecEmiDetracc: dayjs('2022-08-25T16:43'),
  identContrato: 'infrastructures',
  errTipDos: 'Consultant standardization',
  indCompPagoMedPago: 'SMS Fall Lebanon',
  estado: 68915,
  indDel: true,
  fecCrea: dayjs('2022-08-25T10:38'),
  usuCrea: 'Pizza',
  ipCrea: 'Program',
};

export const sampleWithFullData: IRegCompras = {
  id: '01f7dac4-093b-428c-8be0-080014994408',
  periodo: 'navigate Congo',
  cuo: 'Won',
  asientContab: 'Forward Designer Practical',
  fecEmiComp: dayjs('2022-08-25T05:54'),
  fecVencComp: dayjs('2022-08-24T18:57'),
  tipComp: 'firewall Internal',
  nroSerieComp: 'Bike',
  anhoEmisionDua: 'full-range Cambridgeshire',
  nroComp: 'Practical harness',
  nroFinal: 'AGP networks deliver',
  tipDocProv: 'Trafficway Manager invoice',
  nroDocProv: 'RAM',
  nomApeRazSocProv: 'core',
  baseImponible: 82297,
  montoIgv: 33124,
  baseImponible2: 87264,
  montoIgv2: 18144,
  baseImponible3: 43437,
  montoIgv3: 55076,
  montoNoGravado: 80499,
  montoIsc: 2345,
  impConsBols: 64156,
  otrosCargos: 60572,
  importeTotal: 55568,
  codMoneda: 'Optimized',
  tipCambio: 55225,
  fecEmiModif: dayjs('2022-08-25T11:55'),
  tipCompModif: 'parse scale',
  nroSerieCompModif: 'Operations',
  codDuaRef: 'multi-byte Solutions Georgia',
  nroCompModif: 'services granular functionalities',
  fecEmiDetracc: dayjs('2022-08-25T08:31'),
  nroConstDetracc: 'Summit calculating Towels',
  indCompSujRetenc: 'monitoring reboot',
  clasBienesyServicios: 'Spring Intelligent AGP',
  identContrato: 'Electronics visionary',
  errTipUno: 'Israel',
  errTipDos: 'Senior panel',
  errTipTres: 'Intelligent',
  errTipCuatro: 'extensible',
  indCompPagoMedPago: 'channels deposit Walks',
  estado: 41244,
  campoLibre: 'Profound Towels',
  indDel: false,
  fecCrea: dayjs('2022-08-25T16:44'),
  usuCrea: 'Ball capacity',
  ipCrea: 'SAS contingency New',
  fecModif: dayjs('2022-08-24T21:05'),
  usuModif: 'Lebanese',
  ipModif: 'pink invoice Intelligent',
};

export const sampleWithNewData: NewRegCompras = {
  periodo: 'Account',
  cuo: 'Money invoice',
  fecEmiComp: dayjs('2022-08-25T00:42'),
  tipComp: 'generation Auto Eritrea',
  nroSerieComp: 'protocol morph',
  nroComp: 'Coordinator mindshare',
  tipDocProv: 'Human',
  nroDocProv: 'payment',
  nomApeRazSocProv: 'transmit',
  baseImponible: 66656,
  montoIgv: 97987,
  baseImponible2: 66596,
  montoIgv2: 61376,
  montoIgv3: 90676,
  indDel: false,
  fecCrea: dayjs('2022-08-24T23:50'),
  usuCrea: 'calculating',
  ipCrea: 'Inlet Center Devolved',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);