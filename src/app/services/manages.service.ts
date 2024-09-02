import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ManagesService {

  private apiUrl = 'http://localhost:8080/manages';

  constructor(private http: HttpClient, private authService:AuthService) {}

  hasRights(id:number): Observable<HttpResponse<any>>{
    return this.http.get<any>(`${this.apiUrl}/${id}`,  { 
      headers: this.authService.authHeader(),
      observe: 'response'
    });
  }

  hasRightsByUserIdAndFacilityId(userId:number, facilityId:number): Observable<HttpResponse<any>>{
    return this.http.get<any>(`${this.apiUrl}/${userId}/${facilityId}`,  { 
      headers: this.authService.authHeader(),
      observe: 'response'
    });
  }


  getManagesByUser(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}`, { headers: this.authService.authHeader() });
  }

  addManages(data: any): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${this.apiUrl}`, data, {
      headers: this.authService.authHeader(),
      observe: 'response'
    });
  }

  deleteManages(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`, {
      headers: this.authService.authHeader(),
      observe: 'response'
    });
  }
}
