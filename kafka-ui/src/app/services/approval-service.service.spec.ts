import { TestBed } from '@angular/core/testing';

import { ApprovalServiceService } from './approval-service.service';

describe('ApprovalServiceService', () => {
  let service: ApprovalServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApprovalServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
