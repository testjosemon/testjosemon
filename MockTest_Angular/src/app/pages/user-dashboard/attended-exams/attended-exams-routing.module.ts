import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AttendedExamsPage } from './attended-exams.page';

const routes: Routes = [
  {
    path: '',
    component: AttendedExamsPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AttendedExamsPageRoutingModule {}
