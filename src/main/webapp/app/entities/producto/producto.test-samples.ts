import dayjs from 'dayjs/esm';

import { IProducto, NewProducto } from './producto.model';

export const sampleWithRequiredData: IProducto = {
  id: '8a4deb02-683d-4854-8a8a-9eeee169f3b8',
  descripcion: 'Incredible Small',
  valor: 22513,
  urlImage: 'Savings',
  estado: 'deposit Intelligent',
  version: 46113,
  indDel: true,
  fecCrea: dayjs('2022-08-25T01:21'),
  usuCrea: 'Expanded revolutionary Plastic',
  ipCrea: 'unleash',
};

export const sampleWithPartialData: IProducto = {
  id: '2b720f23-312c-4ef7-b1b6-3f24e1e329db',
  codQr: 'methodology Sausages eyeballs',
  descripcion: 'Seamless invoice Dynamic',
  detalleDesc: 'Face Uzbekistan',
  valor: 27670,
  fecIniVig: dayjs('2022-08-25T05:09'),
  costoProd: 17097,
  urlImage: 'TCP',
  estado: 'pixel Clothing',
  version: 86863,
  indDel: true,
  fecCrea: dayjs('2022-08-25T00:27'),
  usuCrea: 'interface Plastic',
  ipCrea: 'Gorgeous transmitting',
  usuModif: 'core Proactive azure',
};

export const sampleWithFullData: IProducto = {
  id: '942dd40e-b0ed-49e5-8a31-069f7101bec9',
  codProducto: 'TCP',
  codQr: 'Reduced',
  codBarra: 'Stravenue olive',
  descripcion: 'revolutionize',
  detalleDesc: 'Krona knowledge',
  valor: 68176,
  fecIniVig: dayjs('2022-08-25T05:06'),
  fecFinVig: dayjs('2022-08-25T09:52'),
  costoProd: 86836,
  urlImage: 'bypassing',
  estado: 'Borders Avon Course',
  version: 92473,
  indDel: false,
  fecCrea: dayjs('2022-08-24T20:40'),
  usuCrea: 'Buckinghamshire Handmade Buckinghamshire',
  ipCrea: 'distributed Missouri Buckinghamshire',
  fecModif: dayjs('2022-08-25T11:36'),
  usuModif: 'SDD invoice maroon',
  ipModif: 'Unbranded cross-platform',
};

export const sampleWithNewData: NewProducto = {
  descripcion: 'Fuerte',
  valor: 58895,
  urlImage: 'Fresh',
  estado: 'empowering Buckinghamshire',
  version: 76125,
  indDel: true,
  fecCrea: dayjs('2022-08-25T17:16'),
  usuCrea: 'Account',
  ipCrea: 'JBOD Virginia',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
