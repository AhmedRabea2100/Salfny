import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UploadItem } from './upload-item';
import { FormGroup, FormControl, Validators} from '@angular/forms';
@Component({
  selector: 'app-upload-items',
  templateUrl: './upload-items.component.html',
  styleUrls: ['./upload-items.component.css']
})

export class UploadItemsComponent {
  // constructor(private http: HttpClient) { }

  uploadItem = new UploadItem('', '', '', '');
  categories = [ 'cars','department', 'bikes','suit','dresses','electronic devices','others'];

//   imageSrc: string;
//   myForm = new FormGroup({
//    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
//    file: new FormControl('', [Validators.required]),
//    fileSource: new FormControl('', [Validators.required])
//  });
 
 
   
//  get f(){
//    return this.myForm.controls;
//  }
  
//  onFileChange(event) {
//    const reader = new FileReader();
   
//    if(event.target.files && event.target.files.length) {
//      const [file] = event.target.files;
//      reader.readAsDataURL(file);
   
//      reader.onload = () => {
  
//        this.imageSrc = reader.result as string;
    
//       /* this.myForm.patchValue({
//          fileSource: reader.result
//        });*/
  
//      };
  
//    }
//  }
  
//  submit(){
//    console.log(this.myForm.value);
//    this.http.post('http://localhost:8001/upload.php', this.myForm.value)
//      .subscribe(res => {
//        console.log(res);
//        alert('Uploaded Successfully.');
//      })
//  }


  

 

   onSubmit() {
  
    console.log(' title: ' + this.uploadItem.title + ', description: ' + this.uploadItem.description+ 'price: '+ this.uploadItem.price + 'category '+this.uploadItem.category+"sss" );  
  
  }
  
imageSrc: string;
myForm = new FormGroup({
 name: new FormControl('', [Validators.required, Validators.minLength(3)]),
 file: new FormControl('', [Validators.required]),
 fileSource: new FormControl('', [Validators.required])
});

constructor(private http: HttpClient) { }
 
get f(){
 return this.myForm.controls;
}

onFileChange(event) {
 const reader = new FileReader();
 
 if(event.target.files && event.target.files.length) {
   const [file] = event.target.files;
   reader.readAsDataURL(file);
 
   reader.onload = () => {

     this.imageSrc = reader.result as string;
  
     this.myForm.patchValue({
      //  fileSource: reader.result
     });

   };

 }
}

  

}

 
 /*onUpload() {


  const uploadData = new FormData();
  uploadData.append('myFile', this.selectedFile, this.selectedFile.name);


  this.httpClient.post('http://localhost:8080/check/upload', uploadData)
  .subscribe(
               res => {console.log(res);
                       this.receivedImageData = res;
                       this.base64Data = this.receivedImageData.pic;
                       this.convertedImage = 'data:image/jpeg;base64,' + this.base64Data; },
               err => console.log('Error Occured duringng saving: ' + err)
            );


 }
}*/
