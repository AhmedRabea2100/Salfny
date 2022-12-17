import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Signup } from './signup';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  ngOnInit(): void {
    
  }
  constructor(private router: Router, private route: ActivatedRoute,private http: HttpClient) { }
  title = '';
  passwordConfirmationFailed = false;
  passwordConfirmationTxt = '';
  emailFormat = false;
  signup = new Signup('', '', '', '');



  confirmPassword() {
    if (this.signup.password === this.passwordConfirmationTxt) {
      this.passwordConfirmationFailed = false;
    } else {
      this.passwordConfirmationFailed = true;
    }
  }
  confirmEmail() {
    console.log("d5lt");
    if (this.signup.email.includes('@')){
      this.emailFormat = true;
    }
    console.log(this.emailFormat);
      
  }

  email: any
  pass: any
  name:any
  phone:any

  onSubmit() {
    console.log('Name: ' + this.signup.username + ', Email: ' + this.signup.email + ', Password: ' + this.signup.password);
    /*this.signupp(this.signup).subscribe(data => {
      console.log(data)
      
    }) ;*/
    this.signupp()
  }

  signupp() {
  
    const headerr=new HttpHeaders({'Content-Type': 'application/json' ,'authentication': 'key' });
    this.http.post('http://localhost:8080/signup', this.signup, { headers: headerr, responseType:'text'})
    .subscribe({

        next: (data: any) => {
            console.log("hii")
            console.log(data)
            if(data==='Registration Succeeded'){
              alert("Registeration Succeeded, please login")
              this.router.navigateByUrl('login')
            }else
              alert("This Email is already used")
            
            
            },
            error: (error: any) => {
            console.error(error);
            }
        });
  }
  

  /*signupp(signup:Signup): Observable<Signup> {
    const headers = { 'content-type': 'application/json'}  
    const body=JSON.stringify(signup);
 
    return this.http.post<Signup>('http://localhost:8080/signup', body,{'headers':headers, responsetype: 'text'})
  }*/
 
  



}
