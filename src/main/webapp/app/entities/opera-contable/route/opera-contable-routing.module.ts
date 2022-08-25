import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { OperaContableComponent } from '../list/opera-contable.component';
import { OperaContableDetailComponent } from '../detail/opera-contable-detail.component';
import { OperaContableUpdateComponent } from '../update/opera-contable-update.component';
import { OperaContableRoutingResolveService } from './opera-contable-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const operaContableRoute: Routes = [
  {
    path: '',
    component: OperaContableComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OperaContableDetailComponent,
    resolve: {
      operaContable: OperaContableRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OperaContableUpdateComponent,
    resolve: {
      operaContable: OperaContableRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OperaContableUpdateComponent,
    resolve: {
      operaContable: OperaContableRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(operaContableRoute)],
  exports: [RouterModule],
})
export class OperaContableRoutingModule {}
