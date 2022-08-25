import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LibDiarioDetailComponent } from './lib-diario-detail.component';

describe('LibDiario Management Detail Component', () => {
  let comp: LibDiarioDetailComponent;
  let fixture: ComponentFixture<LibDiarioDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LibDiarioDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ libDiario: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(LibDiarioDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(LibDiarioDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load libDiario on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.libDiario).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
