import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { HistoricoCajaComponent } from '../list/historico-caja.component';
import { HistoricoCajaDetailComponent } from '../detail/historico-caja-detail.component';
import { HistoricoCajaUpdateComponent } from '../update/historico-caja-update.component';
import { HistoricoCajaRoutingResolveService } from './historico-caja-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const historicoCajaRoute: Routes = [
  {
    path: '',
    component: HistoricoCajaComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: HistoricoCajaDetailComponent,
    resolve: {
      historicoCaja: HistoricoCajaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: HistoricoCajaUpdateComponent,
    resolve: {
      historicoCaja: HistoricoCajaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: HistoricoCajaUpdateComponent,
    resolve: {
      historicoCaja: HistoricoCajaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(historicoCajaRoute)],
  exports: [RouterModule],
})
export class HistoricoCajaRoutingModule {}
