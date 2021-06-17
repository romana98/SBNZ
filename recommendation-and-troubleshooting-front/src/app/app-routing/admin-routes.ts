import {Routes} from "@angular/router";
import {AdminGuard} from "../guards/admin.guard";
import {ViewDeleteBugComponent} from "../components/troubleshooting/bug/view-delete-bug/view-delete-bug.component";
import {ViewDeleteDescriptionComponent} from "../components/troubleshooting/description/view-delete-description/view-delete-description.component";
import {AuthGuard} from "../guards/auth.guard";
import {ViewProfileComponent} from "../components/users/view-profile/view-profile.component";
import {ViewDeleteSolutionComponent} from "../components/troubleshooting/solution/view-delete-solution/view-delete-solution.component";
import {MainPageComponent} from "../components/users/main-page/main-page.component";

export const adminRoutes: Routes = [
  {
    path: '',
    component: MainPageComponent,
    canActivate: [AuthGuard]
  },

  {
    path: 'view-profile',
    component: ViewProfileComponent,
    canActivate: [AuthGuard]
  },

  {
    path: 'troubleshooting',
    children: [
      {
        path: '',
        component: ViewDeleteBugComponent,
        canActivate: [AdminGuard]
      },

      {
        path: 'descriptions',
        component: ViewDeleteDescriptionComponent,
        canActivate: [AdminGuard]
      },

      {
        path: 'solutions',
        component: ViewDeleteSolutionComponent,
        canActivate: [AdminGuard]
      },
    ]
  },

  {
    path: 'recommendation',
    children: []
  }


];
