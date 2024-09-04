import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-user-password',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './edit-user-password.component.html',
  styleUrl: './edit-user-password.component.css'
})
export class EditUserPasswordComponent {
  oldPassword:String = "";
  newPassword:String = "";
  newPasswordAgain:String = "";
  note:String = 'Nakon uspesne izmene bicete preusmereni na stranicu za prijavu. Sva polja moraju biti popunjena.';
  userData:any;

  constructor(
    private userService:UserService,
    public dialogRef: MatDialogRef<EditUserPasswordComponent>,
    private authService:AuthService,
    private router: Router
  ){}

  ngOnInit() {
    this.userService.getUserInfo().subscribe({
      next: (data) => {
        this.userData = data;
      },
      error: (err) => {
        console.error('Failed to load user info', err);
      }
    });
  }

  onUpdateUserPassword(){
    if(this.newPassword == this.newPasswordAgain){
      this.userData.password = this.newPassword;
      this.userService.updateUserPassword(this.userData.email, this.oldPassword, this.userData).subscribe({
        next: (response) => {
          console.log('User updated successfully', response);
          this.authService.logout();
          this.dialogRef.close();
          this.router.navigate(['/auth']);
        },
        error: (err) => {
          console.error('Failed to update user', err);
          this.note = 'Proverite lozinku';
        }
      });
    } else {
      this.note = 'Nova lozinka nije jednaka ponovljenoj.'
    }
  }

}
