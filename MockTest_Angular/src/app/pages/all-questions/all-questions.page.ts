import { Component, OnInit } from '@angular/core';
<<<<<<< HEAD
import { MockTestService, Question } from '../../services/mock-test.service';
=======
import { MockTestService, Question } from 'src/app/mock-test.service';
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801

@Component({
  selector: 'app-all-questions',
  templateUrl: './all-questions.page.html',
  styleUrls: ['./all-questions.page.scss'],
})
export class AllQuestionsPage implements OnInit {

  constructor(private mockSer:MockTestService) { }
  
  allQuestions=this.mockSer.getDataFromServer("/app/allQuestions")

<<<<<<< HEAD
  fquestions = [];

  ngOnInit() {
  }

=======
  ngOnInit() {
  }
fquestions = []ofslks
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801
}
