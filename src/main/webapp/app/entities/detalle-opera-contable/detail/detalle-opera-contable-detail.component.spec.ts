import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DetalleOperaContableDetailComponent } from './detalle-opera-contable-detail.component';

describe('DetalleOperaContable Management Detail Component', () => {
  let comp: DetalleOperaContableDetailComponent;
  let fixture: ComponentFixture<DetalleOperaContableDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetalleOperaContableDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ detalleOperaContable: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(DetalleOperaContableDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DetalleOperaContableDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load detalleOperaContable on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.detalleOperaContable).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
