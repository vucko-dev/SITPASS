import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DisciplineService {
  private apiUrl = 'http://localhost:8080/disciplines';

  constructor(private http: HttpClient, private authService:AuthService) {}

  getDisciplines(): Observable<any> {
    return this.http.get(this.apiUrl, { headers: this.authService.authHeader() })
  }
}
