import dayjs from 'dayjs/esm';

import { IPromocion, NewPromocion } from './promocion.model';

export const sampleWithRequiredData: IPromocion = {
  id: '216c3569-75e6-4bec-891a-f0aaa2bade0e',
  tipPromocion: 'synthesize monitor',
  fecIniVig: dayjs('2022-08-24T18:16'),
  fecFinVig: dayjs('2022-08-25T03:38'),
  comentarios: '../fake-data/blob/hipster.txt',
  estado: 'teal',
  version: 20700,
  indDel: false,
  fecCrea: dayjs('2022-08-24T22:00'),
  usuCrea: 'primary digital',
  ipCrea: 'Checking',
};

export const sampleWithPartialData: IPromocion = {
  id: 'a4af4780-c12c-42fb-aca7-63df071cbdda',
  tipPromocion: 'access Bedfordshire',
  val2: 'Account Direct Open-architected',
  val3: 'Beauty Solutions',
  maxProm: 'Gloves Monetary',
  fecIniVig: dayjs('2022-08-25T11:57'),
  fecFinVig: dayjs('2022-08-25T10:49'),
  comentarios: '../fake-data/blob/hipster.txt',
  estado: 'Berkshire Plastic dynamic',
  version: 32462,
  indDel: false,
  fecCrea: dayjs('2022-08-25T12:01'),
  usuCrea: 'JBOD Account',
  ipCrea: 'indexing Niue',
  ipModif: 'Small Optimization',
};

export const sampleWithFullData: IPromocion = {
  id: '08dc57fe-7e41-412d-938b-832a5e68559f',
  tipPromocion: 'Plastic',
  val1: 'orange',
  val2: 'auxiliary blue Kentucky',
  val3: 'Minnesota Cambridgeshire',
  maxProm: 'Kwacha Rubber',
  fecIniVig: dayjs('2022-08-25T08:45'),
  fecFinVig: dayjs('2022-08-24T22:53'),
  comentarios: '../fake-data/blob/hipster.txt',
  estado: 'Security approach mesh',
  version: 86551,
  indDel: false,
  fecCrea: dayjs('2022-08-24T21:47'),
  usuCrea: 'calculate Ridges',
  ipCrea: 'Mouse synthesize Bacon',
  fecModif: dayjs('2022-08-25T02:02'),
  usuModif: 'Solutions',
  ipModif: 'Assistant logistical',
};

export const sampleWithNewData: NewPromocion = {
  tipPromocion: 'compressing deposit Intuitive',
  fecIniVig: dayjs('2022-08-24T23:09'),
  fecFinVig: dayjs('2022-08-24T18:16'),
  comentarios: '../fake-data/blob/hipster.txt',
  estado: 'Markets Towels',
  version: 54662,
  indDel: false,
  fecCrea: dayjs('2022-08-25T15:31'),
  usuCrea: 'vertical International',
  ipCrea: 'transmitting 5th yellow',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
