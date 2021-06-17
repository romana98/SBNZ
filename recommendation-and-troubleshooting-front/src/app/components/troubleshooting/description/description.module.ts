import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateDescriptionComponent } from './create-description/create-description.component';
import { UpdateDescriptionComponent } from './update-description/update-description.component';
import { ViewDeleteDescriptionComponent } from './view-delete-description/view-delete-description.component';



@NgModule({
  declarations: [
    CreateDescriptionComponent,
    UpdateDescriptionComponent,
    ViewDeleteDescriptionComponent
  ],
  imports: [
    CommonModule
  ]
})
export class DescriptionModule { }
