import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AttendedExamDetailsPage } from './attended-exam-details.page';

const routes: Routes = [
  {
    path: ':id',
    component: AttendedExamDetailsPage,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AttendedExamDetailsPageRoutingModule {}
