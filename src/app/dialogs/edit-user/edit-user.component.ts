import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { MatDialogRef } from '@angular/material/dialog';
import { AppComponent } from '../../app.component';
import { Injector } from '@angular/core';


@Component({
  selector: 'app-edit-user',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './edit-user.component.html',
  styleUrl: './edit-user.component.css',

})
export class EditUserComponent implements OnInit {
  userData: any = {};

  constructor(private userService: UserService, private router: Router, private authService: AuthService, public dialogRef: MatDialogRef<EditUserComponent>) {
  }

  ngOnInit() {
    this.userService.getUserInfo().subscribe({
      next: (data) => {
        this.userData = data;
        // Convert birthday array to string format 'yyyy-MM-dd'
        if (this.userData.birthday && Array.isArray(this.userData.birthday)) {
          this.userData.birthday = this.convertArrayToDate(this.userData.birthday);
        }
      },
      error: (err) => {
        console.error('Failed to load user info', err);
      }
    });
  }
  
  convertArrayToDate(dateArray: number[]): string {
    // Convert array [yyyy, mm, dd] to 'yyyy-MM-dd' format
    const year = dateArray[0];
    const month = String(dateArray[1]).padStart(2, '0');
    const day = String(dateArray[2]).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }
  

  onUpdateUser() {
    // Convert 'yyyy-MM-dd' to [yyyy, mm, dd] array format if needed
    if (this.userData.birthday && typeof this.userData.birthday === 'string') {
      this.userData.birthday = this.convertDateToArray(this.userData.birthday);
    }
  
    this.userService.updateUser(this.userData).subscribe({
      next: (response) => {
        console.log('User updated successfully', response);
        this.authService.logout();
        this.dialogRef.close();
        // this.appComponent.showMessage('Uspesno ste izmenili profil. Vraceni ste na stranicu za prijavu.', 'green');
        this.router.navigate(['/auth']);
      },
      error: (err) => {
        console.error('Failed to update user', err);
      }
    });
  }
  
  convertDateToArray(dateString: string): number[] {
    const [year, month, day] = dateString.split('-').map(Number);
    return [year, month, day];
  }
}