import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BugFrequencyComponent } from './bug-frequency.component';

describe('BugFrequencyComponent', () => {
  let component: BugFrequencyComponent;
  let fixture: ComponentFixture<BugFrequencyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BugFrequencyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BugFrequencyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
