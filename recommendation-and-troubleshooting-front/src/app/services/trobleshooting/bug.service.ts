import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BugModel} from "../../model/bug-model";

@Injectable({
  providedIn: 'root'
})
export class BugService {

  constructor(private http: HttpClient) {
  }

  getBug(id: number): Observable<BugModel> {
    return this.http.get<BugModel>('http://localhost:8080/bugs/' + id)
  }

  getBugs(page: number, size: number): Observable<any> {
    return this.http.get('http://localhost:8080/bugs/by-page?page=' + page + '&size=' + size)
  }

  createBug(bug: BugModel): Observable<BugModel> {
    return this.http.post<BugModel>('http://localhost:8080/bugs', bug)
  }

  deleteBug(id: number): Observable<String> {
    return this.http.delete<String>('http://localhost:8080/bugs/' + id)
  }

  updateBug(bug: BugModel): Observable<BugModel> {
    return this.http.put<BugModel>('http://localhost:8080/bugs', bug)
  }
}
