import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from 'src/types/post.type';
@Component({
  selector: 'app-productview',
  templateUrl: './productview.component.html',
  styleUrls: ['./productview.component.css']
})
export class ProductviewComponent {
  photo: any
  Name:any
  description:any
  price:any
  constructor(private router: Router, private route: ActivatedRoute, private http: HttpClient) { }
  ngOnInit(): void {
   
    this.http.post<Post>('http://localhost:8080/post',localStorage.getItem("post_id")
    ) .subscribe({
      next: (data: Post) => {
        this.photo=data.photos[0];
        this.Name=data.title;
        this.description=data.description
        this.price=data.price
      },
      error: (error: any) => {
        
          console.error(error);

      }
      });
  }
 
}

