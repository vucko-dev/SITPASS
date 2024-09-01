import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FacilityService {

  private apiUrl = 'http://localhost:8080/facility';

  constructor(private http: HttpClient, private authService:AuthService) {}

  getFacilities(): Observable<any> {
    return this.http.get(this.apiUrl, { headers: this.authService.authHeader() })
  }


  getFacilityById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`, { headers: this.authService.authHeader() });
  }

  getFacilityByCity(city: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/city/${city}`, { headers: this.authService.authHeader() });
  }

  deleteFacilityById(id:number): Observable<HttpResponse<any>>{
    return this.http.delete<any>(`${this.apiUrl}/${id}`,  { 
      headers: this.authService.authHeader(),
      observe: 'response'
    });
  }

  updateFacility(id:number, data:any): Observable<HttpResponse<any>>{
    return this.http.put<any>(`${this.apiUrl}/${id}`,data,{
      headers: this.authService.authHeader(),
      observe: 'response'
    })
  };

  addImages(id: number, files: File[]): Observable<any> {
    const formData: FormData = new FormData();

    files.forEach(file => {
      formData.append('file', file);
    });

    return this.http.post(`${this.apiUrl}/image/${id}`, formData, {
      headers: this.authService.authHeader(),
      observe: 'response'
    });
  }

}
