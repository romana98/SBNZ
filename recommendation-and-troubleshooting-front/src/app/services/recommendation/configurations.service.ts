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

  getRecommended(payload: any): Observable<any> {
    return this.http.post('http://localhost:8080/recommendation/by-page?page=' + payload.page + '&size=' + payload.size, payload.value)
  }

  rate(payload: any): Observable<any> {
    return this.http.post('http://localhost:8080/rating', payload)
  }

  favorite(payload: any): Observable<any> {
    return this.http.post('http://localhost:8080/favorite', payload)
  }

  getCurrentlyPopular(payload: any): Observable<any> {
    return this.http.get('http://localhost:8080/recommendation/getCurrentlyPopular/by-page?page=' + payload.page + '&size=' + payload.size)
  }

  getIntervalPopular(payload: any): Observable<any> {
    return this.http.post('http://localhost:8080/recommendation/getIntervalPopular/by-page?page=' + payload.page + '&size=' + payload.size, payload.value)
  }

  searchByRate(payload: any): Observable<any> {
    return this.http.post('http://localhost:8080/recommendation/searchByRate/by-page?page=' + payload.page + '&size=' + payload.size, payload.value)
  }

  getFavorites(payload: any): Observable<any> {
    return this.http.get('http://localhost:8080/recommendation/favorites/by-page?page=' + payload.page + '&size=' + payload.size)
  }

  addConfiguration(payload: any): Observable<any> {
    return this.http.post('http://localhost:8080/recommendation/add-configuration', payload)
  }

  deleteConfiguration(payload: any): Observable<any> {
    return this.http.get('http://localhost:8080/recommendation/delete-configuration/' + payload)
  }

  getUsersByRate(payload: any): Observable<any> {
    return this.http.get('http://localhost:8080/recommendation/get-users-by-rate/' + payload)
  }

  getUsages(): Observable<any> {
    return this.http.get('http://localhost:8080/recommendation/getUsages')
  }

  getCharacteristics(): Observable<any> {
    return this.http.get('http://localhost:8080/recommendation/getCharacteristics')
  }

  getRequirements(payload: any): Observable<any> {
    return this.http.get('http://localhost:8080/requirements/by-page?page=' + payload.page + '&size=' + payload.size)
  }

  addUsage(payload: any): Observable<any> {
    return this.http.post('http://localhost:8080/requirements/addUsage', payload)
  }

  addCharacteristic(payload: any): Observable<any> {
    return this.http.post('http://localhost:8080/requirements/addCharacteristic', payload)
  }

  deleteRequirement(payload: any): Observable<any> {
    return this.http.post('http://localhost:8080/requirements/deleteRequirement', payload)
  }

  addNewUsage(payload: any): Observable<any> {
    return this.http.post('http://localhost:8080/requirements/addNewUsage', payload)
  }

  addNewCharacteristic(payload: any): Observable<any> {
    return this.http.post('http://localhost:8080/requirements/addNewCharacteristic', payload)
  }

}






