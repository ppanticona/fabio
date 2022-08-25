import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { LibDiarioService } from '../service/lib-diario.service';

import { LibDiarioComponent } from './lib-diario.component';

describe('LibDiario Management Component', () => {
  let comp: LibDiarioComponent;
  let fixture: ComponentFixture<LibDiarioComponent>;
  let service: LibDiarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'lib-diario', component: LibDiarioComponent }]), HttpClientTestingModule],
      declarations: [LibDiarioComponent],
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
      .overrideTemplate(LibDiarioComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(LibDiarioComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(LibDiarioService);

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
    expect(comp.libDiarios?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to libDiarioService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getLibDiarioIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getLibDiarioIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
