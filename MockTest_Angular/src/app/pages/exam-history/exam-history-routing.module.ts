import { NgModule } from '@angular/core';
import { Routes, RouterModule,ActivatedRoute } from '@angular/router';

import { ExamHistoryPage } from './exam-history.page';

const routes: Routes = [
  {
<<<<<<< HEAD
    path: ':id',
=======
    path: '',
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801
    component: ExamHistoryPage,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ExamHistoryPageRoutingModule {}
