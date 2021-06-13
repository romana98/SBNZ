import { TableComponent } from './../shared/table/table.component';
import { Routes } from "@angular/router";
import { UserGuard } from "../guards/user.guard";
import { ConfigurationsComponent } from '../components/recommendation/configurations/configurations.component';

export const userRoutes: Routes = [
  {
    path: '', // path: 'view-profile',
    component: TableComponent,
    canActivate: [UserGuard]
  },
  {
    path: 'configurations', 
    component: ConfigurationsComponent,
    canActivate: [UserGuard]
  },
];
