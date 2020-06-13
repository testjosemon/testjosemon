import { Component, OnInit,ViewChild } from '@angular/core';
import { UsersService } from '../../../services/users.service';
import {AuthService} from '../../../services/auth.service';
import {Sort} from '@angular/material/sort';
import { Router } from '@angular/router';
import{attendedExam} from '../../../model/attendedExam';
import { dashboard } from '../../../model/dashboard';
import { MatTableDataSource } from '@angular/material/table';
import {MatPaginator} from  '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';

@Component({
  selector: 'app-attended-exams',
  templateUrl: './attended-exams.page.html',
  styleUrls: ['./attended-exams.page.scss'],
})
export class AttendedExamsPage implements OnInit {

  
 data:object;
 login:String;
 examlist: Array<attendedExam>;
 dataSource= null;
 isLoading = true;
 isEmpty=true;

 displayedColumns: string[] = [ 'index','examName', 'score', 'percentage','result','dateTime'];

 @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  examData:attendedExam={
    examId:"",
    examName:"",
    score : "",
    total:"",
    percentage:"" ,
    result:"",
    date:"",
    time:"",
  }

  dashboardData:dashboard={
    currentUser:"",
    userId:"",
    attendedExamList:this.examlist
  }

  constructor(private userServ:UsersService,private auth:AuthService,private router: Router) {}
 
 
  attendedExams()
  {
    this.userServ.getUserDashboardDetails(this.login).subscribe(data => {
      console.log(data);
      this.dashboardData=data
      this.examlist=this.dashboardData.attendedExamList;
      console.log(this.examlist); 
      this.isLoading = false;
      if(this.examlist.length>0)
     {
      this.isEmpty=false;
     }
     this.dataSource = new MatTableDataSource<attendedExam>(this.examlist);
   this.dataSource.sort = this.sort;
     console.log(this.dataSource);
    },
    error => this.isLoading = false
    );
  }
  ngOnInit() {
    
    this.auth.getUserInfo().then(userData => {
      this.login=userData.name;
      this.attendedExams();

    })

  }
  
 examHistory(id)
 {
  console.log("method call" +id);
  this.router.navigate(['/exam-history',id]);
 
 }
}


