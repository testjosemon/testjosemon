import { Component, OnInit } from '@angular/core';
import { RouterModule,ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-exam-history',
  templateUrl: './exam-history.page.html',
  styleUrls: ['./exam-history.page.scss'],
})
export class ExamHistoryPage implements OnInit {

  constructor(private acivaterouter:ActivatedRoute) { }

  ngOnInit() {

<<<<<<< HEAD
    this.acivaterouter.params.subscribe(params => {
      const id= params['id']; //use this id to get examHistory details..!

    });
=======
    // this.acivaterouter.params.subscribe(params => {
    //   let id = params['id'];
    
    //   console.log(`${id}`);
    //   });
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801
  }

}
