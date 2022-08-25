import dayjs from 'dayjs/esm';

import { ICaja, NewCaja } from './caja.model';

export const sampleWithRequiredData: ICaja = {
  id: 'c9391671-95b5-4570-b506-47d97850f450',
  tipCaja: 'parsing Fresh District',
  descripcion: 'Persevering virtual indexing',
  estado: 'Sudanese',
  version: 19917,
  indDel: true,
  fecCrea: dayjs('2022-08-25T06:09'),
  usuCrea: 'infrastructures brand Coordinator',
  ipCrea: 'program XML Internal',
};

export const sampleWithPartialData: ICaja = {
  id: '1259dce7-62ff-41ff-b4c0-5ae530e3d37d',
  tipCaja: 'CSS',
  descripcion: 'target Arizona Money',
  estado: 'Sausages software',
  version: 94874,
  indDel: false,
  fecCrea: dayjs('2022-08-25T17:16'),
  usuCrea: 'Unbranded Highway',
  ipCrea: 'state',
  fecModif: dayjs('2022-08-25T02:30'),
  usuModif: 'implement',
};

export const sampleWithFullData: ICaja = {
  id: '7ea431dd-ab89-4924-9895-9bce6b613841',
  tipCaja: 'Pre-emptive quantify pink',
  descripcion: 'Arizona global',
  estado: 'users Human',
  version: 2037,
  indDel: true,
  fecCrea: dayjs('2022-08-24T23:20'),
  usuCrea: 'Designer programming',
  ipCrea: 'Usability uniform',
  fecModif: dayjs('2022-08-24T23:49'),
  usuModif: 'system Gloves',
  ipModif: 'Synergistic Fresh',
};

export const sampleWithNewData: NewCaja = {
  tipCaja: 'TCP Borders',
  descripcion: 'Investor Producer',
  estado: 'monetize Granite',
  version: 31301,
  indDel: true,
  fecCrea: dayjs('2022-08-25T01:18'),
  usuCrea: "Buckinghamshire d'Ivoire open-source",
  ipCrea: 'Dynamic Money monetize',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
