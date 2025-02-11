import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { AuthGuard } from './auth.guard';

describe('authGuard', () => {
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        AuthGuard
        ]
    });
  });

  it('should be created', () => {
    const executeGuard: AuthGuard = TestBed.inject(AuthGuard);
    expect(executeGuard).toBeTruthy();
  });
});
