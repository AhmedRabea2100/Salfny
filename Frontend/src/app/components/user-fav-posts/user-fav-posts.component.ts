import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Post } from 'src/types/post.type';
import Swal from 'sweetalert2';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-user-fav-posts',
  templateUrl: './user-fav-posts.component.html',
  styleUrls: ['./user-fav-posts.component.css', '../home/home.component.css']
})
export class UserFavPostsComponent {
  constructor(
    private router: Router,
    private http: HttpClient,
    private activatedRoute: ActivatedRoute,
    private sanitizer: DomSanitizer
  ) { }
  searchWord: string;
  categoryName: string = 'All';
  posts: Post[] | undefined;
  logged: any;
  state: any;
  path: string = '/productview';

  ngOnInit() {
    if (localStorage.getItem("token") != null) {
      document.getElementById("userbtn").style.display = "initial"
      document.getElementById("signinBtn").style.display = "none";
    } else {
      document.getElementById("userbtn").style.display = "none";
      document.getElementById("signinBtn").style.display = "initial";
    }
    const headerr = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: localStorage.getItem('token') + '',
    });

    this.http
      .get<Post[]>('http://localhost:8080/userFavPosts', { headers: headerr })
      .subscribe({
        next: (data: any) => {
          this.state = 'Log Out';
          this.posts = data;
          console.log(data);
        },
        error: (error: any) => {
          this.state = 'Login';
          if (error.status == 401) {
            this.posts = error.error;
            console.log(error.error);
          } else {
            console.error(error);
          }
        },
      });
  }

  sell() {
    if (this.state == 'Login') {
      Swal.fire({
        position: 'center',
        icon: 'warning',
        title: 'Please, login first!',
        showConfirmButton: false,
        timer: 1500,
      });
      this.router.navigateByUrl('login');
    } else {
      this.router.navigateByUrl('uploadItems');
    }
  }

  log(state: string) {
    if (state == 'Log Out') {
      localStorage.removeItem('token');
      localStorage.removeItem('user_id');
    }
  }
  view(id: number) {
    localStorage.setItem('post_id', id + '');
  }
  sanitize(url: string) {
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }
  home() {
    this.router.navigateByUrl('home');
  }

  /*********************************************Search****************************************/
  search() {

    if ((<HTMLInputElement>document.getElementById("searchField")).value !== "") {
      localStorage.setItem("category", this.categoryName + "")
      this.searchWord = (<HTMLInputElement>document.getElementById("searchField")).value
      localStorage.setItem("searchWord", this.searchWord + "")
      this.router.navigateByUrl('search')
    }
  }
  unfav(id: Number, e: Event) {
    e.stopPropagation();
    Swal.fire({
      title: 'Are you sure?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, Unsave it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.http.post('http://localhost:8080/addfav', id + " " + localStorage.getItem("user_id"), { headers: new HttpHeaders({ 'Content-Type': 'application/json' }), responseType: 'text' }
        ).subscribe({
          next: (data: any) => {
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Unsaved',
              text: 'Your post has been unsaved',
              showConfirmButton: false,
              timer: 1500
            }).then(() => {
              this.ngOnInit();
            })

          },
          error: (error: any) => {
            console.error(error);
          }
        });

      }
    })


  }
}
