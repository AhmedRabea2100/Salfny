import { Component } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { UploadItem } from './upload-item';
import { FormGroup, FormControl, Validators } from '@angular/forms';
@Component({
  selector: 'app-upload-items',
  templateUrl: './upload-items.component.html',
  styleUrls: ['./upload-items.component.css']
})

export class UploadItemsComponent {

  //selectedFile : File = null

  constructor(private http: HttpClient) { }


  uploadItem = new UploadItem('', '', 0, 0, '',1);
  categories = ['cars', 'department', 'bikes', 'suit', 'dresses', 'electronic devices', 'others'];
  categoryName:string;
  
  imageSrc: string;
  imageName: string;
  imageBlob: string;
  myForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    file: new FormControl('', [Validators.required]),
    fileSource: new FormControl('')
  });



  get f() {
    return this.myForm.controls;
  }

  onFileChange(event) {
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
        console.log(this.myForm.value)

      };

    }
  }



  submit() {
    this.uploadItem.category =this.categories.indexOf(this.categoryName)
    
    const headerr = new HttpHeaders({ 'Content-Type': 'application/json' });
    this.http.post('http://localhost:8080/upload', this.uploadItem, { headers: headerr, responseType: 'text' })
      .subscribe({

        next: (data: any) => {
          console.log("hii")
          console.log(data)
        },
        error: (error: any) => {
          console.error(error);
        }
      });
  }
  


  onSubmit() {this.uploadItem.category =this.categories.indexOf(this.categoryName)

    console.log(' title: ' + this.uploadItem.title + ', description: ' + this.uploadItem.description + 'price: ' + this.uploadItem.price + 'category ' + this.uploadItem.category + "sss");
  }
}