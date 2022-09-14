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
  id: 'bc3039cc-1d60-42b5-bcae-954538535876',
  precCompra: 48762,
  cnt: 36430,
  lote: 'payment Fantastic Dynamic',
  fecMovimiento: dayjs('2022-08-25T11:32'),
  version: 76048,
  indDel: false,
  fecCrea: dayjs('2022-08-25T07:28'),
  usuCrea: 'dynamic Security',
  ipCrea: 'Research',
  fecModif: dayjs('2022-08-25T00:13'),
  usuModif: 'cross-platform Ethiopian protocol',
};

export const sampleWithFullData: IMovimientoProducto = {
  id: '6dc51931-74ea-4bfc-b2f7-1bb5318fdb55',
  tipMovimiento: 'RAM Jersey Fantastic',
  tip2Movimiento: 'Pizza',
  precCompra: 44489,
  cnt: 89355,
  lote: 'Regional deposit generating',
  fecMovimiento: dayjs('2022-08-24T20:39'),
  version: 94728,
  indDel: true,
  fecCrea: dayjs('2022-08-25T01:05'),
  usuCrea: 'access backing',
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
