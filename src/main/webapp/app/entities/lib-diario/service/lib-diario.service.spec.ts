import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ILibDiario } from '../lib-diario.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../lib-diario.test-samples';

import { LibDiarioService, RestLibDiario } from './lib-diario.service';

const requireRestSample: RestLibDiario = {
  ...sampleWithRequiredData,
  fecContable: sampleWithRequiredData.fecContable?.toJSON(),
  fecVenc: sampleWithRequiredData.fecVenc?.toJSON(),
  fecOpeEmi: sampleWithRequiredData.fecOpeEmi?.toJSON(),
  fecCrea: sampleWithRequiredData.fecCrea?.toJSON(),
  fecModif: sampleWithRequiredData.fecModif?.toJSON(),
};

describe('LibDiario Service', () => {
  let service: LibDiarioService;
  let httpMock: HttpTestingController;
  let expectedResult: ILibDiario | ILibDiario[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(LibDiarioService);
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

    it('should create a LibDiario', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const libDiario = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(libDiario).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a LibDiario', () => {
      const libDiario = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(libDiario).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a LibDiario', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of LibDiario', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a LibDiario', () => {
      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addLibDiarioToCollectionIfMissing', () => {
      it('should add a LibDiario to an empty array', () => {
        const libDiario: ILibDiario = sampleWithRequiredData;
        expectedResult = service.addLibDiarioToCollectionIfMissing([], libDiario);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(libDiario);
      });

      it('should not add a LibDiario to an array that contains it', () => {
        const libDiario: ILibDiario = sampleWithRequiredData;
        const libDiarioCollection: ILibDiario[] = [
          {
            ...libDiario,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addLibDiarioToCollectionIfMissing(libDiarioCollection, libDiario);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a LibDiario to an array that doesn't contain it", () => {
        const libDiario: ILibDiario = sampleWithRequiredData;
        const libDiarioCollection: ILibDiario[] = [sampleWithPartialData];
        expectedResult = service.addLibDiarioToCollectionIfMissing(libDiarioCollection, libDiario);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(libDiario);
      });

      it('should add only unique LibDiario to an array', () => {
        const libDiarioArray: ILibDiario[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const libDiarioCollection: ILibDiario[] = [sampleWithRequiredData];
        expectedResult = service.addLibDiarioToCollectionIfMissing(libDiarioCollection, ...libDiarioArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const libDiario: ILibDiario = sampleWithRequiredData;
        const libDiario2: ILibDiario = sampleWithPartialData;
        expectedResult = service.addLibDiarioToCollectionIfMissing([], libDiario, libDiario2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(libDiario);
        expect(expectedResult).toContain(libDiario2);
      });

      it('should accept null and undefined values', () => {
        const libDiario: ILibDiario = sampleWithRequiredData;
        expectedResult = service.addLibDiarioToCollectionIfMissing([], null, libDiario, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(libDiario);
      });

      it('should return initial array if no LibDiario is added', () => {
        const libDiarioCollection: ILibDiario[] = [sampleWithRequiredData];
        expectedResult = service.addLibDiarioToCollectionIfMissing(libDiarioCollection, undefined, null);
        expect(expectedResult).toEqual(libDiarioCollection);
      });
    });

    describe('compareLibDiario', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareLibDiario(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareLibDiario(entity1, entity2);
        const compareResult2 = service.compareLibDiario(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareLibDiario(entity1, entity2);
        const compareResult2 = service.compareLibDiario(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareLibDiario(entity1, entity2);
        const compareResult2 = service.compareLibDiario(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
