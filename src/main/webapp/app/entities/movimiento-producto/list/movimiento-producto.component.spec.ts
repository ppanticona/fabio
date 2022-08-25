import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { MovimientoProductoService } from '../service/movimiento-producto.service';

import { MovimientoProductoComponent } from './movimiento-producto.component';

describe('MovimientoProducto Management Component', () => {
  let comp: MovimientoProductoComponent;
  let fixture: ComponentFixture<MovimientoProductoComponent>;
  let service: MovimientoProductoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'movimiento-producto', component: MovimientoProductoComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [MovimientoProductoComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(MovimientoProductoComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MovimientoProductoComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(MovimientoProductoService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 'ABC' }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.movimientoProductos?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to movimientoProductoService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getMovimientoProductoIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getMovimientoProductoIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
