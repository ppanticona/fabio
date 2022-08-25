import dayjs from 'dayjs/esm';

import { IPlanContable, NewPlanContable } from './plan-contable.model';

export const sampleWithRequiredData: IPlanContable = {
  id: '6f5b1a2f-4f44-4d02-80b3-7e5eaccd8ae4',
  nivPlan: 44874,
  codPlan: 'Fresh',
  descCuenta: 'red Small haptic',
  fecCrea: dayjs('2022-08-25T04:35'),
  usuCrea: 'Bedfordshire',
  ipCrea: 'array Georgia',
};

export const sampleWithPartialData: IPlanContable = {
  id: '6529e32c-7ab6-4790-aa3b-074b617b66b3',
  tipPlan: 'Intelligent microchip',
  nivPlan: 47771,
  codPlan: 'convergence Central',
  descCuenta: 'Card Towels ADP',
  resoPlan: 'Enhanced plum',
  fecCrea: dayjs('2022-08-25T04:41'),
  usuCrea: 'Personal',
  ipCrea: 'Bhutanese Interface wireless',
  ipModif: 'program asynchronous',
};

export const sampleWithFullData: IPlanContable = {
  id: 'abf7544d-dfc5-4cf0-999a-e716a829ae13',
  tipPlan: 'applications Steel',
  nivPlan: 48007,
  codPlan: 'Legacy Card',
  descCuenta: 'Cross-platform',
  anhoPlan: 'Developer',
  resoPlan: 'cyan International',
  fecCrea: dayjs('2022-08-25T14:34'),
  usuCrea: 'monitor',
  ipCrea: 'Pennsylvania',
  fecModif: dayjs('2022-08-25T06:47'),
  usuModif: 'bypassing innovative',
  ipModif: 'users Face SMS',
};

export const sampleWithNewData: NewPlanContable = {
  nivPlan: 63729,
  codPlan: 'Bedfordshire SMTP',
  descCuenta: 'lime robust Money',
  fecCrea: dayjs('2022-08-25T13:01'),
  usuCrea: 'Delaware',
  ipCrea: 'Optimization',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
