import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConfigurationsComponent } from './configurations/configurations.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatDividerModule } from '@angular/material/divider';
import { MatPaginatorModule } from '@angular/material/paginator';
import { DialogConfigurationComponent } from './dialog-configuration/dialog-configuration.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { NgxStarsModule } from 'ngx-stars';
import { RecommendComponent } from './recommend/recommend.component';
import {MatSliderModule} from '@angular/material/slider'; 
import {MatSelectModule} from '@angular/material/select'; 
import {ReactiveFormsModule} from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { AddConfigurationComponent } from './add-configuration/add-configuration.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { AddRequirementComponent } from './add-requirement/add-requirement.component';
import { RequirementsComponent } from './requirements/requirements.component';
import { AddUdageCharacteristicComponent } from './add-udage-characteristic/add-udage-characteristic.component'; 

@NgModule({
  declarations: [
    ConfigurationsComponent,
    DialogConfigurationComponent,
    RecommendComponent,
    AddConfigurationComponent,
    AddRequirementComponent,
    RequirementsComponent,
    AddUdageCharacteristicComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    MatDividerModule,
    MatDialogModule,
    MatButtonToggleModule,
    NgxStarsModule,
    MatSliderModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule,
    MatCheckboxModule
  ]
})
export class RecommendationModule { }
