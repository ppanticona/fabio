import dayjs from 'dayjs/esm';
import { IOrden } from 'app/entities/orden/orden.model';
import { IProducto } from 'app/entities/producto/producto.model';
import { IPromocion } from 'app/entities/promocion/promocion.model';

export interface IDetalleOrden {
  id: string;
  cantidad?: number | null;
  valUni?: number | null;
  dcto?: number | null;
  subtotal?: number | null;
  observacion?: string | null;
  estado?: string | null;
  version?: number | null;
  indDel?: boolean | null;
  fecCrea?: dayjs.Dayjs | null;
  usuCrea?: string | null;
  ipCrea?: string | null;
  fecModif?: dayjs.Dayjs | null;
  usuModif?: string | null;
  ipModif?: string | null;
  orden?: Pick<IOrden, 'id'> | null;
  producto?: Pick<IProducto, 'id'> | null;
  promocion?: Pick<IPromocion, 'id'> | null;
}

export type NewDetalleOrden = Omit<IDetalleOrden, 'id'> & { id: null };
