import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Signup } from './signup';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
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

  onSubmit() {
    console.log('Name: ' + this.signup.name + ', Email: ' + this.signup.email + ', Password: ' + this.signup.password);

  }

}
