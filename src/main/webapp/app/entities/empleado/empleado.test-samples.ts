import dayjs from 'dayjs/esm';

import { IEmpleado, NewEmpleado } from './empleado.model';

export const sampleWithRequiredData: IEmpleado = {
  id: '32e98d42-38a8-4b04-b144-c8b3ce3ecae9',
  tipDocEmp: 'Fantastic circuit Greenland',
  nroDocEmp: 'Checking Station',
  nombresEmp: 'SCSI Money',
  apellidosEmp: 'invoice scalable',
  fecNac: dayjs('2022-08-25T09:44'),
  estado: 'withdrawal Account Bedfordshire',
  version: 69758,
  indDel: false,
  fecCrea: dayjs('2022-08-25T08:16'),
  usuCrea: 'SMS program',
  ipCrea: 'program Frozen bypass',
};

export const sampleWithPartialData: IEmpleado = {
  id: 'fd2f2d44-b322-4e59-966d-3f048a2ba93b',
  tipDocEmp: 'Guinea',
  nroDocEmp: 'Tobago Account',
  nombresEmp: 'Wisconsin Guilder local',
  apellidosEmp: 'Applications Health',
  categoria: 'distributed',
  tel2: 'protocol Reactive',
  correo: 'bi-directional',
  direccion: 'microchip Small Account',
  distrito: 'Card',
  fecIngreso: dayjs('2022-08-24T21:53'),
  fecNac: dayjs('2022-08-25T00:43'),
  userId: 'Car JSON',
  estado: 'benchmark Coordinator',
  version: 86937,
  indDel: true,
  fecCrea: dayjs('2022-08-25T14:15'),
  usuCrea: 'Forest AI static',
  ipCrea: 'Robust bus',
  fecModif: dayjs('2022-08-25T11:19'),
};

export const sampleWithFullData: IEmpleado = {
  id: '84b93b30-899b-4473-8865-fbdefaa85b5b',
  tipDocEmp: 'Manager',
  nroDocEmp: 'multi-byte',
  nombresEmp: 'Rial Grocery Handcrafted',
  apellidosEmp: 'hacking',
  categoria: 'card SQL Avon',
  tel1: 'Checking',
  tel2: 'Pennsylvania Books',
  correo: 'deposit Frozen forecast',
  direccion: 'leading-edge',
  refDirecc: 'one-to-one bypass',
  distrito: 'Producer COM microchip',
  fecIngreso: dayjs('2022-08-25T02:02'),
  fecNac: dayjs('2022-08-24T22:49'),
  userId: 'interface invoice Dynamic',
  estado: 'customized Human Checking',
  version: 348,
  indDel: false,
  fecCrea: dayjs('2022-08-25T04:25'),
  usuCrea: 'SMTP Lake paradigms',
  ipCrea: 'Managed invoice bluetooth',
  fecModif: dayjs('2022-08-25T07:22'),
  usuModif: 'hack software Borders',
  ipModif: 'Division Architect',
};

export const sampleWithNewData: NewEmpleado = {
  tipDocEmp: 'pixel even-keeled',
  nroDocEmp: 'state Unbranded',
  nombresEmp: 'invoice Dinar Carolina',
  apellidosEmp: 'XSS',
  fecNac: dayjs('2022-08-24T21:33'),
  estado: 'Assistant withdrawal',
  version: 57127,
  indDel: false,
  fecCrea: dayjs('2022-08-25T00:47'),
  usuCrea: 'Market matrix holistic',
  ipCrea: 'hacking Open-architected',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
