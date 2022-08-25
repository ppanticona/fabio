import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { PlanContableComponent } from '../list/plan-contable.component';
import { PlanContableDetailComponent } from '../detail/plan-contable-detail.component';
import { PlanContableUpdateComponent } from '../update/plan-contable-update.component';
import { PlanContableRoutingResolveService } from './plan-contable-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const planContableRoute: Routes = [
  {
    path: '',
    component: PlanContableComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PlanContableDetailComponent,
    resolve: {
      planContable: PlanContableRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PlanContableUpdateComponent,
    resolve: {
      planContable: PlanContableRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PlanContableUpdateComponent,
    resolve: {
      planContable: PlanContableRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(planContableRoute)],
  exports: [RouterModule],
})
export class PlanContableRoutingModule {}
