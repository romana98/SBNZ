import { TableComponent } from './../shared/table/table.component';
import { Routes } from "@angular/router";
import { AdminGuard } from "../guards/admin.guard";
import { AddConfigurationComponent } from '../components/recommendation/add-configuration/add-configuration.component';
import { ConfigurationsComponent } from '../components/recommendation/configurations/configurations.component';

export const adminRoutes: Routes = [
  {
    path: '', // path: 'view-profile',
    component: TableComponent,
    canActivate: [AdminGuard]
  },
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
];
