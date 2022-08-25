import dayjs from 'dayjs/esm';

import { IDetalleOperaContable, NewDetalleOperaContable } from './detalle-opera-contable.model';

export const sampleWithRequiredData: IDetalleOperaContable = {
  id: '2d95a3d9-1efa-4486-a933-2093ee7f7b33',
  fecCrea: dayjs('2022-08-25T14:57'),
  usuCrea: 'challenge clicks-and-mortar',
  ipCrea: 'blue Account bypass',
};

export const sampleWithPartialData: IDetalleOperaContable = {
  id: '814dd60a-61fc-4083-ad19-0bd08dc91a45',
  valorOperacion: 87632,
  fecCrea: dayjs('2022-08-25T11:07'),
  usuCrea: 'back-end Gardens orchid',
  ipCrea: 'connecting Rubber Bolivar',
  fecModif: dayjs('2022-08-25T00:26'),
};

export const sampleWithFullData: IDetalleOperaContable = {
  id: '0a1f9a77-6e30-4702-9508-4ef6f9bf4487',
  operador: 'Fundamental up primary',
  valorOperacion: 99329,
  descripcion: 'Assistant',
  tipMovimiento: 'SQL',
  fecCrea: dayjs('2022-08-24T20:47'),
  usuCrea: 'Ball Enterprise-wide navigate',
  ipCrea: 'National primary parsing',
  fecModif: dayjs('2022-08-25T03:01'),
  usuModif: 'RAM magenta',
  ipModif: 'Uzbekistan Plaza',
};

export const sampleWithNewData: NewDetalleOperaContable = {
  fecCrea: dayjs('2022-08-25T14:29'),
  usuCrea: 'Liaison calculating redundant',
  ipCrea: 'Intelligent Darussalam blockchains',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
