import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private apiUrl = 'http://localhost:8080/comment/reply/0';

  constructor(private http: HttpClient, private authService:AuthService) {}

  addReply(data: any): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${this.apiUrl}`, data, {
      headers: this.authService.authHeader(),
      observe: 'response'
    });
  }
}
