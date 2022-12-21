import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ProductviewComponent } from './components/productview/productview.component';
import { SignupComponent } from './components/signup/signup.component';
import { UploadItemsComponent } from './components/upload-items/upload-items.component';
import { UserprofileComponent } from './components/userprofile/userprofile.component';
const routes: Routes = [
  {path: '',   redirectTo: 'home', pathMatch: 'full' }, // redirect to `first-component`
  {path:'signup', component:SignupComponent},
  {path:'login', component:LoginComponent},
  {path:'home', component:HomeComponent},
  {path:'productview', component:ProductviewComponent},
  {path:'uploadItems', component:UploadItemsComponent},
  {path:'profile', component:UserprofileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
 
exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents =[SignupComponent,LoginComponent];