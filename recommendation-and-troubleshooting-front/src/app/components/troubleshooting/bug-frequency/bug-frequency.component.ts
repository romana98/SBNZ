import {Component, OnInit} from '@angular/core';
import {TroubleshootingService} from "../../../services/trobleshooting/troubleshooting.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SolutionModel} from "../../../model/solution-model";

@Component({
  selector: 'app-bug-frequency',
  templateUrl: './bug-frequency.component.html',
  styleUrls: ['./bug-frequency.component.css']
})
export class BugFrequencyComponent implements OnInit {

  bugs = {content: []};

  constructor(
    private troubleshootingService: TroubleshootingService,
    public snackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
    this.troubleshootingService.getFrequentBugs().toPromise().then(res => {
      // @ts-ignore
      this.bugs.content = res.bugs.map((bug: any) => {
        return {
          'id': bug.id,
          'descriptionDTO': bug.descriptionDTO.map((d: { [x: string]: any; }) => d['problemDescription']).join(', '),
          'solutionDTOList': Object.entries(bug.solutionDTOList).map(
            ([k, v]) => bug.solutionDTOList[k]).map((x: SolutionModel) => x['solution']).join(', ')
        }
      })
    }, () => {
      this.snackBar.open("Error while finding solution.", "Close", {duration: 2000});
    })

  }

}
