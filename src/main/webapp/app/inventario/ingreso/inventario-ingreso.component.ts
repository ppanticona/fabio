import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ICaja } from 'app/entities/caja/caja.model';
import { CajaService } from 'app/entities/caja/service/caja.service';
import { CajaDeleteDialogComponent } from 'app/entities/caja/delete/caja-delete-dialog.component';
import { InventarioIngresoDialogComponent } from './inventario-ingreso-dialog.component';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';

import { Subscription } from 'rxjs';
import { User } from 'app/admin/user-management/user-management.model';
import { UserManagementService } from 'app/admin/user-management/service/user-management.service';
import { OrdenService } from '../../entities/orden/service/orden.service';
@Component({
  selector: 'jhi-inventario-ingreso',
  templateUrl: './inventario-ingreso.component.html',
})
export class InventarioIngresoComponent implements OnInit {
  cajas?: ICaja[];
  isLoading = false;
  isLoadingIngresos = false;
  userCajeros: User[] | null = null;
  account: Account | null = null;
  listaIngresos: any;
  authSubscription?: Subscription;

  constructor(
    private userService: UserManagementService,
    protected ordenService: OrdenService,
    private accountService: AccountService,
    protected modalService: NgbModal
  ) {}

  loadAllIngresos(): void {
    this.isLoadingIngresos = true;
    this.ordenService.listByTipOrden('05').subscribe(
      (res: HttpResponse<any>) => {
        this.isLoadingIngresos = false;
        this.listaIngresos = res.body.ordenes ?? [];
      },
      () => (this.isLoadingIngresos = false)
    );
  }

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    this.loadAllIngresos();
  }

  trackId(index: number, item: ICaja): string {
    return item.id!;
  }

  nuevoIngreso(): void {
    const modalRef = this.modalService.open(InventarioIngresoDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.usuCrea = this.account?.login;

    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'registrado') {
        this.loadAllIngresos();
      }
    });
  }
}
