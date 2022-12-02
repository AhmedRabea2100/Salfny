import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
const routes: Routes = [
  {path: '',   redirectTo: 'signup', pathMatch: 'full' }, // redirect to `first-component`
  {path:'signup', component:SignupComponent},
  {path:'login', component:LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents =[SignupComponent,LoginComponent];