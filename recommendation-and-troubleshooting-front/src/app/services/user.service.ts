import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserModel} from "../model/user-model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getProfile(id: String): Observable<UserModel> {
    return this.http.get<UserModel>('http://localhost:8080/administrators/' + id)
  }

  editProfile(user: UserModel): Observable<UserModel> {
    return this.http.put<UserModel>('http://localhost:8080/administrators',
      user);
  }
}
