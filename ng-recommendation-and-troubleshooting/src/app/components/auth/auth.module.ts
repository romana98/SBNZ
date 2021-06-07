import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LogInComponent} from "./log-in/log-in.component";
import {SignUpComponent} from "./sign-up/sign-up.component";
import {ActivateAccountComponent} from "./activate-account/activate-account.component";
import {MaterialModule} from "../../shared/material.module";
import {FormBuilder, FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    LogInComponent,
    SignUpComponent,
    ActivateAccountComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule
  ],
  exports: [
    LogInComponent,
    SignUpComponent,
    ActivateAccountComponent
  ]
})
export class AuthModule {
}
