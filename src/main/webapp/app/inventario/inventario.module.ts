import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InventarioIngresoComponent } from './ingreso/inventario-ingreso.component';
import { InventarioIngresoDialogComponent } from './ingreso/inventario-ingreso-dialog.component';
import { InventarioIngresoDialog2Component } from './ingreso/inventario-ingreso-dialog-2.component';
import { InventarioSalidaComponent } from './salida/inventario-salida.component';
import { InventarioSalidaDialogComponent } from './salida/inventario-salida-dialog.component';

import { InventarioProductosComponent } from './productos/inventario-productos.component';
import { InventarioProductosDialogComponent } from './productos/inventario-productos-dialog.component';

import { InventarioReportesComponent } from './reportes/inventario-reportes.component';
import { InventarioReportesDialogComponent } from './reportes/inventario-reportes-dialog.component';

import { SharedModule } from 'app/shared/shared.module';
import { INVENTARIO_ROUTE } from './inventario.route';

@NgModule({
  imports: [SharedModule, RouterModule.forChild(INVENTARIO_ROUTE)],
  declarations: [
    InventarioIngresoComponent,
    InventarioIngresoDialogComponent,
    InventarioIngresoDialog2Component,
    InventarioSalidaComponent,
    InventarioSalidaDialogComponent,
    InventarioProductosComponent,
    InventarioProductosDialogComponent,
    InventarioReportesComponent,
    InventarioReportesDialogComponent,
  ],
  entryComponents: [
    InventarioIngresoDialogComponent,
    InventarioIngresoDialog2Component,
    InventarioSalidaDialogComponent,
    InventarioProductosDialogComponent,
    InventarioReportesDialogComponent,
  ],
})
export class InventarioModule {}
