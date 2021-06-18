import {Routes} from "@angular/router";
import {AdminGuard} from "../guards/admin.guard";
import {AuthGuard} from "../guards/auth.guard";
import {ViewProfileComponent} from "../components/users/view-profile/view-profile.component";
import {MainPageComponent} from "../components/users/main-page/main-page.component";
import {BugComponent} from "../components/troubleshooting/bug/bug.component";
import {DescriptionComponent} from "../components/troubleshooting/description/description.component";
import {SolutionComponent} from "../components/troubleshooting/solution/solution.component";

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
        component: BugComponent,
        canActivate: [AdminGuard]
      },

      {
        path: 'descriptions',
        component: DescriptionComponent,
        canActivate: [AdminGuard]
      },

      {
        path: 'solutions',
        component: SolutionComponent,
        canActivate: [AdminGuard]
      },
    ]
  },

  {
    path: 'recommendation',
    children: []
  }


];
