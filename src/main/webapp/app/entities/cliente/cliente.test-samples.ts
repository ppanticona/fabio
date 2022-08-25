import dayjs from 'dayjs/esm';

import { ICliente, NewCliente } from './cliente.model';

export const sampleWithRequiredData: ICliente = {
  id: '74988507-f857-4196-9f51-d90fcaf0123e',
  tipDocCli: 'navigate streamline',
  nroDocCli: 'Orchestrator',
  nombresCli: 'THX Peso deposit',
  apellidosCli: 'Rico Savings',
  tel1: 'withdrawal Libyan',
  fecNac: dayjs('2022-08-25T13:31'),
  estado: 'Bedfordshire niches superstructure',
  version: 80468,
  indDel: true,
  fecCrea: dayjs('2022-08-25T08:20'),
  usuCrea: 'Trail',
  ipCrea: 'invoice Granite Regional',
};

export const sampleWithPartialData: ICliente = {
  id: '383c00e5-6b03-467e-a564-c315c1badacd',
  tipDocCli: 'XML Direct neural-net',
  nroDocCli: 'Savings Carolina',
  nombresCli: 'transmitting Kong virtual',
  apellidosCli: 'embrace',
  tel1: 'Cambridgeshire Sleek',
  correo: 'Armenia Island evolve',
  distrito: 'Auto Dakota',
  fecNac: dayjs('2022-08-25T10:08'),
  estado: 'CSS dedicated software',
  version: 45042,
  indDel: true,
  fecCrea: dayjs('2022-08-24T19:35'),
  usuCrea: 'Granite',
  ipCrea: 'Ferry Jan Tools',
  fecModif: dayjs('2022-08-24T21:32'),
  ipModif: 'deliverables',
};

export const sampleWithFullData: ICliente = {
  id: '58bbd330-1d90-4d67-9922-1feecc0fb2c9',
  tipDocCli: 'deposit olive functionalities',
  nroDocCli: 'workforce dynamic',
  nombresCli: 'Ameliorated optical',
  apellidosCli: 'Distributed Shilling',
  tel1: 'Practical Innovative',
  tel2: 'Object-based FTP',
  correo: 'eyeballs upward-trending Tuvalu',
  direccion: 'payment',
  refDireccion: 'supply-chains plug-and-play',
  distrito: 'Digitized',
  fecNac: dayjs('2022-08-25T06:53'),
  estado: 'ubiquitous transmitting Computers',
  version: 67239,
  indDel: false,
  fecCrea: dayjs('2022-08-25T08:04'),
  usuCrea: 'pink',
  ipCrea: 'Sleek Jersey CSS',
  fecModif: dayjs('2022-08-24T22:10'),
  usuModif: 'deposit COM Exclusive',
  ipModif: 'Streets Account',
};

export const sampleWithNewData: NewCliente = {
  tipDocCli: 'Developer Bacon',
  nroDocCli: 'backing',
  nombresCli: 'Granite evolve Croatia',
  apellidosCli: 'navigating Analyst',
  tel1: 'B2C',
  fecNac: dayjs('2022-08-24T23:55'),
  estado: 'web-enabled',
  version: 44364,
  indDel: false,
  fecCrea: dayjs('2022-08-25T00:00'),
  usuCrea: 'quantify indexing',
  ipCrea: 'Integration workforce',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
