import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../services/users.service';
import {AuthService} from '../../services/auth.service';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { Router } from '@angular/router';
@Component({
  selector: 'app-attended-exams',
  templateUrl: './attended-exams.page.html',
  styleUrls: ['./attended-exams.page.scss'],
})
export class AttendedExamsPage implements OnInit {

  constructor(private userServ:UsersService,private auth:AuthService,private router: Router) { }
 
  data;

 public result;
 public login:String;

  attendedExams()
  {
    this.userServ.getUserDashboardDetails(this.login).subscribe(data => {
      console.log(data);
     this.data=data
     
    });
  }
  ngOnInit() {
    
    this.auth.getUserInfo().then(userData => {
      this.login=userData.name;
   
      this.attendedExams();

    })

    // if(this.attendedExamdata?.result==true)
    // {
    //   this.result="pass";
    // }
  }
  
 examHistory(id)
 {
  console.log("method call" +id);
  this.router.navigate(['/exam-history']);

 }

}
