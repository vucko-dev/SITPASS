import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { catchError, switchMap, tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/user';
  private checkLogin = 'http://localhost:8080/auth/login';

  constructor(private http: HttpClient, private authService:AuthService) {}

  getUserInfo(): Observable<any> {
    return this.http.get(this.apiUrl, { headers: this.authService.authHeader() })
  }

  async getUserRole(): Promise<string> {
    const data = await this.getUserInfo().toPromise();
    return data.roles[0]?.name || '';
  }

  updateUser(userData: any): Observable<any> {
    return this.http.put(`${this.apiUrl}`, userData, { headers: this.authService.authHeader() });
  }

  updateUserPassword(email:String, password:String, userData: any): Observable<any> {
    return this.http.post(this.checkLogin, { email, password },{ headers: this.authService.authHeader() } ).pipe(
      switchMap((response: any) => {
        if (response.accessToken) {
          return this.http.put(`${this.apiUrl}/password`, userData, { headers: this.authService.authHeader() });
        } else {
          // Handle case where the accessToken is not present (e.g., return an empty observable or throw an error)
          return throwError(() => new Error('No access token in response'));
        }
      })
    );
  }

  changeImage(file: File): Observable<any> {
    const formData: FormData = new FormData();

    formData.append('file', file);
    return this.http.put(`${this.apiUrl}/image`, formData, {
      headers: this.authService.authHeader(),
      observe: 'response'
    });
  }

  

  getUserInfoByUserId(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`, { headers: this.authService.authHeader() });
  }


  getAllUsers(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/all`, { headers: this.authService.authHeader() });
  }



}
