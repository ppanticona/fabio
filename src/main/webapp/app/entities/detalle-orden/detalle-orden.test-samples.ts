import dayjs from 'dayjs/esm';

import { IDetalleOrden, NewDetalleOrden } from './detalle-orden.model';

export const sampleWithRequiredData: IDetalleOrden = {
  id: '11f5516c-c8e3-49ad-944b-dd8415bec834',
  cantidad: 67516,
  valUni: 78445,
  dcto: 39155,
  subtotal: 85602,
  estado: 'Universal',
  version: 1316,
  indDel: false,
  fecCrea: dayjs('2022-08-25T01:13'),
  usuCrea: 'Buckinghamshire silver cohesive',
  ipCrea: 'Agent Loan',
};

export const sampleWithPartialData: IDetalleOrden = {
  id: '266ef9db-f936-44c9-9a88-152bd1a31e84',
  cantidad: 7722,
  valUni: 60946,
  dcto: 56892,
  subtotal: 22757,
  estado: 'grow SQL',
  version: 68477,
  indDel: true,
  fecCrea: dayjs('2022-08-25T07:18'),
  usuCrea: 'Cocos Silver executive',
  ipCrea: 'SCSI Markets Creative',
  ipModif: 'payment dot-com',
};

export const sampleWithFullData: IDetalleOrden = {
  id: 'e8db7f4a-ab9b-4cf1-9e78-2ced82ad9b11',
  cantidad: 12793,
  valUni: 39122,
  dcto: 41810,
  subtotal: 7792,
  observacion: '../fake-data/blob/hipster.txt',
  estado: 'Principal productivity bypassing',
  version: 63908,
  indDel: true,
  fecCrea: dayjs('2022-08-25T08:12'),
  usuCrea: 'payment connect',
  ipCrea: 'Romania Central',
  fecModif: dayjs('2022-08-25T05:29'),
  usuModif: 'Customizable Practical',
  ipModif: 'contextually-based',
};

export const sampleWithNewData: NewDetalleOrden = {
  cantidad: 76619,
  valUni: 53235,
  dcto: 9196,
  subtotal: 9245,
  estado: 'Forint Cotton SDD',
  version: 74979,
  indDel: false,
  fecCrea: dayjs('2022-08-25T08:47'),
  usuCrea: 'Markets Dollar',
  ipCrea: 'Tasty Granite',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
