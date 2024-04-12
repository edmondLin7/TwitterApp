import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TweetCreatorComponent } from './tweet-creator.component';

describe('TweetCreatorComponent', () => {
  let component: TweetCreatorComponent;
  let fixture: ComponentFixture<TweetCreatorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TweetCreatorComponent]
    });
    fixture = TestBed.createComponent(TweetCreatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
