import {UserGuard} from './../guards/user.guard';
import {AdminGuard} from './../guards/admin.guard';
import {LogInComponent} from './../components/auth/log-in/log-in.component';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {LogInGuard} from "../guards/log-in.guard";
import {adminRoutes} from './admin-routes';
import {userRoutes} from './user-routes';
import {SignUpComponent} from "../components/auth/sign-up/sign-up.component";


export const routes: Routes = [
  {
    path: '',
    component: LogInComponent,
    canActivate: [LogInGuard]
  },
  {
    path: 'sign-up',
    component: SignUpComponent,
    canActivate: [LogInGuard]
  },
  {
    path: 'admin',
    children: adminRoutes,
    canActivate: [AdminGuard]
  },
  {
    path: 'user',
    children: userRoutes,
    canActivate: [UserGuard]
  },
  {
    path: '**',
    redirectTo: '/'
  }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
