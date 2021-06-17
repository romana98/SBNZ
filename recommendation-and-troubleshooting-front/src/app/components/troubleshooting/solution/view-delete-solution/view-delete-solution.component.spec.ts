import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDeleteSolutionComponent } from './view-delete-solution.component';

describe('ViewDeleteSolutionComponent', () => {
  let component: ViewDeleteSolutionComponent;
  let fixture: ComponentFixture<ViewDeleteSolutionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewDeleteSolutionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewDeleteSolutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
