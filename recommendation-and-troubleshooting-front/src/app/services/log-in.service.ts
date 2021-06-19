import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { StorageService } from "./storage.service";
import { Observable } from "rxjs";
import { LogIn, UserRole } from "../model/user-role";
import {UserModel} from "../model/user-model";

@Injectable({
  providedIn: 'root'
})
export class LogInService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient, private storageService: StorageService
  ) {
  }

  logIn(auth: LogIn): Observable<any> {
    return this.http.post('http://localhost:8080/auth/log-in',
      auth, { headers: this.headers, responseType: 'json' });
  }

  activateAccount(id: number): Observable<any> {
    return this.http.post('http://localhost:8080/auth/activate' + id, {});
  }

  signUp(user: UserModel): Observable<UserModel> {
    return this.http.post<UserModel>('http://localhost:8080/auth/sign-up', user);
  }

  logOut(): void {
    this.storageService.clearStorage();
  }

  getRole(): UserRole {
    if (!localStorage.getItem('user')) {
      return UserRole.UNAUTHORIZED;
    }
    return JSON.parse(<string>localStorage.getItem('user')).role === 'ROLE_ADMINISTRATOR' ?
      UserRole.ROLE_ADMINISTRATOR : (JSON.parse(<string>localStorage.getItem('user')).role === 'ROLE_USER' ? UserRole.ROLE_USER : UserRole.UNAUTHORIZED);
  }
}
