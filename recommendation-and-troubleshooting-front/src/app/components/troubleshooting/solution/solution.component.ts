import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, FormGroupDirective, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SolutionService} from "../../../services/trobleshooting/solution.service";
import {SolutionModel} from "../../../model/solution-model";

@Component({
  selector: 'app-solution',
  templateUrl: './solution.component.html',
  styleUrls: ['./solution.component.css']
})
export class SolutionComponent implements OnInit {
  // @ts-ignore
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective;

  page = 0;
  pageSize = 5;
  solutions = {content: [], numberOfElements: 0, totalElements: 0, totalPages: 0, number: 0};

  form: FormGroup;
  formEdit: FormGroup;
  isHidden = true;

  constructor(private fb: FormBuilder,
              private solutionService: SolutionService,
              public snackBar: MatSnackBar) {

    this.form = this.fb.group({
      id: [null, Validators.required],
      solution: [null, Validators.required]
    });
    this.formEdit = this.fb.group({
      id: [null, Validators.required],
      solution: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.getSolutions();
  }

  onPagination(page: number) {
    this.page = page;
    this.getSolutions();
  }

  addSolution() {
    this.solutionService.createSolution(new SolutionModel(this.form.controls['id'].value, this.form.controls['solution'].value)).toPromise().then(() => {
      this.snackBar.open("Successfully created solution", "Close",{duration: 2000} );
      this.getSolutions();
      setTimeout(() => this.formGroupDirective.resetForm(), 0);
    }, () => {
      this.snackBar.open("Solution already exists.", "Close",{duration: 2000} );
    })
  }

  deleteSolution(id: number) {
    this.solutionService.deleteSolution(id).toPromise().then(() => {
      this.snackBar.open("Successfully deleted solution", "Close",{duration: 2000} );
      this.getSolutions();
    }, () => {
      this.snackBar.open("Solution is part of a bug.", "Close",{duration: 2000} );
    })
  }

  editSolution() {
    this.isHidden = true;
    this.solutionService.updateSolution(new SolutionModel(this.formEdit.controls['id'].value, this.formEdit.controls['solution'].value)).toPromise().then(() => {
      this.snackBar.open("Successfully updated solution", "Close",{duration: 2000} );
      this.isHidden = true;
      this.getSolutions();
    }, () => {
      this.snackBar.open("Solution already exists.", "Close",{duration: 2000} );
    })
  }

  editModeOn(id: number) {
    this.solutionService.getSolution(id).toPromise().then(res => {
      this.formEdit.patchValue({
        id: res.id,
        solution: res.solution
      });
      this.isHidden = false;
    }, () => {
      this.snackBar.open("Solution with id doesn't exists.", "Close",{duration: 2000} );
    })
  }

  getSolutions(){
    this.solutionService.getSolutions(this.page, this.pageSize).toPromise().then(res => {
      this.solutions = res;
    }, err => {
      this.snackBar.open("Server error: " + err, "Close",{duration: 2000} );
    })
  }

}
