import { Component } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  logoUrl: string = 'assets/images/logo.png';

  constructor(private authService: AuthService, private router: Router) {}

  onLogout() {
    this.authService.logout();
    console.log('Logout successful');
    this.router.navigate(['/auth']);
  }
}
