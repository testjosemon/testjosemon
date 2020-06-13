import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
 
import { RouteReuseStrategy,RouterModule } from '@angular/router';
import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import {AuthService} from './services/auth.service';
import{AuthGuard} from './guards/auth.guard';
import {LoginGuard} from './guards/login.guard';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
<<<<<<< HEAD
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule } from '@angular/material/table';
import { MatTableDataSource } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
=======
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801


@NgModule({
  declarations: [AppComponent],
  entryComponents: [],
  imports: [
    BrowserModule,
    HttpClientModule,
    IonicModule.forRoot(),
    HttpClientModule,
<<<<<<< HEAD
    AppRoutingModule, RouterModule, BrowserAnimationsModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule
=======
    AppRoutingModule, RouterModule
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801
  ],
  providers: [
    StatusBar,
    SplashScreen,AuthService,AuthGuard,
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
