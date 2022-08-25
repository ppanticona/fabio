import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'promocion',
        data: { pageTitle: 'Promocions' },
        loadChildren: () => import('./promocion/promocion.module').then(m => m.PromocionModule),
      },
      {
        path: 'reg-venta',
        data: { pageTitle: 'RegVentas' },
        loadChildren: () => import('./reg-venta/reg-venta.module').then(m => m.RegVentaModule),
      },
      {
        path: 'reg-compras',
        data: { pageTitle: 'RegCompras' },
        loadChildren: () => import('./reg-compras/reg-compras.module').then(m => m.RegComprasModule),
      },
      {
        path: 'lib-diario',
        data: { pageTitle: 'LibDiarios' },
        loadChildren: () => import('./lib-diario/lib-diario.module').then(m => m.LibDiarioModule),
      },
      {
        path: 'opera-contable',
        data: { pageTitle: 'OperaContables' },
        loadChildren: () => import('./opera-contable/opera-contable.module').then(m => m.OperaContableModule),
      },
      {
        path: 'detalle-opera-contable',
        data: { pageTitle: 'DetalleOperaContables' },
        loadChildren: () => import('./detalle-opera-contable/detalle-opera-contable.module').then(m => m.DetalleOperaContableModule),
      },
      {
        path: 'plan-contable',
        data: { pageTitle: 'PlanContables' },
        loadChildren: () => import('./plan-contable/plan-contable.module').then(m => m.PlanContableModule),
      },
      {
        path: 'movimiento-caja',
        data: { pageTitle: 'MovimientoCajas' },
        loadChildren: () => import('./movimiento-caja/movimiento-caja.module').then(m => m.MovimientoCajaModule),
      },
      {
        path: 'historico-caja',
        data: { pageTitle: 'HistoricoCajas' },
        loadChildren: () => import('./historico-caja/historico-caja.module').then(m => m.HistoricoCajaModule),
      },
      {
        path: 'autorizacion',
        data: { pageTitle: 'Autorizacions' },
        loadChildren: () => import('./autorizacion/autorizacion.module').then(m => m.AutorizacionModule),
      },
      {
        path: 'caja',
        data: { pageTitle: 'Cajas' },
        loadChildren: () => import('./caja/caja.module').then(m => m.CajaModule),
      },
      {
        path: 'movimiento-producto',
        data: { pageTitle: 'MovimientoProductos' },
        loadChildren: () => import('./movimiento-producto/movimiento-producto.module').then(m => m.MovimientoProductoModule),
      },
      {
        path: 'empleado',
        data: { pageTitle: 'Empleados' },
        loadChildren: () => import('./empleado/empleado.module').then(m => m.EmpleadoModule),
      },
      {
        path: 'producto',
        data: { pageTitle: 'Productos' },
        loadChildren: () => import('./producto/producto.module').then(m => m.ProductoModule),
      },
      {
        path: 'cliente',
        data: { pageTitle: 'Clientes' },
        loadChildren: () => import('./cliente/cliente.module').then(m => m.ClienteModule),
      },
      {
        path: 'proveedor',
        data: { pageTitle: 'Proveedors' },
        loadChildren: () => import('./proveedor/proveedor.module').then(m => m.ProveedorModule),
      },
      {
        path: 'detalle-orden',
        data: { pageTitle: 'DetalleOrdens' },
        loadChildren: () => import('./detalle-orden/detalle-orden.module').then(m => m.DetalleOrdenModule),
      },
      {
        path: 'orden',
        data: { pageTitle: 'Ordens' },
        loadChildren: () => import('./orden/orden.module').then(m => m.OrdenModule),
      },
      {
        path: 'secuencias',
        data: { pageTitle: 'Secuencias' },
        loadChildren: () => import('./secuencias/secuencias.module').then(m => m.SecuenciasModule),
      },
      {
        path: 'parametros',
        data: { pageTitle: 'Parametros' },
        loadChildren: () => import('./parametros/parametros.module').then(m => m.ParametrosModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
