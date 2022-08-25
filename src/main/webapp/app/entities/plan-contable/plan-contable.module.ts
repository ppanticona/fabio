import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { PlanContableComponent } from './list/plan-contable.component';
import { PlanContableDetailComponent } from './detail/plan-contable-detail.component';
import { PlanContableUpdateComponent } from './update/plan-contable-update.component';
import { PlanContableDeleteDialogComponent } from './delete/plan-contable-delete-dialog.component';
import { PlanContableRoutingModule } from './route/plan-contable-routing.module';

@NgModule({
  imports: [SharedModule, PlanContableRoutingModule],
  declarations: [PlanContableComponent, PlanContableDetailComponent, PlanContableUpdateComponent, PlanContableDeleteDialogComponent],
})
export class PlanContableModule {}
