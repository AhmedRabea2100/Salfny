import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadItemsComponent } from './upload-items.component';

describe('UploadItemsComponent', () => {
  let component: UploadItemsComponent;
  let fixture: ComponentFixture<UploadItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploadItemsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UploadItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
