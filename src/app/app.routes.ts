import { Routes, RouterModule} from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { AuthComponent } from './features/auth/auth.component';
import { RegisterComponent } from './features/register/register.component';
import { AppComponent } from './app.component';
import { AllobjectsComponent } from './features/allobjects/allobjects.component';
import { DetailedObjectComponent } from './features/detailed-object/detailed-object.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'auth', component: AuthComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'objects', component: AllobjectsComponent },
  { path: 'object', component: DetailedObjectComponent },
  { path: '**', redirectTo: '' }  // Redirect any unknown paths to home
];
