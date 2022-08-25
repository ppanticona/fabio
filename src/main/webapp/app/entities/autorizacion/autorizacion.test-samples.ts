import dayjs from 'dayjs/esm';

import { IAutorizacion, NewAutorizacion } from './autorizacion.model';

export const sampleWithRequiredData: IAutorizacion = {
  id: '2d03d54c-a734-411a-a447-56d68dc11956',
  tipAutorizacion: 'indexing Shirt contextually-based',
  token: 'responsive Gloves',
  fecIniVig: dayjs('2022-08-24T21:00'),
  fecFinVig: dayjs('2022-08-24T20:23'),
  estado: 'digital eyeballs',
  version: 77140,
  indDel: true,
  fecCrea: dayjs('2022-08-24T22:37'),
  usuCrea: 'override auxiliary Terrace',
  ipCrea: 'Forward',
};

export const sampleWithPartialData: IAutorizacion = {
  id: '5811336c-843a-41d5-a526-7850688e5772',
  tipAutorizacion: 'Michigan Iowa',
  token: 'killer Licensed matrix',
  fecIniVig: dayjs('2022-08-24T22:40'),
  fecFinVig: dayjs('2022-08-25T01:21'),
  estado: 'Granite Guinea South',
  version: 34883,
  indDel: false,
  fecCrea: dayjs('2022-08-25T11:34'),
  usuCrea: 'deposit experiences Niue',
  ipCrea: 'compelling',
  usuModif: 'Libyan blockchains users',
  ipModif: 'program JBOD',
};

export const sampleWithFullData: IAutorizacion = {
  id: '88f6a331-3b3b-4164-915e-e61ad434c90e',
  tipAutorizacion: 'Handcrafted',
  token: 'Borders',
  fecIniVig: dayjs('2022-08-25T10:50'),
  fecFinVig: dayjs('2022-08-25T04:28'),
  estado: 'Concrete Infrastructure',
  version: 63645,
  indDel: false,
  fecCrea: dayjs('2022-08-25T07:49'),
  usuCrea: 'wireless',
  ipCrea: 'Lead',
  fecModif: dayjs('2022-08-25T17:23'),
  usuModif: 'robust Zambia',
  ipModif: 'object-oriented Up-sized systematic',
};

export const sampleWithNewData: NewAutorizacion = {
  tipAutorizacion: 'Parkways exuding',
  token: 'Industrial Cloned',
  fecIniVig: dayjs('2022-08-25T10:24'),
  fecFinVig: dayjs('2022-08-24T19:34'),
  estado: 'Extended',
  version: 44040,
  indDel: false,
  fecCrea: dayjs('2022-08-25T08:48'),
  usuCrea: 'supply-chains Islands',
  ipCrea: 'olive Awesome',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
