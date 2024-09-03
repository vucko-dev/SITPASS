import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccRequestService {
  private apiUrl = 'http://localhost:8080/requests';

  constructor(private http: HttpClient, private authService:AuthService) {}

  getAllRequests(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}`, { headers: this.authService.authHeader() });
  }

  acceptRequest(id:number): Observable<HttpResponse<any>>{
    return this.http.put<any>(`${this.apiUrl}/${id}`,{status:'ACCEPTED'},{
      headers: this.authService.authHeader(),
      observe: 'response'
    })
  };


  rejectRequest(id:number): Observable<HttpResponse<any>>{
    return this.http.put<any>(`${this.apiUrl}/${id}`,{status:'REJECTED'},{
      headers: this.authService.authHeader(),
      observe: 'response'
    })
  };
}
