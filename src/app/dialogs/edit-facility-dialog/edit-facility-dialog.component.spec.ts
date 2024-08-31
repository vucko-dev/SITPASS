import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditFacilityDialogComponent } from './edit-facility-dialog.component';

describe('EditFacilityDialogComponent', () => {
  let component: EditFacilityDialogComponent;
  let fixture: ComponentFixture<EditFacilityDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditFacilityDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditFacilityDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
