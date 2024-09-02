import { HttpClient, HttpResponse } from '@angular/common/http';
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

  addReview(data: any): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${this.apiUrl}`, data, {
      headers: this.authService.authHeader(),
      observe: 'response'
    });
  }

  deleteReview(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`, {
      headers: this.authService.authHeader(),
      observe: 'response'
    });
  }

  showReview(id: number): Observable<HttpResponse<any>> {
    return this.http.put<any>(`${this.apiUrl}/show/${id}`,{}, {
      headers: this.authService.authHeader(),
    });
  }

  hideReview(id: number): Observable<HttpResponse<any>> {
    return this.http.put<any>(`${this.apiUrl}/hide/${id}`,{}, {
      headers: this.authService.authHeader(),
    });
  }
}
