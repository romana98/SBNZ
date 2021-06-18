import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {UserModel} from "../../model/user-model";
import {HttpClient} from "@angular/common/http";
import {SolutionModel} from "../../model/solution-model";

@Injectable({
  providedIn: 'root'
})
export class SolutionService {

  constructor(private http: HttpClient) { }

  getSolution(id: number): Observable<SolutionModel> {
    return this.http.get<SolutionModel>('http://localhost:8080/solutions/' + id)
  }

  getSolutions(page: number, size: number): Observable<any> {
    return this.http.get('http://localhost:8080/solutions/by-page?page=' + page + '&size=' + size)
  }

  getAllSolutions(): Observable<any> {
    return this.http.get('http://localhost:8080/solutions/')
  }

  createSolution(solution: SolutionModel): Observable<SolutionModel> {
    return this.http.post<SolutionModel>('http://localhost:8080/solutions', solution)
  }

  deleteSolution(id: number): Observable<String> {
    return this.http.delete<String>('http://localhost:8080/solutions/' + id)
  }

  updateSolution(solution: SolutionModel): Observable<SolutionModel> {
    return this.http.put<SolutionModel>('http://localhost:8080/solutions', solution)
  }
}
