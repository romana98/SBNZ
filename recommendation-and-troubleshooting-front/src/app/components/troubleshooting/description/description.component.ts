import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, FormGroupDirective, Validators} from "@angular/forms";
import {DescriptionService} from "../../../services/trobleshooting/description.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DescriptionModel} from "../../../model/description-model";

@Component({
  selector: 'app-description',
  templateUrl: './description.component.html',
  styleUrls: ['./description.component.css']
})
export class DescriptionComponent implements OnInit {

  // @ts-ignore
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective;

  page = 0;
  pageSize = 5;
  descriptions = {content: [], numberOfElements: 0, totalElements: 0, totalPages: 0, number: 0};

  form: FormGroup;
  formEdit: FormGroup;
  isHidden = true;

  constructor(private fb: FormBuilder,
              private descriptionService: DescriptionService,
              public snackBar: MatSnackBar) {

    this.form = this.fb.group({
      id: [null, Validators.required],
      problemDescription: [null, Validators.required]
    });
    this.formEdit = this.fb.group({
      id: [null, Validators.required],
      problemDescription: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.getDescriptions();
  }

  onPagination(page: number) {
    this.page = page;
    this.getDescriptions();
  }

  addDescription() {
    this.descriptionService.createDescription(new DescriptionModel(this.form.controls['id'].value, this.form.controls['problemDescription'].value)).toPromise().then(() => {
      this.snackBar.open("Successfully created description", "Close",{duration: 2000} );
      this.getDescriptions();
      setTimeout(() => this.formGroupDirective.resetForm(), 0);
    }, () => {
      this.snackBar.open("Description already exists.", "Close",{duration: 2000} );
    })
  }

  deleteDescription(id: number) {
    this.descriptionService.deleteDescription(id).toPromise().then(() => {
      this.snackBar.open("Successfully deleted description", "Close",{duration: 2000} );
      this.getDescriptions();
    }, () => {
      this.snackBar.open("Description is part of a bug.", "Close",{duration: 2000} );
    })
  }

  editDescription() {
    this.isHidden = true;
    this.descriptionService.updateDescription(new DescriptionModel(this.formEdit.controls['id'].value, this.formEdit.controls['problemDescription'].value)).toPromise().then(() => {
      this.snackBar.open("Successfully updated description", "Close",{duration: 2000} );
      this.isHidden = true;
      this.getDescriptions();
    }, () => {
      this.snackBar.open("Description already exists.", "Close",{duration: 2000} );
    })
  }

  editModeOn(id: number) {
    this.descriptionService.getDescription(id).toPromise().then(res => {
      this.formEdit.patchValue({
        id: res.id,
        problemDescription: res.problemDescription
      });
      this.isHidden = false;
    }, () => {
      this.snackBar.open("Description with id doesn't exists.", "Close",{duration: 2000} );
    })
  }

  getDescriptions(){
    this.descriptionService.getDescriptions(this.page, this.pageSize).toPromise().then(res => {
      this.descriptions = res;
    }, err => {
      this.snackBar.open("Server error: " + err, "Close",{duration: 2000} );
    })
  }

}
