import { Component, EventEmitter, Inject, OnInit, Output, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { ConfigurationsService } from 'src/app/services/configurations.service';
import { DialogConfigurationComponent } from '../dialog-configuration/dialog-configuration.component';

@Component({
  selector: 'app-recommend',
  templateUrl: './recommend.component.html',
  styleUrls: ['./recommend.component.css']
})
export class RecommendComponent implements OnInit {

  @Output() newEvent = new EventEmitter<any>();
  form: FormGroup;
  usages = [{id: 1, usage: "usage1"}, {id: 2, usage: "usage2"}, {id: 3, usage: "usage3"}]
  characteristics = [{id: 1, characteristic: "characteristic1"}, {id: 2, characteristic: "characteristic2"}, {id: 3, characteristic: "characteristic3"}]

  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<DialogConfigurationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private configurationsService: ConfigurationsService) { 
    this.form = this.fb.group({
      minprice : [null, Validators.required],
      maxprice : [null, Validators.required],
      usagesSelect: [null, Validators.required],
      characteristicsSelect: [[]],
      sliderValue: [0]
    });
  }

  ngOnInit(): void {
    this.configurationsService.getUsages().subscribe(
      result => {
        this.usages = result;
        console.log(this.usages)
      }
    );
    this.configurationsService.getCharacteristics().subscribe(
      result => {
        this.characteristics = result;
        console.log(this.characteristics)
      }
    );
  }

  onSliderChange(event: any) {
    console.log(event.value)
    this.form.controls["sliderValue"].patchValue(event.value);
  }

  onChangeUsages(event: any) {
    console.log(this.form.controls.usagesSelect)
    console.log(event)
  }

  onChangeCharacteristics(event: any) {
    console.log(this.form.controls.characteristicsSelect)
    console.log(event)
  }

  submit = () => {
    console.log(this.form.value.minprice)
    console.log(this.form.value.maxprice)
    console.log(this.form.value.usagesSelect)
    console.log(this.form.value.characteristicsSelect)
    console.log(this.form.value.sliderValue)
    let input = {
      "budget": {
        "maxPrice": this.form.value.maxprice,
        "minPrice": this.form.value.minprice
      },
      "configurationCharacteristicType": this.form.value.characteristicsSelect,
      "configurationUsageType": this.form.value.usagesSelect,
      "mobility": {
        "mobility": this.form.value.sliderValue
      }
    }
    console.log(input)
    /*let input = {
      "budget": {
        "maxPrice": 100000,
        "minPrice": 2000
      },
      "configurationCharacteristicType": [
        {
          "characteristic": "Presentations",
          "id": 1
        }
      ],
      "configurationUsageType": {
        "id": 4,
        "usage": "Software Development"
      },
      "mobility": {
        "mobility": 90
      }
    }*/
    this.newEvent.emit(input);
  }    

  closeDialog() {
    this.dialogRef.close();
  }


}
