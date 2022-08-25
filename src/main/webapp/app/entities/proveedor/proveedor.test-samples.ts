import dayjs from 'dayjs/esm';

import { IProveedor, NewProveedor } from './proveedor.model';

export const sampleWithRequiredData: IProveedor = {
  id: '4ba12e35-f990-457b-bf0f-9a49bdf4eec2',
  tipDocProv: 'Checking',
  nroDocProv: 'Soft Shoes',
  nombresRazonSocProv: 'markets parsing',
  estado: 'Automotive',
  version: 26332,
  indDel: true,
  fecCrea: dayjs('2022-08-25T11:55'),
  usuCrea: 'deposit static',
  ipCrea: 'methodology withdrawal Licensed',
};

export const sampleWithPartialData: IProveedor = {
  id: 'e25f2ccf-aa38-4c90-91b1-605b3f55f62e',
  tipDocProv: 'Quetzal Intelligent Pennsylvania',
  nroDocProv: 'Rustic Clothing withdrawal',
  nombresRazonSocProv: 'port',
  tel1: 'Liaison calculate',
  direccion: 'withdrawal Wooden',
  estado: 'Loan seamless',
  version: 67341,
  indDel: false,
  fecCrea: dayjs('2022-08-24T20:26'),
  usuCrea: 'firewall Sleek white',
  ipCrea: 'hacking Quality',
  fecModif: dayjs('2022-08-25T06:02'),
  ipModif: 'Berkshire pink',
};

export const sampleWithFullData: IProveedor = {
  id: '8985c952-1131-447e-b3b0-697f07a85318',
  tipDocProv: 'navigate District navigating',
  nroDocProv: 'neural-net',
  nombresRazonSocProv: 'Grocery',
  tel1: 'connecting invoice',
  tel2: 'Fantastic Chief',
  correo: 'Home Shoes Mali',
  direccion: 'Sleek engage Customer',
  refDireccion: 'Assistant',
  distrito: 'Handcrafted 1080p',
  estado: 'initiatives enable',
  version: 27381,
  indDel: false,
  fecCrea: dayjs('2022-08-25T14:43'),
  usuCrea: 'Franc Tasty District',
  ipCrea: 'microchip Frozen',
  fecModif: dayjs('2022-08-25T04:05'),
  usuModif: 'payment Namibia',
  ipModif: 'Cote orchid',
};

export const sampleWithNewData: NewProveedor = {
  tipDocProv: 'methodologies Latvian transmit',
  nroDocProv: 'infomediaries',
  nombresRazonSocProv: 'vertical',
  estado: 'redundant Account index',
  version: 72184,
  indDel: false,
  fecCrea: dayjs('2022-08-25T00:44'),
  usuCrea: 'Cotton Executive',
  ipCrea: 'Canada USB',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
