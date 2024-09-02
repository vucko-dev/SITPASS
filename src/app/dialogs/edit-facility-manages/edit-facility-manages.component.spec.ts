import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditFacilityManagesComponent } from './edit-facility-manages.component';

describe('EditFacilityManagesComponent', () => {
  let component: EditFacilityManagesComponent;
  let fixture: ComponentFixture<EditFacilityManagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditFacilityManagesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditFacilityManagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
