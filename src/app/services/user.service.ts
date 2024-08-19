import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/user';

  constructor(private http: HttpClient, private authService:AuthService) {}

  // Get user info
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

  updateUserPassword(userData: any): Observable<any> {
    return this.http.put(`${this.apiUrl}` + "/password", userData, { headers: this.authService.authHeader() });
  }
}
