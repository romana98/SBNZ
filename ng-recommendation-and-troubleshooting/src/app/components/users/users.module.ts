import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ViewProfileComponent} from "./view-profile/view-profile.component";


@NgModule({
  declarations: [
    ViewProfileComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    ViewProfileComponent
  ]
})
export class UsersModule {
}
