import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DetalleOperaContableComponent } from '../list/detalle-opera-contable.component';
import { DetalleOperaContableDetailComponent } from '../detail/detalle-opera-contable-detail.component';
import { DetalleOperaContableUpdateComponent } from '../update/detalle-opera-contable-update.component';
import { DetalleOperaContableRoutingResolveService } from './detalle-opera-contable-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const detalleOperaContableRoute: Routes = [
  {
    path: '',
    component: DetalleOperaContableComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DetalleOperaContableDetailComponent,
    resolve: {
      detalleOperaContable: DetalleOperaContableRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DetalleOperaContableUpdateComponent,
    resolve: {
      detalleOperaContable: DetalleOperaContableRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DetalleOperaContableUpdateComponent,
    resolve: {
      detalleOperaContable: DetalleOperaContableRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(detalleOperaContableRoute)],
  exports: [RouterModule],
})
export class DetalleOperaContableRoutingModule {}
