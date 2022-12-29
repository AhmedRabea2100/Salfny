import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Signup } from './signup';
import { global } from 'src/app/global';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})

export class SignupComponent implements OnInit {


  itemList = [];
  selectedItems = [];
  settings = {};
  ngOnInit() {
    global.logged=false
    this.itemList = [
      { "id": 1, "itemName": "cars" },
      { "id": 2, "itemName": "department" },
      { "id": 3, "itemName": "bikes" },
      { "id": 4, "itemName": "suit" },
      { "id": 5, "itemName": "dresses" },
      { "id": 6, "itemName": "electronic devices" }
  ];

  this.settings = {
      text: "Select Preferred Cateogries ♡",
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      classes: "myclass custom-class"
  };
}
onItemSelect(item: any) {
  console.log("jjj");
  console.log(item);

}
OnItemDeSelect(item: any) {
  console.log(item);
  this.removeItem(item);

}
removeItem(doc){
  this.selectedItems.forEach( (item, index) => {
    if(item === doc) this.selectedItems.splice(index,1);
  });
}
onSelectAll(items: any) {
  console.log(items);
  this.selectedItems=[      { "id": 1, "itemName": "cars" },
  { "id": 2, "itemName": "department" },
  { "id": 3, "itemName": "bikes" },
  { "id": 4, "itemName": "suit" },
  { "id": 5, "itemName": "dresses" },
  { "id": 6, "itemName": "electronic devices" }]
}
onDeSelectAll(items: any) {
  console.log(items);
  this.selectedItems=[];
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
    console.log(this.selectedItems)
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
              Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Registeration Succeeded, please login',
                showConfirmButton: false,
                timer: 1500
              })
              this.router.navigateByUrl('login')
            }else{
              Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'his Email is already used',
                showConfirmButton: false,
                timer: 1500
              })
              
            }
            
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
