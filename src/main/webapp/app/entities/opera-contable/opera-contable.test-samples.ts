import dayjs from 'dayjs/esm';

import { IOperaContable, NewOperaContable } from './opera-contable.model';

export const sampleWithRequiredData: IOperaContable = {
  id: 'a23d5947-56aa-41df-a6a0-cf48d8fc9bd5',
  descOpera: 'withdrawal core fuchsia',
  fecCrea: dayjs('2022-08-24T18:36'),
  usuCrea: 'Account',
  ipCrea: 'black',
};

export const sampleWithPartialData: IOperaContable = {
  id: 'cc5dd143-391b-4c30-a3c7-8118250b69d9',
  descOpera: 'Cotton',
  areaRela: 'application',
  fecCrea: dayjs('2022-08-25T11:10'),
  usuCrea: 'Product',
  ipCrea: 'invoice mint',
  usuModif: 'Rubber revolutionary Illinois',
  ipModif: 'Garden Lights',
};

export const sampleWithFullData: IOperaContable = {
  id: '90931c1b-6967-44d2-b24f-7df2d96ed24f',
  descOpera: 'Land',
  areaRela: 'Legacy',
  codLib: 'payment Personal Central',
  fecCrea: dayjs('2022-08-24T18:11'),
  usuCrea: 'Steel',
  ipCrea: 'Loan Operations',
  fecModif: dayjs('2022-08-25T08:58'),
  usuModif: 'Graphic invoice',
  ipModif: 'Credit Hungary Chair',
};

export const sampleWithNewData: NewOperaContable = {
  descOpera: 'EXE Mayotte indexing',
  fecCrea: dayjs('2022-08-24T21:15'),
  usuCrea: 'bandwidth',
  ipCrea: 'Rustic Spurs',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
