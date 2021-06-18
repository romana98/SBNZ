import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TroubleshootComponent} from './troubleshoot/troubleshoot.component';
import {BugFrequencyComponent} from './bug-frequency/bug-frequency.component';
import {UnsolvedBugsComponent} from './unsolved-bugs/unsolved-bugs.component';
import {ComputerStateComponent} from './computer-state/computer-state.component';
import { BugComponent } from './bug/bug.component';
import { SolutionComponent } from './solution/solution.component';
import { DescriptionComponent } from './description/description.component';
import {MatDividerModule} from "@angular/material/divider";
import {MatFormFieldModule} from "@angular/material/form-field";
import {ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {SharedModule} from "../../shared/shared.module";
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatTooltipModule} from "@angular/material/tooltip";


@NgModule({
  declarations: [
    TroubleshootComponent,
    BugFrequencyComponent,
    UnsolvedBugsComponent,
    ComputerStateComponent,
    BugComponent,
    SolutionComponent,
    DescriptionComponent
  ],
  imports: [
    CommonModule,
    MatDividerModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatInputModule,
    SharedModule,
    MatOptionModule,
    MatSelectModule,
    MatTooltipModule
  ]
})
export class TroubleshootingModule {
}
