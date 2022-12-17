import { Component } from '@angular/core';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent {
  ngOnInit(): void {
  }

  setEnable(){
    const x = document.getElementById('editable-Email');
    x?.removeAttribute('disabled');
    const y = document.getElementById('editable-Phone');
    y?.removeAttribute('disabled');
  }
  editValue(){
    const x = document.getElementById('editable-Email');
    x?.setAttribute('disabled', 'true');
    const y = document.getElementById('editable-Phone');
    y?.setAttribute('disabled', 'true');
  }
}
