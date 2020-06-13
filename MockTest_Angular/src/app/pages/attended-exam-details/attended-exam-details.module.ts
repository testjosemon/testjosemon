import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AttendedExamDetailsPageRoutingModule } from './attended-exam-details-routing.module';

import { AttendedExamDetailsPage } from './attended-exam-details.page';
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
    AttendedExamDetailsPageRoutingModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule
  ],
  declarations: [AttendedExamDetailsPage]
})
export class AttendedExamDetailsPageModule {}
