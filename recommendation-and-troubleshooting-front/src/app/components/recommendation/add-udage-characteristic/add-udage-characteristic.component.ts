import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfigurationsService } from 'src/app/services/recommendation/configurations.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-udage-characteristic',
  templateUrl: './add-udage-characteristic.component.html',
  styleUrls: ['./add-udage-characteristic.component.css']
})
export class AddUdageCharacteristicComponent implements OnInit {

  formAddUsageReq: FormGroup;
  formAddCharReq: FormGroup;

  constructor(private fb: FormBuilder, private configurationsService: ConfigurationsService, public snackBar: MatSnackBar) {
    this.formAddUsageReq = this.fb.group({
      value: [null, Validators.required]
    });
    this.formAddCharReq = this.fb.group({
      value: [null, Validators.required]
    });
   }

  ngOnInit(): void {
  }

  submitFormAddUsageReq() {
    let usage = {
      "value": this.formAddUsageReq.value.value,
    }
    this.configurationsService.addNewUsage(usage).subscribe(
      result => {
        this.snackBar.open('Successfully added usage!', 'Ok', { duration: 2000 });
      },
      error => {
        this.snackBar.open('Something went wrong. Try again!', 'Ok', { duration: 2000 });
      }
    );
  }

  submitFormAddCharReq() {
    let char = {
      "value": this.formAddCharReq.value.value,
    }
    this.configurationsService.addNewCharacteristic(char).subscribe(
      result => {
        this.snackBar.open('Successfully added characteristic!', 'Ok', { duration: 2000 });
      },
      error => {
        this.snackBar.open('Something went wrong. Try again!', 'Ok', { duration: 2000 });
      }
    );
  }

}
