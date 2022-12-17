import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { global } from 'src/app/global';
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
    this.photo = global.photo
    this.Name=global.Name
    this.description=global.description
    this.price=global.price
  }
 
}

