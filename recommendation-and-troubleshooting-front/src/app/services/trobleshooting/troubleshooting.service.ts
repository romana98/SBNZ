import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProblemModel} from "../../model/problem-model";
import {BugsModel} from "../../model/bugs-model";
import {WarningModel} from "../../model/warning-model";

@Injectable({
  providedIn: 'root'
})
export class TroubleshootingService {

  constructor(private http: HttpClient) {

  }

  troubleshoot(problem: ProblemModel): Observable<ProblemModel> {
    return this.http.post<ProblemModel>('http://localhost:8080/troubleshooting', problem)
  }

  saveTroubleshoot(problem: ProblemModel): Observable<ProblemModel> {
    return this.http.put<ProblemModel>('http://localhost:8080/troubleshooting', problem)
  }

  getFrequentBugs(): Observable<BugsModel> {
    return this.http.get<BugsModel>('http://localhost:8080/troubleshooting/bug-frequency')
  }

  getUnsolvedBugs(): Observable<BugsModel> {
    return this.http.get<BugsModel>('http://localhost:8080/troubleshooting/unsolved-bugs')
  }

  getComputerState(): Observable<WarningModel[]> {
    return this.http.get<WarningModel[]>('http://localhost:8080/troubleshooting/computer-state')
  }
}
