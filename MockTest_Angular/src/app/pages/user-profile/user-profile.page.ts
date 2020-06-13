import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../services/users.service';
import{AuthService} from '../../services/auth.service';
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.page.html',
  styleUrls: ['./user-profile.page.scss'],
})
export class UserProfilePage implements OnInit {

  constructor(private userServ:UsersService,private auth:AuthService) { }
  data;
  id;
  user()
  {
    
      this.userServ.getUserById(this.id).subscribe(data => {
      console.log(data);
      this.data=data});
   
  }

  ngOnInit() {
    this.auth.getUserInfo().then(userData => {
      console.log(userData);
      this.id=userData.id;
      console.log(this.id);
      this.user(); 
    })
  }

}
