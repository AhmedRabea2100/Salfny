import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Post } from 'src/types/post.type';
import Swal from 'sweetalert2';
import { DomSanitizer } from '@angular/platform-browser';


@Component({
  selector: 'app-user-posts',
  templateUrl: './user-posts.component.html',
  styleUrls: ['./user-posts.component.css', '../home/home.component.css']
})
export class UserPostsComponent {
  constructor(
    private router: Router,
    private http: HttpClient,
    private activatedRoute: ActivatedRoute,
    private sanitizer: DomSanitizer
  ) {}
  
  posts: Post[] | undefined;
  path: string = '/productview';
  ngOnInit() {
    const headerr = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: localStorage.getItem('token') + '',
    });

    this.http
      .get<Post[]>('http://localhost:8080/userProducts', { headers: headerr })
      .subscribe({
        next: (data: any) => {
          this.posts = data;
          console.log(data);
        },
        error: (error: any) => {
          if (error.status == 401) {
            this.posts = error.error;
            console.log(error.error);
          } else {
            console.error(error);
          }
        },
      });
  }
  
  view(id: number) {
    localStorage.setItem('post_id', id + '');
  }

}
