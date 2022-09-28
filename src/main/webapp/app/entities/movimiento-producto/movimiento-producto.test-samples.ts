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
  id: 'c3039cc1-d602-4b53-8ae9-545385358767',
  preVenta: 36430,
  precCompra: 70121,
  cnt: 65603,
  lote: 'deposit Keys Dynamic',
  fecMovimiento: dayjs('2022-08-25T11:32'),
  version: 76048,
  indDel: false,
  fecCrea: dayjs('2022-08-25T07:28'),
  usuCrea: 'dynamic Security',
  ipCrea: 'Research',
  fecModif: dayjs('2022-08-25T00:13'),
  ipModif: 'cross-platform Ethiopian protocol',
};

export const sampleWithFullData: IMovimientoProducto = {
  id: '6dc51931-74ea-4bfc-b2f7-1bb5318fdb55',
  tipMovimiento: 'RAM Jersey Fantastic',
  tip2Movimiento: 'Pizza',
  preVenta: 44489,
  precCompra: 89355,
  cnt: 66803,
  lote: 'disintermediate Sleek Account',
  fecMovimiento: dayjs('2022-08-24T19:05'),
  version: 53456,
  indDel: true,
  fecCrea: dayjs('2022-08-25T08:46'),
  usuCrea: 'Electronics',
  ipCrea: 'Generic',
  fecModif: dayjs('2022-08-25T05:28'),
  usuModif: 'Sleek',
  ipModif: 'Jewelery Fresh',
};

export const sampleWithNewData: NewMovimientoProducto = {
  version: 28085,
  indDel: true,
  fecCrea: dayjs('2022-08-24T18:56'),
  usuCrea: 'Hawaii infrastructures',
  ipCrea: 'Granite networks Licensed',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
