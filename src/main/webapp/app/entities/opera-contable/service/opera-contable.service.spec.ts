import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IOperaContable } from '../opera-contable.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../opera-contable.test-samples';

import { OperaContableService, RestOperaContable } from './opera-contable.service';

const requireRestSample: RestOperaContable = {
  ...sampleWithRequiredData,
  fecCrea: sampleWithRequiredData.fecCrea?.toJSON(),
  fecModif: sampleWithRequiredData.fecModif?.toJSON(),
};

describe('OperaContable Service', () => {
  let service: OperaContableService;
  let httpMock: HttpTestingController;
  let expectedResult: IOperaContable | IOperaContable[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(OperaContableService);
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

    it('should create a OperaContable', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const operaContable = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(operaContable).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OperaContable', () => {
      const operaContable = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(operaContable).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OperaContable', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OperaContable', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a OperaContable', () => {
      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addOperaContableToCollectionIfMissing', () => {
      it('should add a OperaContable to an empty array', () => {
        const operaContable: IOperaContable = sampleWithRequiredData;
        expectedResult = service.addOperaContableToCollectionIfMissing([], operaContable);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(operaContable);
      });

      it('should not add a OperaContable to an array that contains it', () => {
        const operaContable: IOperaContable = sampleWithRequiredData;
        const operaContableCollection: IOperaContable[] = [
          {
            ...operaContable,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addOperaContableToCollectionIfMissing(operaContableCollection, operaContable);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OperaContable to an array that doesn't contain it", () => {
        const operaContable: IOperaContable = sampleWithRequiredData;
        const operaContableCollection: IOperaContable[] = [sampleWithPartialData];
        expectedResult = service.addOperaContableToCollectionIfMissing(operaContableCollection, operaContable);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(operaContable);
      });

      it('should add only unique OperaContable to an array', () => {
        const operaContableArray: IOperaContable[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const operaContableCollection: IOperaContable[] = [sampleWithRequiredData];
        expectedResult = service.addOperaContableToCollectionIfMissing(operaContableCollection, ...operaContableArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const operaContable: IOperaContable = sampleWithRequiredData;
        const operaContable2: IOperaContable = sampleWithPartialData;
        expectedResult = service.addOperaContableToCollectionIfMissing([], operaContable, operaContable2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(operaContable);
        expect(expectedResult).toContain(operaContable2);
      });

      it('should accept null and undefined values', () => {
        const operaContable: IOperaContable = sampleWithRequiredData;
        expectedResult = service.addOperaContableToCollectionIfMissing([], null, operaContable, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(operaContable);
      });

      it('should return initial array if no OperaContable is added', () => {
        const operaContableCollection: IOperaContable[] = [sampleWithRequiredData];
        expectedResult = service.addOperaContableToCollectionIfMissing(operaContableCollection, undefined, null);
        expect(expectedResult).toEqual(operaContableCollection);
      });
    });

    describe('compareOperaContable', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareOperaContable(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareOperaContable(entity1, entity2);
        const compareResult2 = service.compareOperaContable(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareOperaContable(entity1, entity2);
        const compareResult2 = service.compareOperaContable(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareOperaContable(entity1, entity2);
        const compareResult2 = service.compareOperaContable(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
