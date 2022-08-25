import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { LibDiarioComponent } from '../list/lib-diario.component';
import { LibDiarioDetailComponent } from '../detail/lib-diario-detail.component';
import { LibDiarioUpdateComponent } from '../update/lib-diario-update.component';
import { LibDiarioRoutingResolveService } from './lib-diario-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const libDiarioRoute: Routes = [
  {
    path: '',
    component: LibDiarioComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LibDiarioDetailComponent,
    resolve: {
      libDiario: LibDiarioRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LibDiarioUpdateComponent,
    resolve: {
      libDiario: LibDiarioRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LibDiarioUpdateComponent,
    resolve: {
      libDiario: LibDiarioRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(libDiarioRoute)],
  exports: [RouterModule],
})
export class LibDiarioRoutingModule {}
