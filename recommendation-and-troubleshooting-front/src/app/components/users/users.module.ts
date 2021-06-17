import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ViewProfileComponent } from './view-profile/view-profile.component';
import { MainPageComponent } from './main-page/main-page.component';
import {MatDividerModule} from "@angular/material/divider";
import {MatButtonModule} from "@angular/material/button";
import {RouterModule} from "@angular/router";



@NgModule({
  declarations: [
    ViewProfileComponent,
    MainPageComponent
  ],
  imports: [
    CommonModule,
    MatDividerModule,
    MatButtonModule,
    RouterModule
  ]
})
export class UsersModule { }
