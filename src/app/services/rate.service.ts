import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RateService {
  private apiUrl = 'http://localhost:8080/rate';

  constructor(private http: HttpClient, private authService:AuthService) {}

  getTotalEquipment(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/equipment/${id}`, { headers: this.authService.authHeader() });
  }

  getTotalStaff(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/staff/${id}`, { headers: this.authService.authHeader() });
  }

  getTotalSpace(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/space/${id}`, { headers: this.authService.authHeader() });
  }

  getTotalHygiene(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/hygiene/${id}`, { headers: this.authService.authHeader() });
  }
}
