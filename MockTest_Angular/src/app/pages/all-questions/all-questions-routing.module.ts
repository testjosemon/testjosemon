import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AllQuestionsPage } from './all-questions.page';

const routes: Routes = [
  {
    path: '',
    component: AllQuestionsPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AllQuestionsPageRoutingModule {}
