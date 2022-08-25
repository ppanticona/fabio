import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DetalleOperaContableComponent } from './list/detalle-opera-contable.component';
import { DetalleOperaContableDetailComponent } from './detail/detalle-opera-contable-detail.component';
import { DetalleOperaContableUpdateComponent } from './update/detalle-opera-contable-update.component';
import { DetalleOperaContableDeleteDialogComponent } from './delete/detalle-opera-contable-delete-dialog.component';
import { DetalleOperaContableRoutingModule } from './route/detalle-opera-contable-routing.module';

@NgModule({
  imports: [SharedModule, DetalleOperaContableRoutingModule],
  declarations: [
    DetalleOperaContableComponent,
    DetalleOperaContableDetailComponent,
    DetalleOperaContableUpdateComponent,
    DetalleOperaContableDeleteDialogComponent,
  ],
})
export class DetalleOperaContableModule {}
