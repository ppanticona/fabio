import dayjs from 'dayjs/esm';
import { IProducto } from 'app/entities/producto/producto.model';
import { IRegVenta } from 'app/entities/reg-venta/reg-venta.model';
import { IOrden } from 'app/entities/orden/orden.model';
import { IRegCompras } from 'app/entities/reg-compras/reg-compras.model';

export interface IMovimientoProducto {
  id: string;
  tipMovimiento?: string | null;
  tip2Movimiento?: string | null;
  preVenta?: number | null;
  precCompra?: number | null;
  cnt?: number | null;
  lote?: string | null;
  fecMovimiento?: dayjs.Dayjs | null;
  version?: number | null;
  indDel?: boolean | null;
  fecCrea?: dayjs.Dayjs | null;
  usuCrea?: string | null;
  ipCrea?: string | null;
  fecModif?: dayjs.Dayjs | null;
  usuModif?: string | null;
  ipModif?: string | null;
  producto?: Pick<IProducto, 'id'> | null;
  regVenta?: Pick<IRegVenta, 'id'> | null;
  orden?: Pick<IOrden, 'id'> | null;
  regCompras?: Pick<IRegCompras, 'id'> | null;
}

export type NewMovimientoProducto = Omit<IMovimientoProducto, 'id'> & { id: null };
