import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuard} from '../../guards/auth.guard';
import { HomePage } from './home.page';

const routes: Routes = [
  {
    path: '',
    component: HomePage,
    canActivate: [AuthGuard],
    children: [
      
        // {
        //   path: 'active-exams',
        //   loadChildren: () => import('./active-exams/active-exams.module').then( m => m.ActiveExamsPageModule)
        // },
        // {
        //   path: 'user-dashboard',
        //   loadChildren: () => import('./user-dashboard/user-dashboard.module').then( m => m.UserDashboardPageModule)
        // }
        // {
        //   path: 'question',
        //   loadChildren: () => import('../question/question.module').then( m => m.QuestionPageModule)
        // },
        
       

      ]
    } 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomePageRoutingModule {}
