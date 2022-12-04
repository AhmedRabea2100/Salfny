import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { global } from 'src/app/global';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor (private router: Router,private http: HttpClient,private activatedRoute: ActivatedRoute) {}

   ngOnInit () {
    const headerr=new HttpHeaders({'Content-Type': 'application/json' ,'Cookie':global.tokenn + "; Path=/; Expires=Mon, 04 Dec 2023 16:38:56 GMT;" });
    this.http.get('http://localhost:8080/home',{ headers: headerr, responseType:'arraybuffer'}
  
    ) .subscribe({
      next: (data: any) => {
          console.log(global.tokenn);
          console.log(data)
          console.log("hii")
          },
          error: (error: any) => {
          console.error(error);
          }
      });
}


}
