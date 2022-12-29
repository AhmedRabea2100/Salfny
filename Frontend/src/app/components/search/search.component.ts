import { Component, ElementRef, ViewChild } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Post } from 'src/types/post.type';
import Swal from 'sweetalert2';
import {DomSanitizer} from '@angular/platform-browser';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  @ViewChild('input', { static: false }) 
   set input(element: ElementRef<HTMLInputElement>) {
     if(element) {
       element.nativeElement.focus()
     }
  }

  constructor (private router: Router,private http: HttpClient,private activatedRoute: ActivatedRoute,private sanitizer:DomSanitizer) {}
  categories = ['All','cars', 'department', 'bikes', 'suit', 'dresses', 'electronic devices', 'others'];
  categoryName=localStorage.getItem('category')
  searchWord:string
  products='Products'
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
    
    if(localStorage.getItem('isCategory')==='true'){
      
      this.category();
      localStorage.setItem("isCategory",false + "")
      
    }else{
      this.searchWord=localStorage.getItem("searchWord");
      (<HTMLInputElement>document.getElementById("searchField")).value=this.searchWord
      this.searchh();
    }
    
  
   
}
category(){
  
  localStorage.setItem("category",this.categoryName + "")
  this.products=this.categoryName
  const headerr=new HttpHeaders({'Content-Type': 'application/json' ,'authentication': 'key' });
  this.http.post<Post[]>('http://localhost:8080/category',localStorage.getItem('category'),{ headers: headerr}
    ) .subscribe({
      next: (data: Post[]) => { 
          this.posts=data;     
      },
      error: (error: any) => {
        if(error.status==401){
          this.posts=error.error;
          console.log(error.error);
        }else{
          console.error(error);
        }
      }
      });
      
      
}
searchh(){

  this.searchWord = (<HTMLInputElement>document.getElementById("searchField")).value||" ";

  const headerr=new HttpHeaders({'Content-Type': 'application/json' ,'authentication': 'key' });
  this.http.post<Post[]>('http://localhost:8080/search',this.searchWord,{ headers: headerr}
    ) .subscribe({
      next: (data: Post[]) => { 
          this.posts=data;     
      },
      error: (error: any) => {
        if(error.status==401){
          this.posts=error.error;
          console.log(error.error);
        }else{
          console.error(error);
        }
      }
      });
     // localStorage.setItem("searchWord", this.searchWord + "")
      //this.router.navigateByUrl('search')
        

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
search(){

 
    this.searchWord = (<HTMLInputElement>document.getElementById("searchField")).value
    console.log("hello " + this.searchWord)
    localStorage.setItem("searchWord", this.searchWord + "")
    this.searchh()
     
    
}
}
