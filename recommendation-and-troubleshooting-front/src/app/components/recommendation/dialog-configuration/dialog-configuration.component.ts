import { Inject, Input, Output, EventEmitter, ViewEncapsulation } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { ConfigurationsService } from 'src/app/services/configurations.service';

@Component({
  selector: 'app-dialog-configuration',
  templateUrl: './dialog-configuration.component.html',
  styleUrls: ['./dialog-configuration.component.css']
})
export class DialogConfigurationComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DialogConfigurationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private configurationsService: ConfigurationsService) { }

  favoriteLabel = this.data.favorite ? "Favorited" : "Favorite";
  usersPerGrade = {ones: 0, twos: 0, threes: 0, fours: 0, fives: 0};

  ngOnInit(): void {
    this.configurationsService.getUsersByRate(this.data.id).subscribe(
      result => {
        this.usersPerGrade = result;
        console.log("success")
        console.log(result)
      }
    );
  }

  onValChange(checked: any) {
    console.log(checked)
    this.favoriteLabel = checked ? "Favorited" : "Favorite";
    let dto = {
      "configId": this.data.id,
      "value": checked
    }
    this.configurationsService.favorite(dto).subscribe(
      result => {
        console.log("success")
        console.log(result)
      }
    );
  }

  onRatingSet(rating: number): void {
    console.log(`User set rating to ${rating}`);
    let dto = {
      "configId": this.data.id,
      "rate": rating
    }
    this.configurationsService.rate(dto).subscribe(
      result => {
        console.log("success")
        console.log(result)
      }
    );
  }

}
