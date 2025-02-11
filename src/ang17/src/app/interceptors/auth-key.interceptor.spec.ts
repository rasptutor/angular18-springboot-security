import { TestBed } from '@angular/core/testing';

import { authKeyInterceptor } from './auth-key.interceptor';

describe('AuthKeyInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      authKeyInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: authKeyInterceptor = TestBed.inject(authKeyInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
