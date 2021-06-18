import { TestBed } from '@angular/core/testing';

import { TroubleshootingService } from './troubleshooting.service';

describe('TroubleshootingService', () => {
  let service: TroubleshootingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TroubleshootingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
