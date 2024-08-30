import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  private apiUrl = 'http://localhost:8080/exercise';

  constructor(private http: HttpClient, private authService:AuthService) {}

  getExercisesForUser(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}`, { headers: this.authService.authHeader() });
  }

  getExercisesCountForSpecificFacility(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`, { headers: this.authService.authHeader() });
  }

  addExercise(data: any): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${this.apiUrl}`, data, {
      headers: this.authService.authHeader(),
      observe: 'response'
    });
  }
}
