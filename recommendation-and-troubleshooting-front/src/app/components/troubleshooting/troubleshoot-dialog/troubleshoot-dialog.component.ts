import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TroubleshootingService} from "../../../services/trobleshooting/troubleshooting.service";
import {ProblemModel} from "../../../model/problem-model";
import {SolutionModel} from "../../../model/solution-model";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-troubleshoot-dialog',
  templateUrl: './troubleshoot-dialog.component.html',
  styleUrls: ['./troubleshoot-dialog.component.css']
})
export class TroubleshootDialogComponent implements OnInit {

  solution: String = "";
  problem: ProblemModel = new ProblemModel(new SolutionModel(-1, ""), [], [], false);

  constructor(public dialogRef: MatDialogRef<TroubleshootDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: { descriptions: [] },
              private troubleshootingService: TroubleshootingService,
              public snackBar: MatSnackBar,
  ) {
  }

  ngOnInit(): void {
    this.problem.problems = this.data.descriptions;
    this.troubleshootingService.troubleshoot(this.problem).toPromise().then(res => {
      this.problem = res;
      if(res.currentSolution != null)
        this.solution = res.currentSolution.solution;
    }, () => {
      this.snackBar.open("Error while finding solution.", "Close", {duration: 2000});
    })
  }

  yes() {
    this.troubleshootingService.saveTroubleshoot(this.problem).toPromise().then(() => {
      this.snackBar.open("Bug saved to history.", "Close", {duration: 2000});
      this.dialogRef.close();
    }, () => {
      this.snackBar.open("Error while saving bug to history.", "Close", {duration: 2000});
    })
  }

  no() {
    this.problem.solutionWorked = false;
    this.troubleshootingService.troubleshoot(this.problem).toPromise().then(res => {
      this.problem = res;
      if(res.currentSolution != null)
        this.solution = res.currentSolution.solution;
    }, () => {
      this.snackBar.open("Error while finding solution.", "Close", {duration: 2000});
    })

  }
}
