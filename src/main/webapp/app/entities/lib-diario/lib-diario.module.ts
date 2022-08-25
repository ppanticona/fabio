import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { LibDiarioComponent } from './list/lib-diario.component';
import { LibDiarioDetailComponent } from './detail/lib-diario-detail.component';
import { LibDiarioUpdateComponent } from './update/lib-diario-update.component';
import { LibDiarioDeleteDialogComponent } from './delete/lib-diario-delete-dialog.component';
import { LibDiarioRoutingModule } from './route/lib-diario-routing.module';

@NgModule({
  imports: [SharedModule, LibDiarioRoutingModule],
  declarations: [LibDiarioComponent, LibDiarioDetailComponent, LibDiarioUpdateComponent, LibDiarioDeleteDialogComponent],
})
export class LibDiarioModule {}
