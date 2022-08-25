import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { HistoricoCajaComponent } from './list/historico-caja.component';
import { HistoricoCajaDetailComponent } from './detail/historico-caja-detail.component';
import { HistoricoCajaUpdateComponent } from './update/historico-caja-update.component';
import { HistoricoCajaDeleteDialogComponent } from './delete/historico-caja-delete-dialog.component';
import { HistoricoCajaRoutingModule } from './route/historico-caja-routing.module';

@NgModule({
  imports: [SharedModule, HistoricoCajaRoutingModule],
  declarations: [HistoricoCajaComponent, HistoricoCajaDetailComponent, HistoricoCajaUpdateComponent, HistoricoCajaDeleteDialogComponent],
})
export class HistoricoCajaModule {}
