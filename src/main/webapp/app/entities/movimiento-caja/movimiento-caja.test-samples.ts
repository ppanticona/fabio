import dayjs from 'dayjs/esm';

import { IMovimientoCaja, NewMovimientoCaja } from './movimiento-caja.model';

export const sampleWithRequiredData: IMovimientoCaja = {
  id: '3cce3652-b4e4-40f1-93bd-86285189551e',
  version: 72879,
  indDel: true,
  fecCrea: dayjs('2022-08-25T04:31'),
  usuCrea: 'Concrete Loan models',
  ipCrea: 'intermediate',
};

export const sampleWithPartialData: IMovimientoCaja = {
  id: '05a07a35-2010-4b99-8c98-79a3d2aa236a',
  tipMovimiento: 'Belarussian Cliff Norwegian',
  tipMoneda: 'Keyboard',
  version: 40040,
  indDel: true,
  fecCrea: dayjs('2022-08-25T04:54'),
  usuCrea: 'deposit Cotton',
  ipCrea: 'Steel Tasty Savings',
  fecModif: dayjs('2022-08-25T06:21'),
  usuModif: 'Research Dynamic',
};

export const sampleWithFullData: IMovimientoCaja = {
  id: 'ca5ed854-f6fb-40cf-8fe2-ec3d6e8b1f2b',
  tipMovimiento: 'Agent full-range Saint',
  monto: 79426,
  tipMoneda: 'Cotton',
  fecMovimiento: dayjs('2022-08-25T16:34'),
  version: 91959,
  indDel: false,
  fecCrea: dayjs('2022-08-25T03:43'),
  usuCrea: 'Rustic Planner',
  ipCrea: 'drive',
  fecModif: dayjs('2022-08-24T23:40'),
  usuModif: 'Devolved',
  ipModif: 'Assimilated',
};

export const sampleWithNewData: NewMovimientoCaja = {
  version: 15090,
  indDel: false,
  fecCrea: dayjs('2022-08-25T02:41'),
  usuCrea: 'Avon Table card',
  ipCrea: 'synthesizing Supervisor e-business',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
