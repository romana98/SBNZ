import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TroubleshootDialogComponent } from './troubleshoot-dialog.component';

describe('TroubleshootDialogComponent', () => {
  let component: TroubleshootDialogComponent;
  let fixture: ComponentFixture<TroubleshootDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TroubleshootDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TroubleshootDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
