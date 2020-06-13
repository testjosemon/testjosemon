import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../services/users.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-exam-analysis',
  templateUrl: './exam-analysis.page.html',
  styleUrls: ['./exam-analysis.page.scss'],
})
export class ExamAnalysisPage implements OnInit {
  exam;
  constructor(private userServ:UsersService,private router: Router) {

     }
     getAllExam() {
      this.userServ.getAllExams().subscribe(data => {
        console.log(data);
        this.exam=data    
      });
     }
     examDetaills(id)
     {
      console.log("method call" +id);
    this.router.navigate(['/attended-exam-details',id]);
     }
  ngOnInit() {
    this.getAllExam();
  }

}
