import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { MovimientoProductoComponent } from './list/movimiento-producto.component';
import { MovimientoProductoDetailComponent } from './detail/movimiento-producto-detail.component';
import { MovimientoProductoUpdateComponent } from './update/movimiento-producto-update.component';
import { MovimientoProductoDeleteDialogComponent } from './delete/movimiento-producto-delete-dialog.component';
import { MovimientoProductoRoutingModule } from './route/movimiento-producto-routing.module';

@NgModule({
  imports: [SharedModule, MovimientoProductoRoutingModule],
  declarations: [
    MovimientoProductoComponent,
    MovimientoProductoDetailComponent,
    MovimientoProductoUpdateComponent,
    MovimientoProductoDeleteDialogComponent,
  ],
})
export class MovimientoProductoModule {}
