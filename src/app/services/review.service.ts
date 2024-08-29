import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private apiUrl = 'http://localhost:8080/review';

  constructor(private http: HttpClient, private authService:AuthService) {}

  getReviewsByFacilityId(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/facility/${id}`, { headers: this.authService.authHeader() });
  }

  getReviewsByUser(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}`, { headers: this.authService.authHeader() });
  }

  getReviewsCountByFacilityId(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/facility/count/${id}`, { headers: this.authService.authHeader() });
  }
}
