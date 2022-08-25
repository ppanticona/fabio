import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ProveedorService } from '../service/proveedor.service';

import { ProveedorComponent } from './proveedor.component';

describe('Proveedor Management Component', () => {
  let comp: ProveedorComponent;
  let fixture: ComponentFixture<ProveedorComponent>;
  let service: ProveedorService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'proveedor', component: ProveedorComponent }]), HttpClientTestingModule],
      declarations: [ProveedorComponent],
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
      .overrideTemplate(ProveedorComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ProveedorComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ProveedorService);

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
    expect(comp.proveedors?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to proveedorService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getProveedorIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getProveedorIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
