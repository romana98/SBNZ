import { Component, OnInit } from '@angular/core';
import { ConfigurationsService } from 'src/app/services/recommendation/configurations.service';
import { FormBuilder, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-configuration',
  templateUrl: './add-configuration.component.html',
  styleUrls: ['./add-configuration.component.css']
})
export class AddConfigurationComponent implements OnInit {

  form: FormGroup;
  types = ["DESKTOP", "LAPTOP"];
  discTypes = ["SSD", "HDD"];

  constructor(private fb: FormBuilder,  private configurationsService: ConfigurationsService, public snackBar: MatSnackBar) {
    this.form = this.fb.group({
      price : [null, Validators.required],
      type : [null, Validators.required],
      cpu: [null, Validators.required],
      gpu: [null, Validators.required],
      ram: [null, Validators.required],
      os: [null, Validators.required],
      psu: [null, Validators.required],
      discType: [null, Validators.required],
      discSize: [null, Validators.required],
      motherboard: [null, Validators.required],
      screenSize: [null, Validators.required],
      screenResolution: [null, Validators.required],
      musicCard: [null, Validators.required],
      touchscreen: [false, Validators.required],
      microphone: [false, Validators.required],
      camera: [false, Validators.required],
      ergonomic: [false, Validators.required],
    });
   }

  ngOnInit(): void {
  }

  onChangeType(event: any) {
  }

  onChangeDiscType(event: any) {
  }

  changedCheckbox(key: string, event: any) {
    this.form.controls[key].patchValue(event.checked);
  }

  submit = () => {
    let input = {
      "camera": this.form.value.camera,
      "cpu": this.form.value.cpu,
      "discSize": this.form.value.discSize,
      "discType": this.form.value.discType,
      "ergonomic": this.form.value.ergonomic,
      "gpu": this.form.value.gpu,
      "microphone": this.form.value.microphone,
      "motherboard": this.form.value.motherboard,
      "musicCard": this.form.value.musicCard,
      "os": this.form.value.os,
      "price": parseInt(this.form.value.price),
      "psu": this.form.value.psu,
      "ram": this.form.value.ram,
      "screenResolution": this.form.value.screenResolution,
      "screenSize": this.form.value.screenSize,
      "touchscreen": this.form.value.touchscreen,
      "type": this.form.value.type
    }
    this.configurationsService.addConfiguration(input).subscribe(
      result => {
        this.snackBar.open('Successfully added configuration!', 'Ok', { duration: 2000 });
      },
      error => {
        this.snackBar.open('Something went wrong. Try again!', 'Ok', { duration: 2000 });
      }
    );

  }

}
