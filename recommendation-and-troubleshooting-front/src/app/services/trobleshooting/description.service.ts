import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DescriptionModel} from "../../model/description-model";

@Injectable({
  providedIn: 'root'
})
export class DescriptionService {

  constructor(private http: HttpClient) {
  }

  getDescription(id: number): Observable<DescriptionModel> {
    return this.http.get<DescriptionModel>('http://localhost:8080/descriptions/' + id)
  }

  getDescriptions(page: number, size: number): Observable<any> {
    return this.http.get('http://localhost:8080/descriptions/by-page?page=' + page + '&size=' + size)
  }

  getAllDescriptions(): Observable<any> {
    return this.http.get('http://localhost:8080/descriptions/')
  }

  createDescription(description: DescriptionModel): Observable<DescriptionModel> {
    return this.http.post<DescriptionModel>('http://localhost:8080/descriptions', description)
  }

  deleteDescription(id: number): Observable<String> {
    return this.http.delete<String>('http://localhost:8080/descriptions/' + id)
  }

  updateDescription(description: DescriptionModel): Observable<DescriptionModel> {
    return this.http.put<DescriptionModel>('http://localhost:8080/descriptions', description)
  }
}
