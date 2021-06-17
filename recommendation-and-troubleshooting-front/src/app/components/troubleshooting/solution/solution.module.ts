import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateSolutionComponent } from './create-solution/create-solution.component';
import { ViewDeleteSolutionComponent } from './view-delete-solution/view-delete-solution.component';
import { UpdateSolutionComponent } from './update-solution/update-solution.component';



@NgModule({
  declarations: [
    CreateSolutionComponent,
    ViewDeleteSolutionComponent,
    UpdateSolutionComponent
  ],
  imports: [
    CommonModule
  ]
})
export class SolutionModule { }
