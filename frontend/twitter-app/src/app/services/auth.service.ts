import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private AUTH_URL = 'http://localhost:9001/api/v1.0/tweets'

  constructor(private http: HttpClient) { }

  register(data: any): Observable<string> {
    const requestOptions: Object = {
      /* other options here */
      responseType: 'json'
    }
    return this.http.post<any>(`${this.AUTH_URL}/register`, data, requestOptions)
      .pipe(catchError((error) => [error.error]));
  }

  login(data: any): Observable<any> {
    const requestOptions: Object = {
      /* other options here */
      responseType: 'json'
    }
    return this.http.post<any>(`${this.AUTH_URL}/login`, data, requestOptions)
      .pipe(catchError((error) => [error.error]));
  }

  logout() {
    localStorage.clear();
  }

  getToken(): boolean {
    if (localStorage.getItem('loginToken')) {
      return true;
    }
    return false;
  }

  resetPassword(username: string, password: string): Observable<any> {
    //let username: string = localStorage.getItem("username")!;
    return this.http.post<any>(`${this.AUTH_URL}/reset-password/${username}`, password)
      .pipe(catchError((error) => [error.error]))
  }
}
