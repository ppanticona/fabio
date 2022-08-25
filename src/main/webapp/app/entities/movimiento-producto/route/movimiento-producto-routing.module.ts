import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { MovimientoProductoComponent } from '../list/movimiento-producto.component';
import { MovimientoProductoDetailComponent } from '../detail/movimiento-producto-detail.component';
import { MovimientoProductoUpdateComponent } from '../update/movimiento-producto-update.component';
import { MovimientoProductoRoutingResolveService } from './movimiento-producto-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const movimientoProductoRoute: Routes = [
  {
    path: '',
    component: MovimientoProductoComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MovimientoProductoDetailComponent,
    resolve: {
      movimientoProducto: MovimientoProductoRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MovimientoProductoUpdateComponent,
    resolve: {
      movimientoProducto: MovimientoProductoRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MovimientoProductoUpdateComponent,
    resolve: {
      movimientoProducto: MovimientoProductoRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(movimientoProductoRoute)],
  exports: [RouterModule],
})
export class MovimientoProductoRoutingModule {}
