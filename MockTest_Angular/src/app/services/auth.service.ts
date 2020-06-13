import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  imports:[HttpClient]
  public data;
  public isLogin=false;
  private loginurl=""
  constructor(private http: HttpClient) { }

  public isAuthenticated(): boolean {
    const userData = sessionStorage.getItem('userData');
    console.log(userData);
    if (userData && userData.length > 0) {
    return true;
    } else {
    return false;
    }
    }
    
    public async getUserInfo()
    {
      const userInfo=await sessionStorage.getItem('userData');
      return JSON.parse(userInfo);
    }
    public async login(postData) {
      console.log('user'+postData.username);
      const loginApiResponce = {
        name: 'pushkala',
        role:'user',
        id: 5
        // token: '2323523523DFSWERWERWER'
      };
      await sessionStorage.setItem('userData', JSON.stringify(loginApiResponce));
      this.data =sessionStorage.getItem('userData');
      console.log('data'+this.data);
      return true;
    }
    public loginUser(user){
      //  return this.http.post<any>(this.loginurl,user)
      console.log('hiiiii'+user);
      this.isLogin=true;
      console.log(this.isLogin);
      this.data =sessionStorage.getItem('userData');
      console.log(this.data);
     return this.data;
    
    }
  
    public async logout() {
      await sessionStorage.removeItem('userData');
      await sessionStorage.clear();
      return true;
    }
    
}
