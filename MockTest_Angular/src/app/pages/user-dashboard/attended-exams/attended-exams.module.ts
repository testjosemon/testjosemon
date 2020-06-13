import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { IonicModule } from '@ionic/angular';

import { AttendedExamsPageRoutingModule } from './attended-exams-routing.module';

import { AttendedExamsPage } from './attended-exams.page';
import { MatTableModule } from '@angular/material/table';
import { MatTableDataSource } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import {MatCardModule} from '@angular/material/card';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner'

@NgModule({
  imports: [
    MatCardModule,
    MatProgressSpinnerModule,
    CommonModule,
    FormsModule,
    IonicModule,
    AttendedExamsPageRoutingModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule
  ],
  declarations: [AttendedExamsPage]
})
export class AttendedExamsPageModule {}
