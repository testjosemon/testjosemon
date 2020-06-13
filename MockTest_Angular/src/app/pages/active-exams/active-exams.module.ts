import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ActiveExamsPageRoutingModule } from './active-exams-routing.module';

import { ActiveExamsPage } from './active-exams.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ActiveExamsPageRoutingModule
  ],
  declarations: [ActiveExamsPage]
})
export class ActiveExamsPageModule {}
