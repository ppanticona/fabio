jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { HistoricoCajaService } from '../service/historico-caja.service';

import { HistoricoCajaDeleteDialogComponent } from './historico-caja-delete-dialog.component';

describe('HistoricoCaja Management Delete Component', () => {
  let comp: HistoricoCajaDeleteDialogComponent;
  let fixture: ComponentFixture<HistoricoCajaDeleteDialogComponent>;
  let service: HistoricoCajaService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [HistoricoCajaDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(HistoricoCajaDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(HistoricoCajaDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(HistoricoCajaService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete('ABC');
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith('ABC');
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      })
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
