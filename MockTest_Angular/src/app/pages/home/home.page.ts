import { Component, OnInit } from '@angular/core';
<<<<<<< HEAD
import { MockTestService } from '../../services/mock-test.service';
=======
import { MockTestService } from '../../mock-test.service';
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801
import{AuthService} from '../../services/auth.service';
import{Router,ActivatedRoute} from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { UsersService } from '../../services/users.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {
  public selectedIndex = 0;
  public adminPages = [
    {
      title: 'home',
      url: '/home',
      icon: 'home'
    },
    {
     
      title: 'question',
      url: '/question',
      icon: 'mail'
    },
    {
      title: 'exam',
      url: '/active-exams',
      icon: 'paper-plane'
    },
    {
<<<<<<< HEAD
      title: 'exam-analysis',
      url: '/exam-analysis',
      icon: 'pencil'
=======
      title: 'users',
      url: '/users',
      icon: 'heart'
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801
    }
  ];

  public userPages = [
    {
      title: 'home',
      url: '/home',
      icon: 'home'
    },
 
    {
      title: 'Dashboard',
      url:  '/user-dashboard',
      icon: 'mail'
    },
    {
      title: 'ActiveExams',
      url: '/active-exams',
      icon: 'heart'
    }
  ];
  
  url:string='http://localhost:8080/api/mocktest-controller/';
  questions:any=this.mockTestSer.getDataFromServer('http://localhost:8080/api/questions/');
  // userRole=this.mockTestSer.getStringFromServer(this.url);

  public userInfo:object;
   userRole;

//  registerId;
  constructor(private mockTestSer: MockTestService,private http: HttpClient,
    private route: ActivatedRoute,private userServ:UsersService,private auth:AuthService,private router:Router) { }

  isAuthenticated(){
   if(this.auth.isLogin==true)
   {
    if(this.auth.data.role=='admin')
     this.userRole='admin';
     else
     this.userRole='user';
     console.log(this.userRole)
     return true;
   }
 }
 async logout() {
    
  if(this.auth.logout())
  {
    this.router.navigate(['login']);
  }

  }
 
  ngOnInit() {

    this.userRole='admin'
 
    const path = window.location.pathname.split('folder/')[1];
    // const path = window.location.pathname.split('/')[1];
    if (path !== undefined) {
      this.selectedIndex = this.adminPages.findIndex(page => page.title.toLowerCase() === path.toLowerCase());
    }
    // this.isAuthenticated();
    this.auth.getUserInfo().then(userData => {
      console.log(userData);
      this.userInfo=userData;
      // this.id=userData.id;
     
    })
   

  
  }

}
