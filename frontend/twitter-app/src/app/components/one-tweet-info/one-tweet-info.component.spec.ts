import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OneTweetInfoComponent } from './one-tweet-info.component';

describe('OneTweetInfoComponent', () => {
  let component: OneTweetInfoComponent;
  let fixture: ComponentFixture<OneTweetInfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OneTweetInfoComponent]
    });
    fixture = TestBed.createComponent(OneTweetInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
