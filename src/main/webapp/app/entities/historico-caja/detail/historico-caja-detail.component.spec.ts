import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HistoricoCajaDetailComponent } from './historico-caja-detail.component';

describe('HistoricoCaja Management Detail Component', () => {
  let comp: HistoricoCajaDetailComponent;
  let fixture: ComponentFixture<HistoricoCajaDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HistoricoCajaDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ historicoCaja: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(HistoricoCajaDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(HistoricoCajaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load historicoCaja on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.historicoCaja).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
