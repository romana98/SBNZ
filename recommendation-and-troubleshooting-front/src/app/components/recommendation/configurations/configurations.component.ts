import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ConfigurationsService } from 'src/app/services/recommendation/configurations.service';
import { DialogConfigurationComponent } from '../dialog-configuration/dialog-configuration.component';
import { RecommendComponent } from '../recommend/recommend.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LogInService } from 'src/app/services/log-in.service';

@Component({
  selector: 'app-configurations',
  templateUrl: './configurations.component.html',
  styleUrls: ['./configurations.component.css']
})
export class ConfigurationsComponent implements OnInit {

  //states = ["ALL", "POPULAR", "FAVORITES", "RECOMMENDED", "INTERVAL_SEARCH", "RATE_SEARCH"];
  state = "ALL";
  input = {};
  rateInput = {};
  intervalInput = {};
  page = 0;
  pageSize = 1;
  configurations = {content: [], numberOfElements: 0, totalElements: 0, totalPages: 0, number: 0};
  formInterval: FormGroup;
  formRate: FormGroup;
  role = 0;

  constructor(private configurationsService: ConfigurationsService,
    private router: Router,
    public dialog: MatDialog,
    private fb: FormBuilder,
    public snackBar: MatSnackBar,
    private loginService: LogInService) {
    this.formInterval = this.fb.group({
      minDate : [null, Validators.required],
      maxDate : [null, Validators.required]
    });
    this.formRate = this.fb.group({
      minRate : [null, Validators.required],
      maxRate : [null, Validators.required]
    });
   }

  ngOnInit(): void {
    this.role = this.loginService.getRole();
    console.log(this.role)
    this.state = "ALL";
    this.page = 0;
    this.configurationsService.getConfigurations({ page: this.page, size: this.pageSize }).subscribe(
      result => {
        this.configurations = result;
        console.log(this.configurations.content)
      }
    );
  }

  onPagination(page: number){
    this.page = page;
    if (this.state == "ALL") {
      this.configurationsService.getConfigurations({ page: this.page, size: this.pageSize }).subscribe(
        result => {
          this.configurations = result;
          console.log(this.configurations.content)
        }
      );
    } else if (this.state == "RECOMMENDED") {
      console.log("page: " + page)
      this.configurationsService.getRecommended({ page: this.page, size: this.pageSize, value: this.input }).subscribe(
        result => {
          this.configurations = result;
          console.log(this.configurations.content)
        }
      );
    } else if (this.state == "POPULAR") {
      console.log("page: " + page)
      this.configurationsService.getCurrentlyPopular({ page: this.page, size: this.pageSize }).subscribe(
        result => {
          this.configurations = result;
          console.log(this.configurations.content)
        }
      );
    } else if (this.state == "FAVORITES") {
      console.log("page: " + page)
      this.configurationsService.getFavorites({ page: this.page, size: this.pageSize }).subscribe(
        result => {
          this.configurations = result;
          console.log(this.configurations.content)
        }
      );
    } else if (this.state == "INTERVAL_SEARCH") {
      console.log("page: " + page)
      this.configurationsService.getIntervalPopular({ page: this.page, size: this.pageSize, value: this.intervalInput }).subscribe(
        result => {
          this.configurations = result;
          console.log(this.configurations.content)
        }
      );
    } else if (this.state == "RATE_SEARCH") {
      console.log("page: " + page)
      this.configurationsService.searchByRate({ page: this.page, size: this.pageSize, value: this.rateInput }).subscribe(
        result => {
          this.configurations = result;
          console.log(this.configurations.content)
        }
      );
    }
  }

  onClick(data: any) {
    console.log(data)
    const dialogRef = this.dialog.open(DialogConfigurationComponent, {
      width: '800px',
      data: data,
    });
  }

  onClickInput(data: any) {
    console.log(data)
    const dialogRef = this.dialog.open(RecommendComponent, {
      width: '800px',
      data: data,
    });
    const dialogSubmitSubscription = dialogRef.componentInstance.newEvent.subscribe(result => {
      console.log('Got the data!', result);
      this.getRecommendation(result);
      dialogSubmitSubscription.unsubscribe();
      dialogRef.close();
  });
  }

  getRecommendation(event: any) {
    console.log(event)
    this.page = 0;
    this.state = "RECOMMENDED";
    this.input = event;
    this.configurationsService.getRecommended({ page: this.page, size: this.pageSize, value: event }).subscribe(
      result => {
        this.configurations = result;
        console.log(this.configurations.content)
        console.log(this.page)
        console.log(this.pageSize)
        this.snackBar.open('See configurations for you!', 'Ok', { duration: 2000 });
      },
      error => {
        this.snackBar.open('Something went wrong. Try again!', 'Ok', { duration: 2000 });
      }
    );
  }

  onClickPopular(event: any) {
    console.log(event)
    this.page = 0;
    this.state = "POPULAR";
    this.configurationsService.getCurrentlyPopular({ page: this.page, size: this.pageSize }).subscribe(
      result => {
        this.configurations = result;
        console.log(this.configurations.content)
      }
    );
  }

  onClickFavorites(event: any) {
    console.log(event)
    this.page = 0;
    this.state = "FAVORITES";
    this.configurationsService.getFavorites({ page: this.page, size: this.pageSize }).subscribe(
      result => {
        this.configurations = result;
        console.log(this.configurations.content)
      }
    );
  }

  submitRate() {
    console.log(this.formRate.value.minRate)
    console.log(this.formRate.value.maxRate)
    this.page = 0;
    this.state = "RATE_SEARCH";
    this.rateInput = { "minRate": this.formRate.value.minRate, "maxRate": this.formRate.value.maxRate };
    this.configurationsService.searchByRate({ page: this.page, size: this.pageSize, value: this.rateInput }).subscribe(
      result => {
        this.configurations = result;
        console.log(this.configurations.content)
      },
      error => {
        this.snackBar.open('Something went wrong. Rate should be a number.', 'Ok', { duration: 2000 });
      }
    );
  }

  onValueChanged(key: string, value: any) {
    console.log(value.target.value)
    this.formInterval.controls[key].patchValue(value.target.value.toISOString().split('T')[0]);
  }

  submitInterval() {
    if (this.formInterval.value.minDate == null) this.formInterval.controls["minDate"].patchValue("2000-01-01");
    if (this.formInterval.value.maxDate == null) this.formInterval.controls["maxDate"].patchValue("2100-01-01");
    console.log(this.formInterval.value.minDate)
    console.log(this.formInterval.value.maxDate)
    this.page = 0;
    this.state = "INERVAL_SEARCH";
    this.intervalInput = { "minDate": this.formInterval.value.minDate, "maxDate": this.formInterval.value.maxDate };
    this.configurationsService.getIntervalPopular({ page: this.page, size: this.pageSize, value: this.intervalInput }).subscribe(
      result => {
        this.configurations = result;
        console.log(this.configurations.content)
      },
      error => {
        this.snackBar.open('Something went wrong.', 'Ok', { duration: 2000 });
      }
    );
  }

  onDelete(event: any) {
    console.log(event)
    this.configurationsService.deleteConfiguration(event).subscribe(
      result => {
        console.log(result)
        this.state = "ALL";
        this.page = 0;
        this.configurationsService.getConfigurations({ page: this.page, size: this.pageSize }).subscribe(
          result => {
            this.configurations = result;
            console.log(this.configurations.content)
            this.snackBar.open('Successfully deleted configuration.', 'Ok', { duration: 2000 });
          },
          error => {
            this.snackBar.open('Something went wrong.', 'Ok', { duration: 2000 });
          }
        );
      }
    );
  }

}
