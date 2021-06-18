import {Routes} from "@angular/router";
import {MainPageComponent} from "../components/users/main-page/main-page.component";
import {AuthGuard} from "../guards/auth.guard";
import {ViewProfileComponent} from "../components/users/view-profile/view-profile.component";
import {ConfigurationsComponent} from "../components/recommendation/configurations/configurations.component";
import {UserGuard} from "../guards/user.guard";
import {TroubleshootComponent} from "../components/troubleshooting/troubleshoot/troubleshoot.component";
import {AdminGuard} from "../guards/admin.guard";
import {BugFrequencyComponent} from "../components/troubleshooting/bug-frequency/bug-frequency.component";
import {UnsolvedBugsComponent} from "../components/troubleshooting/unsolved-bugs/unsolved-bugs.component";
import {ComputerStateComponent} from "../components/troubleshooting/computer-state/computer-state.component";


export const userRoutes: Routes = [
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
    path: 'recommendation',
    children: [
      {
        path: '',
        component: ConfigurationsComponent,
        canActivate: [UserGuard]
      },
    ]
  },
  {
    path: 'troubleshooting',
    children: [
      {
        path: 'troubleshoot',
        component: TroubleshootComponent,
        canActivate: [AdminGuard]
      },

      {
        path: 'frequent-bugs',
        component: BugFrequencyComponent,
        canActivate: [AdminGuard]
      },

      {
        path: 'unsolved-bugs',
        component: UnsolvedBugsComponent,
        canActivate: [AdminGuard]
      },

      {
        path: 'computer-state',
        component: ComputerStateComponent,
        canActivate: [AdminGuard]
      }
    ]
  }
];
