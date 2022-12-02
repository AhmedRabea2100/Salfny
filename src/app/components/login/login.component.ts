import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent{
  
} 
//implements OnInit {
//   constructor(private http: HttpClient) { }

//   ngOnInit(): void {
//   }
//   email: any
//   pass: any
//   flag: boolean = false;
//   signinmap: any
//   t: any;
//   signin() {
//     this.signinmap = new Map()
//     this.email = (<HTMLInputElement>document.getElementById("email")).value
//     this.pass = (<HTMLInputElement>document.getElementById("password")).value

//     this.signinmap['email'] = this.email
//     this.signinmap['password'] = this.pass
//     console.log(this.signinmap);
//     console.log( JSON.stringify({
//       "email": this.email,
//       "password": this.pass,
//     }))
//     console.log(this.login());
  
//   }
  
//   // SignIn() {
//   //   this.http.post('http://localhost:8080/login', JSON.stringify({
//   //     "email": this.email,
//   //     "password": this.pass,
//   //   }
    
//   //   ))

//       // .subscribe((response) => {
//       //   console.log(response);
//       // // if(response.toString"Login Successfully") alert("Logged in Successfully!!");
//       // // else alert("Wrong e-mail or Password!! please check Again.");
//       // })

//   //}


//    user: any;
//   // public SignIn(): Observable<any> {
//   //   this.user=JSON.stringify({
//   //     "email": this.email,
//   //     "password": this.pass
//   // })
//   //   const url = 'http://localhost:8080/login';
//   //   return this.http.post<any>(url, this.user);
//   // }
  

//   // saveUser() {
//   //     this.user = this.addUserForm.value;
//   //     this.userService.saveUser(this.user).subscribe((response: any) => {
//   //       console.log(response);
//   //     });
//   // }

//   login(){
  
//     this.http.post("http://localhost:8080/login", this.user=JSON.stringify({
//           "email": this.email,
//           "password": this.pass
//       })).subscribe(
//       () => {},
//       (error) => {
//         alert("Username or password are incorrect");
//       },
//       () => {
//         alert("Correct");
//       }
//     );
//   }

// }
