import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatIconModule } from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { EditUserComponent } from '../../dialogs/edit-user/edit-user.component';
import { EditUserPasswordComponent } from '../../dialogs/edit-user-password/edit-user-password.component';


@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, MatTabsModule, MatIconModule, CommonModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {
  userInfo: any;
  userRoles: string[] = [];

  constructor(private userService: UserService, private dialog: MatDialog) {}

  async ngOnInit() {
    this.loadUserInfo();
    const role = await this.userService.getUserRole();
    // let dialogRef = this.dialog.open(EditUserComponent, {
    //   // height: '400px',
    //   // width: '600px',
    // });
    // console.log(role);
  }

  loadUserInfo() {
    this.userService.getUserInfo().subscribe({
      next: (data) => {
        this.userInfo = data;
      },
      error: (err) => {
        console.error('Failed to load user info', err);
      }
    });
  }

  onEditUser(){
    this.dialog.open(EditUserComponent,{});
  }

  onEditUserPassword(){
    this.dialog.open(EditUserPasswordComponent,{});
  }


}
