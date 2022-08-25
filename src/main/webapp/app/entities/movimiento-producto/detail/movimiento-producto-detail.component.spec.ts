import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MovimientoProductoDetailComponent } from './movimiento-producto-detail.component';

describe('MovimientoProducto Management Detail Component', () => {
  let comp: MovimientoProductoDetailComponent;
  let fixture: ComponentFixture<MovimientoProductoDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovimientoProductoDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ movimientoProducto: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(MovimientoProductoDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(MovimientoProductoDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load movimientoProducto on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.movimientoProducto).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
