import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { AttendedExamsPage } from './attended-exams.page';

describe('AttendedExamsPage', () => {
  let component: AttendedExamsPage;
  let fixture: ComponentFixture<AttendedExamsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttendedExamsPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(AttendedExamsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
