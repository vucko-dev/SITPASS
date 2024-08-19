import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

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

  onUpdateUserPassword(){

  }

}
