import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReplyCreatorComponent } from './reply-creator.component';

describe('ReplyCreatorComponent', () => {
  let component: ReplyCreatorComponent;
  let fixture: ComponentFixture<ReplyCreatorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReplyCreatorComponent]
    });
    fixture = TestBed.createComponent(ReplyCreatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
