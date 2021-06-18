import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TroubleshootComponent} from './troubleshoot/troubleshoot.component';
import {BugFrequencyComponent} from './bug-frequency/bug-frequency.component';
import {UnsolvedBugsComponent} from './unsolved-bugs/unsolved-bugs.component';
import {ComputerStateComponent} from './computer-state/computer-state.component';
import { BugComponent } from './bug/bug.component';
import { SolutionComponent } from './solution/solution.component';
import { DescriptionComponent } from './description/description.component';


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
    CommonModule
  ]
})
export class TroubleshootingModule {
}
