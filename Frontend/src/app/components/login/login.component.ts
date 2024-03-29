import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Login } from '../../../types/login.type';
import { global } from 'src/app/global';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  login = new Login('', '');


  constructor(private router: Router, private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit(): void {
    global.logged = false
  }
  email: any
  pass: any
  flag: boolean = false;
  signinmap: any
  t: any;
  onSubmit() {
    this.loginn();
  }


  loginn() {
    const headerr = new HttpHeaders({ 'Content-Type': 'application/json', 'authentication': 'key' });
    this.http.post('http://localhost:8080/login', this.login, { headers: headerr, responseType: 'text' })
      .subscribe({
        next: (data: string) => {
          if (data === "Email not found") {
            Swal.fire({
              position: 'center',
              icon: 'error',
              title: 'Email not Found',
              showConfirmButton: false,
              timer: 1500
            })

          } else if (data === "Incorrect password") {
            Swal.fire({
              position: 'center',
              icon: 'error',
              title: 'Incorrect password',
              showConfirmButton: false,
              timer: 1500
            })

          }
          else {
            localStorage.setItem("token", data.split(" ")[0]);
            localStorage.setItem("user_id", data.split(" ")[1]);
            this.router.navigateByUrl('home')
          }
        },
        error: (error: any) => {
          console.error(error);
        }
      });
  }




}
