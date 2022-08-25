import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { PlanContableService } from '../service/plan-contable.service';

import { PlanContableComponent } from './plan-contable.component';

describe('PlanContable Management Component', () => {
  let comp: PlanContableComponent;
  let fixture: ComponentFixture<PlanContableComponent>;
  let service: PlanContableService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'plan-contable', component: PlanContableComponent }]), HttpClientTestingModule],
      declarations: [PlanContableComponent],
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
      .overrideTemplate(PlanContableComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PlanContableComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(PlanContableService);

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
    expect(comp.planContables?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to planContableService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getPlanContableIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getPlanContableIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
