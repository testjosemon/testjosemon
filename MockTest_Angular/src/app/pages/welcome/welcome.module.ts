import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { WelcomePageRoutingModule } from './welcome-routing.module';
import { RouterModule, Routes } from '@angular/router';
import { WelcomePage } from './welcome.page';
import { ComponentsModule } from '../../components/components.module';



const routes: Routes = [
  {
  path: '',
  component: WelcomePage
  }
  ];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    WelcomePageRoutingModule,ComponentsModule,RouterModule.forChild(routes)  ],
  declarations: [WelcomePage]
})
export class WelcomePageModule {}
