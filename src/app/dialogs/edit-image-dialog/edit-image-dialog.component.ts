import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-edit-image-dialog',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './edit-image-dialog.component.html',
  styleUrl: './edit-image-dialog.component.css'
})
export class EditImageDialogComponent {
  note:string = '';

  selectedFile: File | null = null;  // Declare the type

  constructor(
    private userService:UserService,
  ){}

  onUpdateImage(): void {
    if (this.selectedFile) {
      this.userService.changeImage(this.selectedFile).subscribe({
        next: (response) => {
          console.log('User updated successfully', response);
          window.location.reload();
        },
        error: (err) => {
          console.error('Failed to change user image', err);
          this.note = 'Greska pri promeni slike';
        }
      });
    } else {
      console.error('No file selected');
      this.note = 'Please select a file to upload.';
    }
  }


  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
  
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0] as File;  // Explicitly cast to File
    } else {
      console.error('No file selected');
      this.selectedFile = null;
    }
  }
}
