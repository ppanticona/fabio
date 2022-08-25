import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { HistoricoCajaService } from '../service/historico-caja.service';

import { HistoricoCajaComponent } from './historico-caja.component';

describe('HistoricoCaja Management Component', () => {
  let comp: HistoricoCajaComponent;
  let fixture: ComponentFixture<HistoricoCajaComponent>;
  let service: HistoricoCajaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'historico-caja', component: HistoricoCajaComponent }]), HttpClientTestingModule],
      declarations: [HistoricoCajaComponent],
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
      .overrideTemplate(HistoricoCajaComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(HistoricoCajaComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(HistoricoCajaService);

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
    expect(comp.historicoCajas?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to historicoCajaService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getHistoricoCajaIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getHistoricoCajaIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
