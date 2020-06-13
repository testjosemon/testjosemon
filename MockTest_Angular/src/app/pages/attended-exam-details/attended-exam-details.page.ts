import { Component, OnInit,ViewChild } from '@angular/core';
import { RouterModule,ActivatedRoute } from '@angular/router';
import { UsersService } from '../../services/users.service';
import { Router } from '@angular/router';
import { PopoverController } from '@ionic/angular';
import { MatTableDataSource } from '@angular/material/table';
import {MatPaginator} from  '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';


export interface attendedExamDetails
{
     user:{
    login:String;
    id;
    }
   score:string;
    total:string;
    percentage:string;
    result:string;
    dateTime:string;
    
}

@Component({
  selector: 'app-attended-exam-details',
  templateUrl: './attended-exam-details.page.html',
  styleUrls: ['./attended-exam-details.page.scss'],
})
export class AttendedExamDetailsPage implements OnInit {


  examData:attendedExamDetails={
    
    user:{
      id:"",
      login:""},
   score : "",
    total:"",
    percentage:"" ,
    result:"",
    dateTime:"",
    

  }
  
  examlist: Array<attendedExamDetails>;
 
  attendedExamData;
  dataSource1= null;
 
  isLoading = true;
  isEmpty=true;
 
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private acivaterouter:ActivatedRoute,
    private userServ:UsersService,
    private router: Router,
    public popoverController: PopoverController) { }


  
  attendedExamDetails(id)
  {
    this.userServ.getAllAttendedExamDetails(id).subscribe(data => {
      console.log(data);
      this.attendedExamData=data
      this.isLoading = false;
     this.examlist=this.attendedExamData.attendList
   
     if(this.examlist.length>0)
     {
      this.isEmpty=false;
     }
    this.dataSource1 = new MatTableDataSource<attendedExamDetails>(this.examlist);
    this.dataSource1.sortingDataAccessor = (item, property) => {
      switch(property) {
        case 'users.login': return item.users.login;
        default: return item[property];
      }
    };
   this.dataSource1.sort = this.sort;
   

     console.log(this.dataSource1);
    },
    error => this.isLoading = false
    );
  }
  examHistory(examid,userid)
 {
  console.log("method call" +examid + userid);
  this.router.navigate(['/exam-history',examid]);
 
 }


displayedColumns: string[] = [ 'index','user', 'score', 'percentage','result','dateTime'];


  ngOnInit() {
    this.acivaterouter.params.subscribe(params => {
      const id= params['id']; //use this id to get  details..!
      this.attendedExamDetails(id);
     
    });

  }
  

}
