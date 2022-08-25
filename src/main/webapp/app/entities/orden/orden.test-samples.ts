import dayjs from 'dayjs/esm';

import { IOrden, NewOrden } from './orden.model';

export const sampleWithRequiredData: IOrden = {
  id: '5eeb0487-dac9-471d-a3f1-b04125ab76a5',
  numOrden: 90699,
  tipOrden: 'Washington',
  estado: 'Business-focused Polynesia Mexico',
  version: 52515,
  indDel: false,
  fecCrea: dayjs('2022-08-25T11:26'),
  usuCrea: 'Uruguay generating Principal',
  ipCrea: 'digital',
};

export const sampleWithPartialData: IOrden = {
  id: '802d73cc-b5cd-481f-962f-e61c82885c93',
  numOrden: 51599,
  fecEstiEnt: dayjs('2022-08-25T13:15'),
  fecRecog: dayjs('2022-08-24T17:58'),
  tipOrden: 'evolve Producer',
  estado: 'withdrawal Personal',
  version: 4890,
  indDel: false,
  fecCrea: dayjs('2022-08-25T03:45'),
  usuCrea: 'Soft',
  ipCrea: 'JSON',
  usuModif: 'Granite Metal Licensed',
};

export const sampleWithFullData: IOrden = {
  id: '13d8e038-0952-4843-ad6a-007de4621f0f',
  numOrden: 14261,
  fecEstiEnt: dayjs('2022-08-24T21:37'),
  fecRecog: dayjs('2022-08-25T07:45'),
  observacion: '../fake-data/blob/hipster.txt',
  tipOrden: 'COM Garden hard',
  estado: 'unleash',
  version: 32652,
  indDel: true,
  fecCrea: dayjs('2022-08-25T01:19'),
  usuCrea: 'Engineer',
  ipCrea: 'Plains',
  fecModif: dayjs('2022-08-25T11:50'),
  usuModif: 'Latvia Buckinghamshire',
  ipModif: 'purple',
};

export const sampleWithNewData: NewOrden = {
  numOrden: 43593,
  tipOrden: 'Peso',
  estado: 'Consultant Persevering Gorgeous',
  version: 15341,
  indDel: false,
  fecCrea: dayjs('2022-08-24T20:26'),
  usuCrea: 'Unbranded 24/365',
  ipCrea: 'compress',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
