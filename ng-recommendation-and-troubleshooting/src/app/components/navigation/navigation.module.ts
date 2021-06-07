import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AdminNavigationComponent} from './admin-navigation/admin-navigation.component';
import {UserNavigationComponent} from './user-navigation/user-navigation.component';
import {NavigationComponent} from "./navigation.component";
import {NotLoggedInComponent} from './not-logged-in/not-logged-in.component';


@NgModule({
  declarations: [
    NavigationComponent,
    AdminNavigationComponent,
    UserNavigationComponent,
    NotLoggedInComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    NavigationComponent,
    AdminNavigationComponent,
    UserNavigationComponent
  ],
})
export class NavigationModule {
}
