import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
//for forms


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClient, HttpClientModule} from '@angular/common/http'
import { SignupComponent } from './components/signup/signup.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { ProductviewComponent } from './components/productview/productview.component';
import { UploadItemsComponent } from './components/upload-items/upload-items.component';
import { UserprofileComponent } from './components/userprofile/userprofile.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserPostsComponent } from './components/user-posts/user-posts.component';
import { UserFavPostsComponent } from './components/user-fav-posts/user-fav-posts.component';
import { SearchComponent } from './components/search/search.component';
import { AngularMultiSelectModule } from 'angular2-multiselect-dropdown';


@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    ProductviewComponent,
    UploadItemsComponent,
    UserprofileComponent,
    UserPostsComponent,
    UserFavPostsComponent,
    UserprofileComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    AngularMultiSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
