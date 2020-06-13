import { NgModule,CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import  {RouterModule} from '@angular/router';
import { IonicModule } from '@ionic/angular';
import { HomePageRoutingModule } from './home-routing.module';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { HomePage } from './home.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    HomePageRoutingModule,RouterModule,NgxDatatableModule
  ],
 
  
  declarations: [HomePage], schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HomePageModule {}
