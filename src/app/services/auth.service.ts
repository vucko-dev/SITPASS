import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

const API_URL = 'http://localhost:8080/auth/';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post(API_URL + 'login', { email, password }).pipe(
      tap((response: any) => {
        if (response.accessToken) {
          localStorage.setItem('user', JSON.stringify(response));
        }
      })
    );
  }

  logout(): void {
    localStorage.removeItem('user');
  }

  register(email: string, password: string, firstName: string, lastName: string, jmbg: string, phoneNumber: string, address: string): Observable<any> {
    return this.http.post(API_URL + 'signup', {
      email,
      password,
      firstName,
      lastName,
      jmbg,
      phoneNumber,
      address,
      enabled: true,
    });
  }

  registerSeller(email: string, password: string, firstName: string, lastName: string, jmbg: string, phoneNumber: string, address: string): Observable<any> {
    return this.http.post(
      'http://localhost:8080/seller/register',
      {
        email,
        password,
        firstName,
        lastName,
        jmbg,
        phoneNumber,
        address,
        enabled: true,
      },
      { headers: this.authHeader() }
    );
  }

  getCurrentUser(): any {
    return JSON.parse(localStorage.getItem('user')!);
  }

  isUserLoggedIn(): boolean {
    // return true;
    return this.getCurrentUser() != null;
  }

  private authHeader(): HttpHeaders {
    const user = this.getCurrentUser();
    if (user && user.accessToken) {
      return new HttpHeaders({ Authorization: 'Bearer ' + user.accessToken });
    } else {
      return new HttpHeaders();
    }
  }
}
