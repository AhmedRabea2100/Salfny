import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Login} from './login';
import { global } from 'src/app/global';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit{
  login = new Login('', '');


  constructor(private router: Router, private route: ActivatedRoute,private http: HttpClient) { }

  ngOnInit(): void {
    global.logged=false
  }
  email: any
  pass: any
  flag: boolean = false;
  signinmap: any
  t: any;
  onSubmit() {
  
    console.log(' Email: ' + this.login.email + ', Password: ' + this.login.password );

    this.loginn();
  
  }
  
 
  loginn() {
    const headerr=new HttpHeaders({'Content-Type': 'application/json' ,'authentication': 'key' });
    this.http.post('http://localhost:8080/login', this.login, { headers: headerr, responseType:'text'})
    .subscribe({
        next: (data: any) => {
            if(data==="Email not found"){
              alert("Email not Found")
            }else if(data==="Incorrect password"){
              alert("Incorrect password")  
            }
            else{
              localStorage.setItem("token",data);
              this.router.navigateByUrl('home')
            }                     
            },
            error: (error: any) => {
            console.error(error);
            }
        });
  }




}
