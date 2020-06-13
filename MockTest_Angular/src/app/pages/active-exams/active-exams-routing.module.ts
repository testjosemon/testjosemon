import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ActiveExamsPage } from './active-exams.page';

const routes: Routes = [
  {
    path: '',
    component: ActiveExamsPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ActiveExamsPageRoutingModule {}
