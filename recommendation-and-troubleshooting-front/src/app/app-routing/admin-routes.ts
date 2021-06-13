import { TableComponent } from './../shared/table/table.component';
import { Routes } from "@angular/router";
import { AdminGuard } from "../guards/admin.guard";

export const adminRoutes: Routes = [
  {
    path: '', // path: 'view-profile',
    component: TableComponent,
    canActivate: [AdminGuard]
  },
];
