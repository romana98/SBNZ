import {Routes} from "@angular/router";
import {MainPageComponent} from "../components/users/main-page/main-page.component";
import {AuthGuard} from "../guards/auth.guard";
import {ViewProfileComponent} from "../components/users/view-profile/view-profile.component";
import {BugComponent} from "../components/troubleshooting/bug/bug.component";
import {AdminGuard} from "../guards/admin.guard";
import {DescriptionComponent} from "../components/troubleshooting/description/description.component";
import {SolutionComponent} from "../components/troubleshooting/solution/solution.component";
import {AddConfigurationComponent} from "../components/recommendation/add-configuration/add-configuration.component";
import {ConfigurationsComponent} from "../components/recommendation/configurations/configurations.component";
import { RequirementsComponent } from "../components/recommendation/requirements/requirements.component";
import { AddRequirementComponent } from "../components/recommendation/add-requirement/add-requirement.component";

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
    children: [

      {
        path: 'add-configuration',
        component: AddConfigurationComponent,
        canActivate: [AdminGuard]
      },
      {
        path: 'configurations',
        component: ConfigurationsComponent,
        canActivate: [AdminGuard]
      },
      {
        path: 'requirements',
        component: RequirementsComponent,
        canActivate: [AdminGuard]
      },
      {
        path: 'add-requirement',
        component: AddRequirementComponent,
        canActivate: [AdminGuard]
      },
    ]
  }

];
