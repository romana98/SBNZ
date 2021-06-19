import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUdageCharacteristicComponent } from './add-udage-characteristic.component';

describe('AddUdageCharacteristicComponent', () => {
  let component: AddUdageCharacteristicComponent;
  let fixture: ComponentFixture<AddUdageCharacteristicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddUdageCharacteristicComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddUdageCharacteristicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
