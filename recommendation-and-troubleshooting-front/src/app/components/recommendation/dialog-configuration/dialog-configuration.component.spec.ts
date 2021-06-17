import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogConfigurationComponent } from './dialog-configuration.component';

describe('DialogConfigurationComponent', () => {
  let component: DialogConfigurationComponent;
  let fixture: ComponentFixture<DialogConfigurationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogConfigurationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
