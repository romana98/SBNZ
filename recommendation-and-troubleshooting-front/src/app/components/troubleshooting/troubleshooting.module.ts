import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TroubleshootComponent} from './troubleshoot/troubleshoot.component';
import {BugFrequencyComponent} from './bug-frequency/bug-frequency.component';
import {UnsolvedBugsComponent} from './unsolved-bugs/unsolved-bugs.component';
import {ComputerStateComponent} from './computer-state/computer-state.component';
import {BugModule} from "./bug/bug.module";
import {DescriptionModule} from "./description/description.module";
import {SolutionModule} from "./solution/solution.module";


@NgModule({
  declarations: [
    TroubleshootComponent,
    BugFrequencyComponent,
    UnsolvedBugsComponent,
    ComputerStateComponent
  ],
  imports: [
    CommonModule,
    BugModule,
    DescriptionModule,
    SolutionModule,
  ],
  exports: [
    BugModule,
    DescriptionModule,
    SolutionModule
  ]
})
export class TroubleshootingModule {
}
