import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ProductoService } from '../service/producto.service';

import { ProductoComponent } from './producto.component';

describe('Producto Management Component', () => {
  let comp: ProductoComponent;
  let fixture: ComponentFixture<ProductoComponent>;
  let service: ProductoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'producto', component: ProductoComponent }]), HttpClientTestingModule],
      declarations: [ProductoComponent],
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
      .overrideTemplate(ProductoComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ProductoComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ProductoService);

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
    expect(comp.productos?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to productoService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getProductoIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getProductoIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
