import { Routes } from '@angular/router';

import { InventarioIngresoComponent } from './ingreso/inventario-ingreso.component';

import { InventarioSalidaComponent } from './salida/inventario-salida.component';
import { Authority } from 'app/config/authority.constants';
import { InventarioProductosComponent } from './productos/inventario-productos.component';
import { InventarioReportesComponent } from './reportes/inventario-reportes.component';
export const INVENTARIO_ROUTE: Routes = [
  {
    path: 'inventarioIngreso',
    component: InventarioIngresoComponent,
    data: {
      authorities: [Authority.GERENTE, Authority.ADMIN, Authority.INVENTARIADOR],
      pageTitle: 'Modulo Inventario - INGRESOS',
    },
  },
  {
    path: 'inventarioSalida',
    component: InventarioSalidaComponent,
    data: {
      authorities: [Authority.GERENTE, Authority.ADMIN, Authority.INVENTARIADOR],
      pageTitle: 'Modulo Inventario - SALIDAS',
    },
  },
  {
    path: 'inventarioProductos',
    component: InventarioProductosComponent,
    data: {
      authorities: [Authority.GERENTE, Authority.ADMIN, Authority.INVENTARIADOR],
      pageTitle: 'Modulo Inventario - PRODUCTOS',
    },
  },
  {
    path: 'inventarioReportes',
    component: InventarioReportesComponent,
    data: {
      authorities: [Authority.GERENTE, Authority.ADMIN, Authority.INVENTARIADOR],
      pageTitle: 'Modulo Inventario - REPORTES',
    },
  },
];
