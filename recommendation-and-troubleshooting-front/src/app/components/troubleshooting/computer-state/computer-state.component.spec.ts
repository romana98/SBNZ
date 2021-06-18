import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComputerStateComponent } from './computer-state.component';

describe('ComputerStateComponent', () => {
  let component: ComputerStateComponent;
  let fixture: ComponentFixture<ComputerStateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ComputerStateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ComputerStateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
