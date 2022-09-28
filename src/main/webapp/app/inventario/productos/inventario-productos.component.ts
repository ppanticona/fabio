import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ICaja } from 'app/entities/caja/caja.model';
import { CajaDeleteDialogComponent } from 'app/entities/caja/delete/caja-delete-dialog.component';
import { InventarioProductosDialogComponent } from './inventario-productos-dialog.component';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';

import { Subscription } from 'rxjs';
import { User } from 'app/admin/user-management/user-management.model';
import { UserManagementService } from 'app/admin/user-management/service/user-management.service';
import { ProductoService } from '../../entities/producto/service/producto.service';
@Component({
  selector: 'jhi-inventario-productos',
  templateUrl: './inventario-productos.component.html',
})
export class InventarioProductosComponent implements OnInit {
  cajas?: ICaja[];
  isLoading = false;
  isLoadingProductos = false;
  userCajeros: User[] | null = null;
  inventarioProductos: any;
  account: Account | null = null;
  authSubscription?: Subscription;

  constructor(
    private userService: UserManagementService,
    protected productoService: ProductoService,
    private accountService: AccountService,
    protected modalService: NgbModal
  ) {}

  loadCajas(): void {
    this.isLoading = true;

    this.productoService.query().subscribe(
      (res: HttpResponse<ICaja[]>) => {
        this.isLoading = false;
        this.cajas = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  loadProductosInventario(): void {
    this.isLoadingProductos = true;
    this.productoService.listProductosInventario().subscribe(
      (res: HttpResponse<any>) => {
        this.isLoadingProductos = false;
        this.inventarioProductos = res.body.productos ?? [];
      },
      () => (this.isLoadingProductos = false)
    );
  }

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    this.loadProductosInventario();
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

  nuevoProducto(): void {
    const modalRef = this.modalService.open(InventarioProductosDialogComponent, { size: 'lg', backdrop: 'static' });

    modalRef.componentInstance.producto.usuCrea = this.account?.login;

    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'registrado') {
        this.loadCajas();
      }
    });
  }
  cerrarCaja(caja: ICaja): void {
    const modalRef = this.modalService.open(InventarioProductosDialogComponent, { size: 'lg', backdrop: 'static' });
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
