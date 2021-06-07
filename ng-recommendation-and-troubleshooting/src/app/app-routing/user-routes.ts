import {Routes} from "@angular/router";
import {UserGuard} from "../guards/user.guard";
import {ViewProfileComponent} from "../components/users/view-profile/view-profile.component";

export const userRoutes: Routes = [
  {
    path: '', // path: 'view-profile',
    component: ViewProfileComponent,
    canActivate: [UserGuard]
  },
];
