import dayjs from 'dayjs/esm';

import { IHistoricoCaja, NewHistoricoCaja } from './historico-caja.model';

export const sampleWithRequiredData: IHistoricoCaja = {
  id: 'c6569f29-b52b-4a5d-9052-03f90aced74d',
  fecIniVig: dayjs('2022-08-24T23:17'),
  fecFinVig: dayjs('2022-08-24T20:11'),
  estado: 'transmitting Avon Chicken',
  montoInicialSoles: 36833,
  montoMaximoSoles: 60251,
  montoInicialDolares: 18277,
  montoMaximoDolares: 51358,
  version: 13920,
  indDel: true,
  fecCrea: dayjs('2022-08-25T09:29'),
  usuCrea: 'Frozen withdrawal',
  ipCrea: 'Austria vortals invoice',
};

export const sampleWithPartialData: IHistoricoCaja = {
  id: 'eb0b5bc4-439f-45c6-8f6d-3689be0bd374',
  fecIniVig: dayjs('2022-08-25T13:37'),
  fecFinVig: dayjs('2022-08-25T03:54'),
  estado: 'SMTP',
  montoInicialSoles: 27784,
  montoMaximoSoles: 26617,
  montoRealSoles: 1850,
  montoInicialDolares: 98621,
  montoMaximoDolares: 5603,
  version: 59249,
  indDel: false,
  fecCrea: dayjs('2022-08-25T17:18'),
  usuCrea: 'hack',
  ipCrea: 'out-of-the-box',
  fecModif: dayjs('2022-08-24T22:54'),
};

export const sampleWithFullData: IHistoricoCaja = {
  id: '04828889-7562-450e-83d2-fb524c7bfb0b',
  fecIniVig: dayjs('2022-08-25T12:30'),
  fecFinVig: dayjs('2022-08-24T23:24'),
  estado: 'Netherlands bleeding-edge',
  montoInicialSoles: 23659,
  montoMaximoSoles: 14345,
  montoRealSoles: 82973,
  montoInicialDolares: 10321,
  montoMaximoDolares: 23981,
  montoRealDolares: 9933,
  montoRealOtros: 16329,
  usuarioAsignado: 'hard',
  comentario: 'bypassing Chair',
  version: 44919,
  indDel: true,
  fecCrea: dayjs('2022-08-25T11:49'),
  usuCrea: 'bluetooth compelling',
  ipCrea: 'Refined generate withdrawal',
  fecModif: dayjs('2022-08-25T08:11'),
  usuModif: 'Cotton Caicos Villages',
  ipModif: 'Washington payment',
};

export const sampleWithNewData: NewHistoricoCaja = {
  fecIniVig: dayjs('2022-08-25T06:46'),
  fecFinVig: dayjs('2022-08-24T18:34'),
  estado: 'directional',
  montoInicialSoles: 33776,
  montoMaximoSoles: 45173,
  montoInicialDolares: 84320,
  montoMaximoDolares: 16733,
  version: 29653,
  indDel: false,
  fecCrea: dayjs('2022-08-25T08:59'),
  usuCrea: 'parsing systemic',
  ipCrea: 'payment',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
