import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { ObjectComponent } from '../object/object.component';
import { ObjectsComponent } from '../objects/objects.component';
import { FooterComponent } from '../footer/footer.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavbarComponent, ObjectComponent, ObjectsComponent, FooterComponent, RouterModule, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
