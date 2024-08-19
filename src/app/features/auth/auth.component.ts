import { Component } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [RouterModule, CommonModule, FormsModule],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css'
})
export class AuthComponent {
  logoUrl: string = 'assets/images/logo.png';
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router, private appComponent: AppComponent) {}

  onLogin() {
    this.authService.login(this.username, this.password).subscribe({
      next: (response) => {
        console.log('Login successful', response);
        this.appComponent.showMessage('Uspesno ste se prijavili!', 'green');
        this.router.navigate(['/home']); // Redirect to home on success
      },
      error: (err) => {
        console.error('Login failed', err);
        this.appComponent.showMessage('Proverite unos. Takodje postoji mogucnost da vas nalog jos nije odobren od strane administracije.', 'red');
        // Handle login error (e.g., show an error message)
      }
    });
  }

}
