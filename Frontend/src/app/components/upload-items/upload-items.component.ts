import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UploadItem } from './upload-item';
import { __values } from 'tslib';
@Component({
  selector: 'app-upload-items',
  templateUrl: './upload-items.component.html',
  styleUrls: ['./upload-items.component.css']
})
export class UploadItemsComponent {
  constructor(private httpClient: HttpClient) { }
   

  title = 'ImageUploaderFrontEnd';

  //public selectedFile;
  //public event1;
  imgURL: any;
  receivedImageData: any;
  base64Data: any;
  convertedImage: any;

  uploadItem = new UploadItem('', '', '', '');
   categories = [ 'cars','department', 'bikes','suit','dresses','electronic devices','others'];

   onSubmit() {
  
    console.log(' title: ' + this.uploadItem.title + ', description: ' + this.uploadItem.description+ 'price: '+ this.uploadItem.price + 'category '+this.uploadItem.category+"sss" );

    
  
  }
  

  /*public  onFileChanged(event) {
    console.log(event);
    this.selectedFile = event.target.files[0];

    // Below part is used to display the selected image
    let reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event2) => {
      this.imgURL = reader.result;
  };*/

}
 // This part is for uploading
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
