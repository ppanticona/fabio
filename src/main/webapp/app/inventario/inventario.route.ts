import { Routes } from '@angular/router';

import { InventarioIngresoComponent } from './ingreso/inventario-ingreso.component';

import { InventarioSalidaComponent } from './salida/inventario-salida.component';

import { InventarioProductosComponent } from './productos/inventario-productos.component';
import { InventarioReportesComponent } from './reportes/inventario-reportes.component';
export const INVENTARIO_ROUTE: Routes = [
  {
    path: 'inventarioIngreso',
    component: InventarioIngresoComponent,
    data: {
      pageTitle: 'Modulo Inventario - INGRESOS',
    },
  },
  {
    path: 'inventarioSalida',
    component: InventarioSalidaComponent,
    data: {
      pageTitle: 'Modulo Inventario - SALIDAS',
    },
  },
  {
    path: 'inventarioProductos',
    component: InventarioProductosComponent,
    data: {
      pageTitle: 'Modulo Inventario - PRODUCTOS',
    },
  },
  {
    path: 'inventarioReportes',
    component: InventarioReportesComponent,
    data: {
      pageTitle: 'Modulo Inventario - REPORTES',
    },
  },
];
