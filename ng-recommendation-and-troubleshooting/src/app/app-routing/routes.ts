import {Routes} from "@angular/router";
import {LogInComponent} from "../components/auth/log-in/log-in.component";
import {LogInGuard} from "../guards/log-in.guard";
import {SignUpComponent} from "../components/auth/sign-up/sign-up.component";
import {ActivateAccountComponent} from "../components/auth/activate-account/activate-account.component";
import {userRoutes} from "./user-routes";
import {adminRoutes} from "./admin-routes";

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
    path: 'activateAccount',
    component: ActivateAccountComponent,
    canActivate: [LogInGuard]
  },
  {
    path: 'admin',
    children: adminRoutes
  },
  {
    path: 'user',
    children: userRoutes
  },
];
