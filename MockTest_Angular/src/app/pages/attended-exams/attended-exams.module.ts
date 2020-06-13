import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { IonicModule } from '@ionic/angular';

import { AttendedExamsPageRoutingModule } from './attended-exams-routing.module';

import { AttendedExamsPage } from './attended-exams.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AttendedExamsPageRoutingModule,NgxDatatableModule
  ],
  declarations: [AttendedExamsPage]
})
export class AttendedExamsPageModule {}
