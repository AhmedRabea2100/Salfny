import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { Post } from 'src/types/post.type';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-productview',
  templateUrl: './productview.component.html',
  styleUrls: ['./productview.component.css']
})
export class ProductviewComponent {
  searchWord: string;
  categories = ['All', 'cars', 'department', 'bikes', 'suit', 'dresses', 'electronic devices', 'others'];
  categoryName: string = this.categories[0]
  photo: any
  Name: any
  description: any
  price: any
  address: any
  date: any
  username: any
  userPic: any
  memberSince: any
  user_id: any
  state: any
  fav: any
  catid: any
  phone: any;
  path: string = '/productview';
  topPosts: Post[];

  constructor(private router: Router, private route: ActivatedRoute, private http: HttpClient) { }
  ngOnInit(): void {
    if (localStorage.getItem("token") != null) {
      document.getElementById("userbtn").style.display = "initial"
      document.getElementById("signinBtn").style.display = "none";
    } else {
      document.getElementById("userbtn").style.display = "none";
      document.getElementById("signinBtn").style.display = "initial";
    }

    this.http.post<Post>('http://localhost:8080/post', localStorage.getItem("post_id") + " " + localStorage.getItem("user_id")
    ).subscribe({
      next: (data: Post) => {
        this.photo = data.photos[0];
        this.Name = data.title;
        this.description = data.description
        this.price = data.price
        this.address = data.address
        this.user_id = data.user_id
        this.date = data.date[0] + "/" + data.date[1] + "/" + data.date[2]
        this.catid = data.category_id;
        this.http.post<Post[]>('http://localhost:8080/search', " @" + this.categories[this.catid + 1], { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'authentication': 'key' }) }).subscribe({
          next: (data: Post[]) => {
            this.topPosts = new Array();
            for (let i in data) {
              if (data[i].id + "" != localStorage.getItem("post_id")) {
                this.topPosts.push(data[i])
              }
            }
            if (this.topPosts.length == 0) {
              document.getElementById("norelated").style.display = "block";
            } else {
              document.getElementById("norelated").style.display = "none";
            }
          },
          error: (error: any) => {
            console.error(error);
          }
        });



        this.http.post<string>('http://localhost:8080/isfav', localStorage.getItem("post_id") + " " + localStorage.getItem("user_id")
        ).subscribe({
          next: (data) => {
            if (data !== null) {
              this.fav = true
            } else {
              this.fav = false;
            }
          },
          error: (error) => {

            console.error(error)
          }
        });




        this.http.post<User>('http://localhost:8080/user', this.user_id
        ).subscribe({
          next: (data: User) => {
            this.username = data.username
            this.userPic = data.profilePic
            this.memberSince = data.memberSince[0] + "/" + data.memberSince[1] + "/" + data.memberSince[2]
            this.phone = data.phoneNumber
          },
          error: (error: any) => {

            console.error(error);

          }
        });
      },
      error: (error: any) => {

        console.error(error);

      }
    });


  }

  addToFav() {
    var star = document.getElementById("star");


    if (star.className == "active") {
      star.className = "";
    } else {
      star.className = "active";
    }

    if (localStorage.getItem("user_id") == null) {
      Swal.fire({
        position: 'center',
        icon: 'warning',
        title: 'Please, login first!',
        showConfirmButton: false,
        timer: 1500
      })
      this.router.navigateByUrl('login')
    }
    else {


      this.http.post('http://localhost:8080/addfav', localStorage.getItem("post_id") + " " + localStorage.getItem("user_id"), { headers: new HttpHeaders({ 'Content-Type': 'application/json' }), responseType: 'text' }
      ).subscribe({
        next: (data) => {
          console.log(data)
        },
        error: (error: any) => {
          console.error(error);

        }
      });
    }
  }

  sell() {
    if (this.state == "Login") {
      Swal.fire({
        position: 'center',
        icon: 'warning',
        title: 'Please, login first!',
        showConfirmButton: false,
        timer: 1500
      })
      this.router.navigateByUrl('login')
    } else {
      this.router.navigateByUrl('uploadItems')
    }
  }

  log(state: string) {
    if (state == "Log Out") {
      localStorage.removeItem("token");
      localStorage.removeItem("user_id");
    }
  }
  view(id: number) {
    console.log(id);
    localStorage.setItem("post_id", id + "")
    location.reload();
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
  /**************************************category********************************/
  category() {
    if (this.categoryName != 'All') {
      localStorage.setItem("category", this.categoryName + "")
      localStorage.setItem("isCategory", true + "")
      this.router.navigateByUrl('search')
    }
    console.log(this.categoryName);
  }
  home() {
    this.router.navigateByUrl('home');
  }
}

