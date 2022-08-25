import dayjs from 'dayjs/esm';

import { IMovimientoProducto, NewMovimientoProducto } from './movimiento-producto.model';

export const sampleWithRequiredData: IMovimientoProducto = {
  id: 'e958440d-b639-4c93-88db-8d40a0e5ef6d',
  version: 49076,
  indDel: true,
  fecCrea: dayjs('2022-08-25T04:19'),
  usuCrea: 'Metal Overpass quantify',
  ipCrea: 'Savings',
};

export const sampleWithPartialData: IMovimientoProducto = {
  id: '3bc3039c-c1d6-402b-93ca-e95453853587',
  cnt: 42457,
  lote: 'cross-media alarm',
  fecMovimiento: dayjs('2022-08-25T08:32'),
  version: 47496,
  indDel: false,
  fecCrea: dayjs('2022-08-25T15:58'),
  usuCrea: 'blue hack Guam',
  ipCrea: 'Security User-centric input',
  fecModif: dayjs('2022-08-25T09:29'),
  usuModif: 'Credit Shoes index',
  ipModif: 'scalable Centralized uniform',
};

export const sampleWithFullData: IMovimientoProducto = {
  id: 'bfc72f71-bb53-418f-9b55-fd2e99e054a3',
  tipMovimiento: 'Pizza',
  tip2Movimiento: 'Dynamic disintermediate',
  cnt: 8018,
  lote: 'generating XSS',
  fecMovimiento: dayjs('2022-08-25T01:05'),
  version: 37698,
  indDel: false,
  fecCrea: dayjs('2022-08-25T17:23'),
  usuCrea: 'backing',
  ipCrea: 'didactic Avon',
  fecModif: dayjs('2022-08-25T06:20'),
  usuModif: 'Chair platforms port',
  ipModif: 'Borders Practical user-centric',
};

export const sampleWithNewData: NewMovimientoProducto = {
  version: 53149,
  indDel: true,
  fecCrea: dayjs('2022-08-25T07:11'),
  usuCrea: 'Bermuda XML Direct',
  ipCrea: 'Rubber e-enable',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
