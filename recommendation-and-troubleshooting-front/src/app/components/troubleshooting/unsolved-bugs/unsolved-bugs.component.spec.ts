import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnsolvedBugsComponent } from './unsolved-bugs.component';

describe('UnsolvedBugsComponent', () => {
  let component: UnsolvedBugsComponent;
  let fixture: ComponentFixture<UnsolvedBugsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnsolvedBugsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnsolvedBugsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
