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
@Component({
  selector: 'jhi-inventario-ingreso',
  templateUrl: './inventario-ingreso.component.html',
})
export class InventarioIngresoComponent implements OnInit {
  cajas?: ICaja[];
  isLoading = false;
  isLoadingUsers = false;
  userCajeros: User[] | null = null;
  account: Account | null = null;
  authSubscription?: Subscription;

  constructor(
    private userService: UserManagementService,
    protected cajaService: CajaService,
    private accountService: AccountService,
    protected modalService: NgbModal
  ) {}

  loadCajas(): void {
    this.isLoading = true;

    this.cajaService.query().subscribe(
      (res: HttpResponse<ICaja[]>) => {
        this.isLoading = false;
        this.cajas = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  loadAllUsers(): void {
    this.isLoadingUsers = true;
    this.userService.query().subscribe(
      (res: HttpResponse<User[]>) => {
        this.isLoadingUsers = false;
        this.userCajeros = res.body?.filter(x => x.authorities?.includes('ROLE_CAJERO')) ?? [];
      },
      () => (this.isLoadingUsers = false)
    );
  }

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
  }

  trackId(index: number, item: ICaja): string {
    return item.id!;
  }

  delete(caja: ICaja): void {
    const modalRef = this.modalService.open(CajaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.caja = caja;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadCajas();
      }
    });
  }

  nuevoIngreso(): void {
    const modalRef = this.modalService.open(InventarioIngresoDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.usuCrea = this.account?.login;

    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'registrado') {
        this.loadCajas();
      }
    });
  }
  cerrarCaja(caja: ICaja): void {
    const modalRef = this.modalService.open(InventarioIngresoDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.caja = caja;
    modalRef.componentInstance.aperturaCaja = false;
    modalRef.componentInstance.cierreCaja = true;
    modalRef.componentInstance.usuCrea = this.account?.login;

    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'registrado') {
        this.loadCajas();
      }
    });
  }
}
