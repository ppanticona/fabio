import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { DetalleOperaContableService } from '../service/detalle-opera-contable.service';

import { DetalleOperaContableComponent } from './detalle-opera-contable.component';

describe('DetalleOperaContable Management Component', () => {
  let comp: DetalleOperaContableComponent;
  let fixture: ComponentFixture<DetalleOperaContableComponent>;
  let service: DetalleOperaContableService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'detalle-opera-contable', component: DetalleOperaContableComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [DetalleOperaContableComponent],
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
      .overrideTemplate(DetalleOperaContableComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DetalleOperaContableComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(DetalleOperaContableService);

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
    expect(comp.detalleOperaContables?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to detalleOperaContableService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getDetalleOperaContableIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getDetalleOperaContableIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
