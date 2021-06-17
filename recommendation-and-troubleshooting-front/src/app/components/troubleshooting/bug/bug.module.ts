import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CreateBugComponent} from './create-bug/create-bug.component';
import {UpdateBugComponent} from './update-bug/update-bug.component';
import {ViewDeleteBugComponent} from './view-delete-bug/view-delete-bug.component';


@NgModule({
  declarations: [
    CreateBugComponent,
    UpdateBugComponent,
    ViewDeleteBugComponent
  ],
  imports: [
    CommonModule
  ]
})
export class BugModule {
}
