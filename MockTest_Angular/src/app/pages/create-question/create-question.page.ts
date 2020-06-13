import { Component, OnInit } from '@angular/core';
<<<<<<< HEAD
import { Question, MockTestService } from '../../services/mock-test.service';
=======
import { Question, MockTestService } from 'src/app/mock-test.service';
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801

@Component({
  selector: 'app-create-question',
  templateUrl: './create-question.page.html',
  styleUrls: ['./create-question.page.scss'],
})
export class CreateQuestionPage implements OnInit {

  question:Question={
    qstn:'',
    level:'beginner',
    qstnOptions:[
      {
        option:'',
        isAnswer:false
      },
      {
        option:'',
        isAnswer:false
      },
      {
        option:'',
        isAnswer:false
      }
    ]
  };

  allLevels=['beginner','intermediate','expert'];

  logForm(form) {
    console.log('question is '+this.question.qstnOptions[1].isAnswer);
    this.mockSer.postQstnToServer('/app/question',this.question);   
  }
  
  constructor(private mockSer:MockTestService) { }

  ngOnInit() {
    console.log('question is '+this.question.qstn);
  }

}
