import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDeleteBugComponent } from './view-delete-bug.component';

describe('ViewDeleteBugComponent', () => {
  let component: ViewDeleteBugComponent;
  let fixture: ComponentFixture<ViewDeleteBugComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewDeleteBugComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewDeleteBugComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
