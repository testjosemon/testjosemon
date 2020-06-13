import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginPageModule } from '../pages/login/login.module';
<<<<<<< HEAD
import{dashboard} from '../model/dashboard';
=======

>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801
@Injectable({
  providedIn: 'root'
})
export class UsersService {
  imports:[HttpClient]
  constructor(private http: HttpClient) { }
  getData(){
    return this.http.get(`http://localhost:8080/api/mocktest-controller/all/`);
  }
  
  getUserById(id)
  {
    return this.http.get('http://localhost:8080/api/mocktest-controller/users/'+id);
  }

 getUserDashboardDetails(username)
 {
<<<<<<< HEAD
  return this.http.get<dashboard>(`http://localhost:8080/api/mocktest-controller/user_dashboard/`+username);
 }
  getAllExams(){
    return this.http.get(`http://localhost:8080/api/mocktest-controller/allExamDetails/`);
  }
  getAllAttendedExamDetails(id)
  {
    return this.http.get('http://localhost:8080/api/mocktest-controller/getAllAttendedExamDetails/'+id);
  }
=======
  return this.http.get(`http://localhost:8080/api/mocktest-controller/user_dashboard/`+username);
 }
  
>>>>>>> 1953f671dbed23b804d83d9c3a06c519b2767801
}
