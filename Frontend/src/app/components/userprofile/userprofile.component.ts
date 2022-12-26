import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { User } from 'src/app/models/User';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css'],
})
export class UserprofileComponent {
  constructor(private http: HttpClient) {}

  user!: User;
  ngOnInit() {
    const header = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: localStorage.getItem('token') + '',
    });

    this.http.get<User>('http://localhost:8080/profile', { headers: header })
      .subscribe({
        next: (data: User) => {
          this.user = data;
        },
        error: (error: any) => {
          console.error(error);
        },
      });
  }

  setEnable() {
    const x = document.getElementById('editable-Email');
    x?.removeAttribute('disabled');
    const y = document.getElementById('editable-Phone');
    y?.removeAttribute('disabled');
    const z = document.getElementById('saveBtn');
    z?.removeAttribute('disabled');
  }
  editValue() {
    const x = document.getElementById('editable-Email');
    x?.setAttribute('disabled', 'true');
    const y = document.getElementById('editable-Phone');
    y?.setAttribute('disabled', 'true');
    const z = document.getElementById('saveBtn');
    z?.setAttribute('disabled', 'true');
    this.save();
  }

  save() {
    const header = new HttpHeaders({
      'Content-Type': 'application/json',
      authentication: 'key',
    });
    this.http.post('http://localhost:8080/profile', this.user, {
        headers: header,
        responseType: 'text',
      })
      .subscribe({
        next: (data: any) => {
          console.log(data);
          if (data === 'Saved') {
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: "Saved",
              showConfirmButton: false,
              timer: 1500
            })
          }
        },
        error: (error: any) => {
          console.error(error);
        },
      });
  }
}
