import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {StorageService} from "./storage.service";
import {Observable} from "rxjs";
import {LogIn, UserRole} from "../model/user-role";

@Injectable({
  providedIn: 'root'
})
export class LogInService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient, private storageService: StorageService
  ) {
  }

  logIn(auth: LogIn): Observable<any> {
    return this.http.post('http://localhost:8084/auth/log-in',
      auth, {headers: this.headers, responseType: 'json'});
  }

  logOut(): void {
    this.storageService.clearStorage();
  }

  getRole(): UserRole {
    if (!localStorage.getItem('user')) {
      return UserRole.UNAUTHORIZED;
    }
    return JSON.parse(<string>localStorage.getItem('user')).role === 'ADMIN' ?
      UserRole.ADMIN : (JSON.parse(<string>localStorage.getItem('user')).role === 'USER' ? UserRole.USER : UserRole.UNAUTHORIZED);
  }
}
