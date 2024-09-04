import { Routes, RouterModule} from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { AuthComponent } from './features/auth/auth.component';
import { RegisterComponent } from './features/register/register.component';
import { AppComponent } from './app.component';
import { AllobjectsComponent } from './features/allobjects/allobjects.component';
import { DetailedObjectComponent } from './features/detailed-object/detailed-object.component';
import { ProfileComponent } from './features/profile/profile.component';
import { AuthGuard } from './guards/auth.guard';
import { AuthPageGuard } from './guards/auth-page.guard';


export const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'auth', component: AuthComponent, canActivate: [AuthPageGuard] },
  { path: 'register', component: RegisterComponent,canActivate: [AuthPageGuard] },
  { path: 'objects', component: AllobjectsComponent, canActivate: [AuthGuard] },
  { path: 'object/:id', component: DetailedObjectComponent, canActivate: [AuthGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '' } 
];
