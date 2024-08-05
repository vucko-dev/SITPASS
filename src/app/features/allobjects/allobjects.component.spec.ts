import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllobjectsComponent } from './allobjects.component';

describe('AllobjectsComponent', () => {
  let component: AllobjectsComponent;
  let fixture: ComponentFixture<AllobjectsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllobjectsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllobjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
