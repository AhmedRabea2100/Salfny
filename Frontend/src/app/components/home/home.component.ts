import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Post } from '../../../types/post.type';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor (private router: Router,private http: HttpClient,private activatedRoute: ActivatedRoute) {}

   ngOnInit () {
    const headerr=new HttpHeaders({'Content-Type': 'application/json' ,'Authorization':localStorage.getItem("token")+"" });
    this.http.get<Post[]>('http://localhost:8080/home',{ headers: headerr}
  
    ) .subscribe({
      next: (data: any) => {
        document.getElementById("signinBtn")!.style.visibility="hidden";
        console.log(data);
      },
      error: (error: any) => {
        if(error.status==401){
          console.log(error.error);
        }else{
          console.error(error);
        }

      }
      });
}



}
