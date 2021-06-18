import {Component, OnInit} from '@angular/core';
import {TroubleshootingService} from "../../../services/trobleshooting/troubleshooting.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {WarningModel} from "../../../model/warning-model";

@Component({
  selector: 'app-computer-state',
  templateUrl: './computer-state.component.html',
  styleUrls: ['./computer-state.component.css']
})
export class ComputerStateComponent implements OnInit {

  message: String = ''
  messages: WarningModel[] = []

  constructor(private troubleshootingService: TroubleshootingService,
              public snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
  }

  getState() {
    this.message = '';
    this.troubleshootingService.getComputerState().toPromise().then(res => {
      this.messages = res;
      if (this.messages.length === 0)
        this.message = 'Healthy!';
    }, () => {
      this.snackBar.open("Error while getting computerState", "Close", {duration: 2000});
    })
  }
}
