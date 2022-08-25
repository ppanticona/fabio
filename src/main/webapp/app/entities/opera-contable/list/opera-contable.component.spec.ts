import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { OperaContableService } from '../service/opera-contable.service';

import { OperaContableComponent } from './opera-contable.component';

describe('OperaContable Management Component', () => {
  let comp: OperaContableComponent;
  let fixture: ComponentFixture<OperaContableComponent>;
  let service: OperaContableService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'opera-contable', component: OperaContableComponent }]), HttpClientTestingModule],
      declarations: [OperaContableComponent],
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
      .overrideTemplate(OperaContableComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OperaContableComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OperaContableService);

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
    expect(comp.operaContables?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to operaContableService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getOperaContableIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getOperaContableIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
