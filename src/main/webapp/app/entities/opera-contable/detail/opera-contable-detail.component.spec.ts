import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OperaContableDetailComponent } from './opera-contable-detail.component';

describe('OperaContable Management Detail Component', () => {
  let comp: OperaContableDetailComponent;
  let fixture: ComponentFixture<OperaContableDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OperaContableDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ operaContable: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(OperaContableDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OperaContableDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load operaContable on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.operaContable).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
