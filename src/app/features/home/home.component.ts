import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { ObjectComponent } from '../object/object.component';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavbarComponent, ObjectComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
