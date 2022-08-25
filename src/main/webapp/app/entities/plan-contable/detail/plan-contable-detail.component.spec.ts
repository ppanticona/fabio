import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlanContableDetailComponent } from './plan-contable-detail.component';

describe('PlanContable Management Detail Component', () => {
  let comp: PlanContableDetailComponent;
  let fixture: ComponentFixture<PlanContableDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PlanContableDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ planContable: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(PlanContableDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(PlanContableDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load planContable on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.planContable).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
