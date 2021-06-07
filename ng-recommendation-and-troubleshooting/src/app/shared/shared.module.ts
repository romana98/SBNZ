import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PaginationComponent} from './pagination/pagination.component';
import {TableComponent} from "./table/table.component";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";


@NgModule({
  declarations: [
    TableComponent,
    PaginationComponent
  ],
  imports: [
    CommonModule,
    MatPaginatorModule,
    MatTableModule,
  ],
  exports: [
    TableComponent,
    PaginationComponent
  ]
})
export class SharedModule {
}
