import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, FormGroupDirective, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DescriptionService} from "../../../services/trobleshooting/description.service";
import {TroubleshootDialogComponent} from "../troubleshoot-dialog/troubleshoot-dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-troubleshoot',
  templateUrl: './troubleshoot.component.html',
  styleUrls: ['./troubleshoot.component.css']
})
export class TroubleshootComponent implements OnInit {

  // @ts-ignore
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective;

  form: FormGroup;
  descriptions = [];

  constructor(private fb: FormBuilder,
              public snackBar: MatSnackBar,
              private descriptionService: DescriptionService,
              private dialog: MatDialog
  ) {
    this.form = this.fb.group({
      descriptions: [null, Validators.required],
    });
  }

  ngOnInit(): void {
    this.descriptionService.getAllDescriptions().toPromise().then(res => {
      this.descriptions = res;
    }, err => {
      this.snackBar.open("Server error: " + err, "Close", {duration: 2000});
    })
  }

  troubleshoot() {
    const dialogRef = this.dialog.open(TroubleshootDialogComponent, {
      width: '35%',
      data: {descriptions: this.form.controls['descriptions'].value}
    });

    dialogRef.afterClosed().subscribe(() => {
      setTimeout(() => this.formGroupDirective.resetForm(), 0);
    });
  }
}
