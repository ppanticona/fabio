import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IHistoricoCaja } from '../historico-caja.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../historico-caja.test-samples';

import { HistoricoCajaService, RestHistoricoCaja } from './historico-caja.service';

const requireRestSample: RestHistoricoCaja = {
  ...sampleWithRequiredData,
  fecIniVig: sampleWithRequiredData.fecIniVig?.toJSON(),
  fecFinVig: sampleWithRequiredData.fecFinVig?.toJSON(),
  fecCrea: sampleWithRequiredData.fecCrea?.toJSON(),
  fecModif: sampleWithRequiredData.fecModif?.toJSON(),
};

describe('HistoricoCaja Service', () => {
  let service: HistoricoCajaService;
  let httpMock: HttpTestingController;
  let expectedResult: IHistoricoCaja | IHistoricoCaja[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(HistoricoCajaService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find('ABC').subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a HistoricoCaja', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const historicoCaja = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(historicoCaja).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a HistoricoCaja', () => {
      const historicoCaja = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(historicoCaja).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a HistoricoCaja', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of HistoricoCaja', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a HistoricoCaja', () => {
      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addHistoricoCajaToCollectionIfMissing', () => {
      it('should add a HistoricoCaja to an empty array', () => {
        const historicoCaja: IHistoricoCaja = sampleWithRequiredData;
        expectedResult = service.addHistoricoCajaToCollectionIfMissing([], historicoCaja);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(historicoCaja);
      });

      it('should not add a HistoricoCaja to an array that contains it', () => {
        const historicoCaja: IHistoricoCaja = sampleWithRequiredData;
        const historicoCajaCollection: IHistoricoCaja[] = [
          {
            ...historicoCaja,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addHistoricoCajaToCollectionIfMissing(historicoCajaCollection, historicoCaja);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a HistoricoCaja to an array that doesn't contain it", () => {
        const historicoCaja: IHistoricoCaja = sampleWithRequiredData;
        const historicoCajaCollection: IHistoricoCaja[] = [sampleWithPartialData];
        expectedResult = service.addHistoricoCajaToCollectionIfMissing(historicoCajaCollection, historicoCaja);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(historicoCaja);
      });

      it('should add only unique HistoricoCaja to an array', () => {
        const historicoCajaArray: IHistoricoCaja[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const historicoCajaCollection: IHistoricoCaja[] = [sampleWithRequiredData];
        expectedResult = service.addHistoricoCajaToCollectionIfMissing(historicoCajaCollection, ...historicoCajaArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const historicoCaja: IHistoricoCaja = sampleWithRequiredData;
        const historicoCaja2: IHistoricoCaja = sampleWithPartialData;
        expectedResult = service.addHistoricoCajaToCollectionIfMissing([], historicoCaja, historicoCaja2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(historicoCaja);
        expect(expectedResult).toContain(historicoCaja2);
      });

      it('should accept null and undefined values', () => {
        const historicoCaja: IHistoricoCaja = sampleWithRequiredData;
        expectedResult = service.addHistoricoCajaToCollectionIfMissing([], null, historicoCaja, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(historicoCaja);
      });

      it('should return initial array if no HistoricoCaja is added', () => {
        const historicoCajaCollection: IHistoricoCaja[] = [sampleWithRequiredData];
        expectedResult = service.addHistoricoCajaToCollectionIfMissing(historicoCajaCollection, undefined, null);
        expect(expectedResult).toEqual(historicoCajaCollection);
      });
    });

    describe('compareHistoricoCaja', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareHistoricoCaja(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareHistoricoCaja(entity1, entity2);
        const compareResult2 = service.compareHistoricoCaja(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareHistoricoCaja(entity1, entity2);
        const compareResult2 = service.compareHistoricoCaja(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareHistoricoCaja(entity1, entity2);
        const compareResult2 = service.compareHistoricoCaja(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
