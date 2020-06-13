import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SlidesComponent } from './slides/slides.component';
import { StartComponent } from './start/start.component';
import { IonicModule } from '@ionic/angular';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [SlidesComponent,StartComponent],
  imports: [CommonModule,FormsModule,IonicModule],
  exports:[SlidesComponent,StartComponent]
})
export class ComponentsModule { }
