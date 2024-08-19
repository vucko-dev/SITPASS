import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterModule, CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  logoUrl: string = 'assets/images/logo.png';

  email: string = '';
  password: string = '';
  firstName: string = '';
  lastName: string = '';
  phoneNumber: string = '';
  address: string = '';
  city: string = '';
  zipCode: string = '';
  birthday: string = '';


  constructor(private authService: AuthService, private router: Router, private appComponent:AppComponent) {}

  onRegister() {

    const formattedBirthday = this.birthday ? new Date(this.birthday).toISOString().split('T')[0] : '';

    this.authService
      .register(this.email, this.password, this.firstName, this.lastName, this.phoneNumber,this.address, this.city, this.zipCode, formattedBirthday)
      .subscribe({
        next: (response) => {
          console.log('Registration successful', response);
          this.appComponent.showMessage('Poslali ste zahtev za registraciju!', 'green');
          this.router.navigate(['/auth']); // Redirect to login page after successful registration
        },
        error: (err) => {
          console.error('Registration failed', err);
          if(err.error!=null){
            this.appComponent.showMessage('Doslo je do greske pri registraciji. Probajte ponovo. (' + err.error.message + ')', 'red');
          } else {
            this.appComponent.showMessage('Doslo je do greske pri registraciji. Probajte ponovo.', 'red');
          }
          // Handle registration error (e.g., show an error message)
        }
      });
  }
}
