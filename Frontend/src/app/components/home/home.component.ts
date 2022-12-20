import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { global } from 'src/app/global';
import { Post } from 'src/types/post.type';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor (private router: Router,private http: HttpClient,private activatedRoute: ActivatedRoute) {}
  logged:any
  state:any
  p1:any
   ngOnInit () {
    this.p1="/../assets/images/pr2.jpg" 
    

    const headerr=new HttpHeaders({'Content-Type': 'application/json' ,'Authorization':localStorage.getItem("token")+"" });
   
    this.http.get<Post[]>('http://localhost:8080/home',{ headers: headerr}
    ) .subscribe({
      next: (data: any) => {
        this.state="Log Out"
        console.log(data);
      },
      error: (error: any) => {
        this.state="Login"
        if(error.status==401){
          console.log(error.error);
        }else{
          console.error(error);
        }
      }
      });
}

sell(){
if(this.state=="Login"){
  alert("Please, login first!!")
  this.router.navigateByUrl('login')
}else{
  this.router.navigateByUrl('uploadItems')
}
}

log(state:string){
  if(state=="Log Out"){
    localStorage.removeItem("token");
  }
}
view(ph:string,namee:any,desc:any,pricee:any){
  global.photo=ph
  global.Name=namee
  global.description=desc
  global.price=pricee
  this.router.navigateByUrl('productview')
}


}
