import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ExamAnalysisPageRoutingModule } from './exam-analysis-routing.module';

import { ExamAnalysisPage } from './exam-analysis.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ExamAnalysisPageRoutingModule
  ],
  declarations: [ExamAnalysisPage]
})
export class ExamAnalysisPageModule {}
