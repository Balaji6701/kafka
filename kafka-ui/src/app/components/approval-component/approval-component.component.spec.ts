import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovalComponentComponent } from './approval-component.component';

describe('ApprovalComponentComponent', () => {
  let component: ApprovalComponentComponent;
  let fixture: ComponentFixture<ApprovalComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovalComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApprovalComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
