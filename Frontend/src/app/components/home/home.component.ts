import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Post } from 'src/types/post.type';
import Swal from 'sweetalert2';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor (private router: Router,private http: HttpClient,private activatedRoute: ActivatedRoute,private sanitizer:DomSanitizer) {}
  searchWord:string;
  logged:any
  state:any
  p1:any
  posts:Post[] | undefined;
  topPosts:Post[] | undefined;
  path:string='/productview';
   ngOnInit () {
    if(localStorage.getItem("user_login")==null){
      document.getElementById("userbtn").style.visibility="visible"
    }else{
      document.getElementById("userbtn").style.visibility="visible"
    }
    this.p1="/../assets/images/pr2.jpg" 
    

    const headerr=new HttpHeaders({'Content-Type': 'application/json' ,'Authorization':localStorage.getItem("token")+"" });
   
    this.http.get<Post[]>('http://localhost:8080/home',{ headers: headerr}
    ) .subscribe({
      next: (data: any) => {
        this.state="Log Out"
        this.posts=data;
        console.log(data);
      },
      error: (error: any) => {
        this.state="Login"
        if(error.status==401){
          this.posts=error.error;
          console.log(error.error);
        }else{
          console.error(error);
        }
      }
      });

    this.http.get<Post[]>('http://localhost:8080/topten'
    ) .subscribe({
      next: (data: any) => {
        this.topPosts=data;
        console.log(data);
      },
      error: (error: any) => {
          console.error(error);
        
      }
      });
}

sell(){
if(this.state=="Login"){
  Swal.fire({
    position: 'center',
    icon: 'warning',
    title: 'Please, login first!',
    showConfirmButton: false,
    timer: 1500
  })
  this.router.navigateByUrl('login')
}else{
  this.router.navigateByUrl('uploadItems')
}
}

log(state:string){
  if(state=="Log Out"){
    localStorage.removeItem("token");
    localStorage.removeItem("user_id");
  }
}
view(id:number){
  localStorage.setItem("post_id",id+"")
}
sanitize(url:string){
  return this.sanitizer.bypassSecurityTrustUrl(url);
}

/*********************************************Search****************************************/
search(){
 this.searchWord= (<HTMLInputElement>document.getElementById("searchField")).value
   console.log("hello "+this.searchWord)
   localStorage.setItem("searchWord",this.searchWord+"")
   
  // const headerr=new HttpHeaders({'Content-Type': 'application/json' ,'authentication': 'key' });

  // this.http.post<Post[]>('http://localhost:8080/search',this.searchWord,{ headers: headerr}
  //   ) .subscribe({
  //     next: (data: any) => {     
  //       this.posts=data;
  //       console.log(data);
  //     },
  //     error: (error: any) => {
  //       if(error.status==401){
  //         this.posts=error.error;
  //         console.log(error.error);
  //       }else{
  //         console.error(error);
  //       }
  //     }
  //     });


}



}
