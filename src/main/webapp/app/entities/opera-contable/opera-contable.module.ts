import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { OperaContableComponent } from './list/opera-contable.component';
import { OperaContableDetailComponent } from './detail/opera-contable-detail.component';
import { OperaContableUpdateComponent } from './update/opera-contable-update.component';
import { OperaContableDeleteDialogComponent } from './delete/opera-contable-delete-dialog.component';
import { OperaContableRoutingModule } from './route/opera-contable-routing.module';

@NgModule({
  imports: [SharedModule, OperaContableRoutingModule],
  declarations: [OperaContableComponent, OperaContableDetailComponent, OperaContableUpdateComponent, OperaContableDeleteDialogComponent],
})
export class OperaContableModule {}
