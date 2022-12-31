import { Component } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { UploadItem } from './upload-item';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
@Component({
  selector: 'app-upload-items',
  templateUrl: './upload-items.component.html',
  styleUrls: ['./upload-items.component.css']
})

export class UploadItemsComponent {

  //selectedFile : File = null

  constructor(private http: HttpClient, private router: Router) { }


  uploadItem = new UploadItem('', '', 0, 0, '', Number(localStorage.getItem("user_id")));
  categories = ['cars', 'department', 'bikes', 'suit', 'dresses', 'electronic devices', 'others'];
  categoryName: string;

  imageSrc: string;
  imageName: string;
  imageBlob: string;
  myForm = new FormGroup({
    name: new FormControl(''),
    file: new FormControl(''),
    fileSource: new FormControl('')
  });



  get f() {
    return this.myForm.controls;
  }

  onFileChange(event: any) {
    console.log(event)
    const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);

      reader.onload = () => {

        this.imageName = file.name
        this.imageBlob = file
        this.imageSrc = reader.result as string;
        this.uploadItem.photo = this.imageSrc
        console.log(reader.result)

        this.myForm.patchValue({ fileSource: reader.result as string });

      };

    }
  }



  submit() {

    this.uploadItem.category_id = this.categories.indexOf(this.categoryName)
    if (this.uploadItem.title.length < 3) {
      Swal.fire({
        position: 'center',
        icon: 'warning',
        title: 'Title must be at least 3 characters',
        showConfirmButton: false,
        timer: 2000
      })
      return;
    }
    if (this.uploadItem.description == "") {
      Swal.fire({
        position: 'center',
        icon: 'warning',
        title: 'Please provide a detailed description about your product!',
        showConfirmButton: false,
        timer: 2000
      })
      return;
    }
    if (this.uploadItem.price == 0) {
      Swal.fire({
        position: 'center',
        icon: 'warning',
        title: 'Price can not be zero',
        showConfirmButton: false,
        timer: 2000
      })
      return;
    }
    if (this.uploadItem.category_id == -1) {
      Swal.fire({
        position: 'center',
        icon: 'warning',
        title: 'You must choose a category',
        showConfirmButton: false,
        timer: 2000
      })
      return;
    }
    if (this.uploadItem.photo == "") {
      Swal.fire({
        position: 'center',
        icon: 'warning',
        title: 'You must provide a photo for your product',
        showConfirmButton: false,
        timer: 2000
      })
      return;
    }



    const headerr = new HttpHeaders({ 'Content-Type': 'application/json' });
    this.http.post('http://localhost:8080/upload', this.uploadItem, { headers: headerr, responseType: 'text' })
      .subscribe({

        next: (data: any) => {
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Post has been added',
            showConfirmButton: false,
            timer: 1500
          })
          this.router.navigateByUrl('home')
        },
        error: (error: any) => {
          console.error(error);
        }
      });
  }



  onSubmit() {
    this.uploadItem.category_id = this.categories.indexOf(this.categoryName)

    console.log(' title: ' + this.uploadItem.title + ', description: ' + this.uploadItem.description + 'price: ' + this.uploadItem.price + 'category ' + this.uploadItem.category_id + "sss");
  }
}