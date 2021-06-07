import { TableComponent } from './../shared/table/table.component';
import { Routes } from "@angular/router";
import { UserGuard } from "../guards/user.guard";

export const userRoutes: Routes = [
  {
    path: '', // path: 'view-profile',
    component: TableComponent,
    canActivate: [UserGuard]
  },
];
