import { Component } from '@angular/core';
import { RouterOutlet, RouterModule } from '@angular/router';
import { NavbarComponent } from './features/navbar/navbar.component';
import { AuthComponent } from './features/auth/auth.component';
import { RegisterComponent } from './features/register/register.component';
import { MatTabsModule } from '@angular/material/tabs';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { MessageComponent } from './shared/message/message.component';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule, NavbarComponent, AuthComponent, RegisterComponent, MatTabsModule, HttpClientModule, MessageComponent, CommonModule],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'my-angular-app';
  globalMessage: { text: string, color: 'red' | 'green' | 'gray' } | null = null;

  showMessage(text: string, color: 'red' | 'green' | 'gray') {
    this.globalMessage = { text, color };
    setTimeout(() => {
      this.clearMessage();
    }, 3000); 
  }

  clearMessage() {
    this.globalMessage = null;
  }
}
