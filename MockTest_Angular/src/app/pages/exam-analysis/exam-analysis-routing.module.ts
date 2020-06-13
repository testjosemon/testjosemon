import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ExamAnalysisPage } from './exam-analysis.page';

const routes: Routes = [
  {
    path: '',
    component: ExamAnalysisPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ExamAnalysisPageRoutingModule {}
