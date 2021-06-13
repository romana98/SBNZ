import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConfigurationsComponent } from './configurations/configurations.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatDividerModule } from '@angular/material/divider';
import { MatPaginatorModule } from '@angular/material/paginator';



@NgModule({
  declarations: [
    ConfigurationsComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    MatDividerModule,
  ]
})
export class RecommendationModule { }
