import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDeleteDescriptionComponent } from './view-delete-description.component';

describe('ViewDeleteDescriptionComponent', () => {
  let component: ViewDeleteDescriptionComponent;
  let fixture: ComponentFixture<ViewDeleteDescriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewDeleteDescriptionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewDeleteDescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
