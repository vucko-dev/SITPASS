import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

const API_URL = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post(API_URL + 'auth/login', { email, password }).pipe(
      tap((response: any) => {
        if (response.accessToken) {
          localStorage.setItem('user', JSON.stringify(response));
          
          // console.log(localStorage.getItem('user'));
        }
      })
    );
  }

  logout(): void {
    localStorage.removeItem('user');
  }

  register(email: string, password: string, firstName: string, lastName: string, phoneNumber: string, address: string, city: string, zipCode: string, birthday:string ): Observable<any> {
    return this.http.post(API_URL + 'requests', {
      email,
      password,
      firstName,
      lastName,
      phoneNumber,
      address,
      city,
      zipCode,
      birthday,
      enabled: true,
    });
  }


  getCurrentUser(): any {
    return JSON.parse(localStorage.getItem('user')!);
  }

  isUserLoggedIn(): boolean {
    return this.getCurrentUser() != null;
  }

  authHeader(): HttpHeaders {
    const user = this.getCurrentUser();
    if (user && user.accessToken) {
      return new HttpHeaders({ Authorization: 'Bearer ' + user.accessToken });
    } else {
      return new HttpHeaders();
    }
  }
}
