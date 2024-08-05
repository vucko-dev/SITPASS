import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailedObjectComponent } from './detailed-object.component';

describe('DetailedObjectComponent', () => {
  let component: DetailedObjectComponent;
  let fixture: ComponentFixture<DetailedObjectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailedObjectComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailedObjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
