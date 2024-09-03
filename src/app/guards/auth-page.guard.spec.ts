// import { TestBed } from '@angular/core/testing';
// import { Router } from '@angular/router';
// import { AuthPageGuard } from './auth-page.guard';
// import { AuthService } from '../services/auth.service';
// import { RouterTestingModule } from '@angular/router/testing';

// describe('AuthPageGuard', () => {
//   let authPageGuard: AuthPageGuard;
//   let authService: AuthService;
//   let router: Router;

//   beforeEach(() => {
//     TestBed.configureTestingModule({
//       imports: [RouterTestingModule.withRoutes([])],
//       providers: [
//         AuthPageGuard,
//         { provide: AuthService, useValue: { isUserLoggedIn: () => false } },
//       ],
//     });

//     authPageGuard = TestBed.inject(AuthPageGuard);
//     authService = TestBed.inject(AuthService);
//     router = TestBed.inject(Router);
//   });

//   it('should create', () => {
//     expect(authPageGuard).toBeTruthy();
//   });

//   it('should allow the authenticated user to activate the route', () => {
//     spyOn(authService, 'isUserLoggedIn').and.returnValue(true);
//     expect(authPageGuard.canActivate(null as any, null as any)).toBe(true);
//   });

//   it('should not allow the unauthenticated user to activate the route', () => {
//     spyOn(authService, 'isUserLoggedIn').and.returnValue(false);
//     spyOn(router, 'navigate');
//     expect(authPageGuard.canActivate(null as any, null as any)).toBe(false);
//     expect(router.navigate).toHaveBeenCalledWith(['/auth']);
//   });
// });
