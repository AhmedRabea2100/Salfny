import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserFavPostsComponent } from './user-fav-posts.component';

describe('UserFavPostsComponent', () => {
  let component: UserFavPostsComponent;
  let fixture: ComponentFixture<UserFavPostsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserFavPostsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserFavPostsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
