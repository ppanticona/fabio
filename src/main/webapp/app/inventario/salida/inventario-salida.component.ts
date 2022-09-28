import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ICaja } from 'app/entities/caja/caja.model';
import { CajaService } from 'app/entities/caja/service/caja.service';
import { CajaDeleteDialogComponent } from 'app/entities/caja/delete/caja-delete-dialog.component';
import { InventarioSalidaDialogComponent } from './inventario-salida-dialog.component';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';

import { Subscription } from 'rxjs';
import { User } from 'app/admin/user-management/user-management.model';
import { UserManagementService } from 'app/admin/user-management/service/user-management.service';
import { OrdenService } from '../../entities/orden/service/orden.service';
@Component({
  selector: 'jhi-inventario-salida',
  templateUrl: './inventario-salida.component.html',
})
export class InventarioSalidaComponent implements OnInit {
  cajas?: ICaja[];
  isLoading = false;
  isLoadingSalidas = false;
  userCajeros: User[] | null = null;
  account: Account | null = null;
  authSubscription?: Subscription;
  listaSalidas: any;

  constructor(
    private userService: UserManagementService,
    protected ordenService: OrdenService,
    private accountService: AccountService,
    protected modalService: NgbModal
  ) {}

  loadAllSalidas(): void {
    this.isLoadingSalidas = true;
    this.ordenService.listByTipOrden('06').subscribe(
      (res: HttpResponse<any>) => {
        this.isLoadingSalidas = false;
        this.listaSalidas = res.body.ordenes ?? [];
      },
      () => (this.isLoadingSalidas = false)
    );
  }

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    this.loadAllSalidas();
  }

  trackId(index: number, item: ICaja): string {
    return item.id!;
  }

  nuevaSalida(): void {
    const modalRef = this.modalService.open(InventarioSalidaDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.usuCrea = this.account?.login;

    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'registrado') {
        this.loadAllSalidas();
      }
    });
  }
}
