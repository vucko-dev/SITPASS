import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { AuthService } from '../services/auth.service';
import { RouterTestingModule } from '@angular/router/testing';

describe('AuthGuard', () => {
  let authGuard: AuthGuard;
  let authService: AuthService;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([])],
      providers: [
        AuthGuard,
        { provide: AuthService, useValue: { isUserLoggedIn: () => false } },
      ],
    });

    authGuard = TestBed.inject(AuthGuard);
    authService = TestBed.inject(AuthService);
    router = TestBed.inject(Router);
  });

  it('should create', () => {
    expect(authGuard).toBeTruthy();
  });

  it('should allow the authenticated user to activate the route', () => {
    spyOn(authService, 'isUserLoggedIn').and.returnValue(true);
    expect(authGuard.canActivate(null as any, null as any)).toBe(true);
  });

  it('should not allow the unauthenticated user to activate the route', () => {
    spyOn(authService, 'isUserLoggedIn').and.returnValue(false);
    spyOn(router, 'navigate');
    expect(authGuard.canActivate(null as any, null as any)).toBe(false);
    expect(router.navigate).toHaveBeenCalledWith(['/auth']);
  });
});
