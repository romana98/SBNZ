import {Routes} from "@angular/router";
import {ViewProfileComponent} from "../components/users/view-profile/view-profile.component";
import {AdminGuard} from "../guards/admin.guard";

export const adminRoutes: Routes = [
  {
    path: '', // path: 'view-profile',
    component: ViewProfileComponent,
    canActivate: [AdminGuard]
  },
];
