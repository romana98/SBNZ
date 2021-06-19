import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfigurationsService } from 'src/app/services/recommendation/configurations.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-requirement',
  templateUrl: './add-requirement.component.html',
  styleUrls: ['./add-requirement.component.css']
})
export class AddRequirementComponent implements OnInit {

  formAddUsageReq: FormGroup;
  formAddCharReq: FormGroup;
  usages = [];
  characteristics = [];
  attributes = ['CPU', 'GPU', 'RAM', 'OS', 'PSU', 'discSize', 'motherboard', 'screenSize', 'screenResolution', 'musicCard'];

  constructor(private fb: FormBuilder, private configurationsService: ConfigurationsService, public snackBar: MatSnackBar) {
    this.formAddUsageReq = this.fb.group({
      usagesSelect: [null, Validators.required],
      attrsUsageSelect: [null, Validators.required],
      value: [null, Validators.required]
    });
    this.formAddCharReq = this.fb.group({
      charsSelect: [null, Validators.required],
      attrsCharSelect: [null, Validators.required],
      value: [null, Validators.required]
    });
   }

  ngOnInit(): void {
    this.configurationsService.getUsages().subscribe(
      result => {
        this.usages = result;
      }
    );
    this.configurationsService.getCharacteristics().subscribe(
      result => {
        this.characteristics = result;
      }
    );
  }

  onChangeUsages(event: any) {
  }

  onChangeChars(event: any) {
  }

  onChangeAttrsUsage(event: any) {
  }

  onChangeAttrsChars(event: any) {
  }

  submitFormAddUsageReq() {
    let usage = {
      "attribute": this.formAddUsageReq.value.attrsUsageSelect,
      "usageOrChar": this.formAddUsageReq.value.usagesSelect.usage,
      "usageOrCharId": parseInt(this.formAddUsageReq.value.usagesSelect.id),
      "value": this.formAddUsageReq.value.value,
      "type": "USAGE"
    }
    this.configurationsService.addUsage(usage).subscribe(
      result => {
        this.snackBar.open('Successfully added usage requirement!', 'Ok', { duration: 2000 });
      },
      error => {
        this.snackBar.open('Something went wrong. Try again!', 'Ok', { duration: 2000 });
      }
    );
  }

  submitFormAddCharReq() {
    let char = {
      "attribute": this.formAddCharReq.value.attrsCharSelect,
      "usageOrChar": this.formAddCharReq.value.charsSelect.characteristic,
      "usageOrCharId": parseInt(this.formAddCharReq.value.charsSelect.id),
      "value": this.formAddCharReq.value.value,
      "type": "CHARACTERISTIC"
    }
    this.configurationsService.addCharacteristic(char).subscribe(
      result => {
        this.snackBar.open('Successfully added characteristic requirement!', 'Ok', { duration: 2000 });
      },
      error => {
        this.snackBar.open('Something went wrong. Try again!', 'Ok', { duration: 2000 });
      }
    );
  }

}
