import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

export interface QstnOption{
  option: string;
  isAnswer: boolean;
}
export interface Question {
  qstn: string;
  level: string;
  qstnOptions: Array<QstnOption>;
}

@Injectable({
  providedIn: 'root'
})
export class MockTestService {

  defUrl='http://localhost:8080/api/mocktest-controller';
  
  getStringFromServer(url:string):Observable<string>{
   return this.http.get(this.defUrl+url,{responseType: 'text'}).pipe(map(str => {
      console.log('data from server for url'+this.defUrl+url+' is ::'+str);
      return str;}));
  }

  getDataFromServer(url:string){
    return this.http.get(this.defUrl+url).pipe(map(data => {
      console.log('string from server for url'+this.defUrl+url+' is ::'+data);
        return data;}));
  }

  postQstnToServer(url:string,data:Question){
    console.log('post data  for url'+this.defUrl+url+' is ::'+data);
    this.http.post(this.defUrl+url,data).subscribe(()=>{alert("question created");
    this.router.navigateByUrl('/question');}
  );
  err=> {
    alert("something went wromg..!" );
  }
  }

  constructor(private http:HttpClient,private router:Router) { }
}



