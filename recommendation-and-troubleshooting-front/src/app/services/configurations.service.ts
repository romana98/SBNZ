import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationsService {

  constructor(private http: HttpClient) { }
  
  getConfigurations(payload: any): Observable<any> {
    return this.http.get('http://localhost:8080/recommendation/all/by-page?page=' + payload.page + '&size=' + payload.size)
  }

}






