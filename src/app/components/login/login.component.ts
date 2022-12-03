import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Login} from './login';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit{
  login = new Login('', '');


  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }
  email: any
  pass: any
  flag: boolean = false;
  signinmap: any
  t: any;
  onSubmit() {
  
    console.log(' Email: ' + this.login.email + ', Password: ' + this.login.password );

    this.email = this.login.email;
    this.pass = this.login.password;
    console.log( JSON.stringify({
      "email": this.email,
      "password": this.pass,
    }))
    this.loginn();
  
  }
  
  loginn() {
    var httpOptions = {
      headers: new HttpHeaders({
      'Content-Type': 'application/json',
      })
    }
    this.http.post('http://localhost:8080/login', JSON.stringify({
      email: this.email,
      password: this.pass
    }
    
    ), httpOptions)

    .subscribe({
        next: (data: any) => {
            console.log("hii")
            console.log(data);
            },
            error: (error: any) => {
            console.error(error);
            }
        });
  }



}
