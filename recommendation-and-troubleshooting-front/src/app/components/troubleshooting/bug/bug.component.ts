import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, FormGroupDirective, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DescriptionService} from "../../../services/trobleshooting/description.service";
import {SolutionService} from "../../../services/trobleshooting/solution.service";
import {BugService} from "../../../services/trobleshooting/bug.service";
import {SolutionModel} from "../../../model/solution-model";
import {BugModel} from "../../../model/bug-model";
import {DescriptionModel} from "../../../model/description-model";

@Component({
  selector: 'app-bug',
  templateUrl: './bug.component.html',
  styleUrls: ['./bug.component.css']
})
export class BugComponent implements OnInit {
  // @ts-ignore
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective;

  page = 0;
  pageSize = 5;
  bugs = {content: [], numberOfElements: 0, totalElements: 0, totalPages: 0, number: 0};
  descriptions = [];
  solutions = []

  descriptionsEdit = [];
  solutionsEdit = []
  selectedSolutions: SolutionModel[] = []
  selectedSolutionsEdit: SolutionModel[] = []
  bug = null;

  form: FormGroup;
  formEdit: FormGroup;
  isHidden = true;

  constructor(private fb: FormBuilder,
              public snackBar: MatSnackBar,
              private descriptionService: DescriptionService,
              private solutionService: SolutionService,
              private bugService: BugService
  ) {

    this.form = this.fb.group({
      id: [null],
      descriptions: [null, Validators.required],
      solutions: [null, Validators.required]
    });
    this.formEdit = this.fb.group({
      id: [null, Validators.required],
      descriptions: [null, Validators.required],
      solutions: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.getBugs();
    this.descriptionService.getAllDescriptions().toPromise().then(res => {
      this.descriptions = res;
    }, err => {
      this.snackBar.open("Server error: " + err, "Close",{duration: 2000} );
    })

    this.solutionService.getAllSolutions().toPromise().then(res => {
      this.solutions = res;
    }, err => {
      this.snackBar.open("Server error: " + err, "Close",{duration: 2000} );
    })
  }

  onPagination(page: number) {
    this.page = page;
    this.getBugs();
  }

  addBug() {
    this.bugService.createBug(new BugModel(this.form.controls['id'].value, this.transformToMap(), this.form.controls['descriptions'].value)).toPromise().then(() => {
      this.snackBar.open("Successfully created bug", "Close",{duration: 2000} );
      this.getBugs();
      setTimeout(() => this.formGroupDirective.resetForm(), 0);
    }, () => {
      this.snackBar.open("Bug already exists.", "Close",{duration: 2000} );
    })
  }

  deleteBug(id: number) {
    this.bugService.deleteBug(id).toPromise().then(() => {
      this.snackBar.open("Successfully deleted bug", "Close",{duration: 2000} );
      this.getBugs();
    }, () => {
      this.snackBar.open("Bug with id doesn't exists.", "Close",{duration: 2000} );
    })
  }

  editBug() {
    this.isHidden = true;

    this.bugService.updateBug(new BugModel(this.formEdit.controls['id'].value, this.transformToMapEdit(), this.formEdit.controls['descriptions'].value)).toPromise().then(() => {
      this.snackBar.open("Successfully updated bug", "Close",{duration: 2000} );
      this.getBugs();
      setTimeout(() => this.formGroupDirective.resetForm(), 0);
    }, () => {
      this.snackBar.open("Bug already exists.", "Close", {duration: 2000});
    })

  }

  editModeOn(id: number) {
    this.descriptionService.getAllDescriptions().toPromise().then(res => {
      this.descriptionsEdit = res;
    }, err => {
      this.snackBar.open("Server error: " + err, "Close",{duration: 2000} );
    })
    this.solutionService.getAllSolutions().toPromise().then(res => {
      this.solutionsEdit = res;
    }, err => {
      this.snackBar.open("Server error: " + err, "Close",{duration: 2000} );
    })

    this.bugService.getBug(id).toPromise().then(res => {
      this.formEdit.patchValue({
        id: res.id,
        descriptions: this.descriptionsEdit.filter(desc =>  this.containsDescription(desc, res.descriptionDTO)),
        solutions: this.solutionsEdit.filter(solution => this.containsSolution(solution,
          Object.entries(res.solutionDTOList).map(([k, v]) => res.solutionDTOList[k])
          ))
      });
      this.selectedSolutionsEdit = this.formEdit.controls['solutions'].value;
      this.isHidden = false;
    }, () => {
      this.snackBar.open("Bug with id doesn't exists.", "Close",{duration: 2000} );
    })
  }

  getBugs() {
    this.bugService.getBugs(this.page, this.pageSize).toPromise().then(res => {
      res['content'] = res['content'].map((bug: any) => {
        return {
          'id': bug.id,
          'descriptionDTO': bug.descriptionDTO.map((d: { [x: string]: any; }) => d['problemDescription']).join(', '),
          'solutionDTOList': Object.entries(bug.solutionDTOList).map(
            ([k, v]) => bug.solutionDTOList[k]).map((x: SolutionModel) => x['solution']).join(', ')
        }
      })
      this.bugs = res;

    }, err => {
      this.snackBar.open("Server error: " + err, "Close",{duration: 2000} );
    })
  }

  selected(solution: SolutionModel) {
    if (this.selectedSolutions.indexOf(solution) != -1) {
      this.selectedSolutions = this.selectedSolutions.filter(s => s.id !== solution.id);
    } else {
      this.selectedSolutions.push(solution);
    }
  }

  selectedEdit(solution: SolutionModel) {
    if (this.selectedSolutionsEdit.indexOf(solution) != -1) {
      this.selectedSolutionsEdit = this.selectedSolutionsEdit.filter(s => s.id !== solution.id);
    } else {
      this.selectedSolutionsEdit.push(solution);
    }
  }

  transformToMap() {
    let solutionsMap = {};
    // @ts-ignore
    this.selectedSolutions.map((solution, index) => solutionsMap[index + 1] = solution);

    return solutionsMap;
  }

  transformToMapEdit() {
    let solutionsMap = {};
    // @ts-ignore
    this.selectedSolutionsEdit.map((solution, index) => solutionsMap[index + 1] = solution);

    return solutionsMap;
  }

  containsDescription(obj: DescriptionModel, list: DescriptionModel[]) {
    let i;
    for (i = 0; i < list.length; i++) {
      if (list[i].problemDescription === obj.problemDescription && list[i].id === obj.id) {
        return true;
      }
    }
    return false;
  }

  containsSolution(obj: SolutionModel, list: any[]) {
    let i;
    for (i = 0; i < list.length; i++) {
      if (list[i].solution === obj.solution && list[i].id === obj.id) {
        return true;
      }
    }
    return false;
  }
}
