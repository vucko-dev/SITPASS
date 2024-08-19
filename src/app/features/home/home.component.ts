import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { ObjectComponent } from '../object/object.component';
import { ObjectsComponent } from '../objects/objects.component';
import { FooterComponent } from '../footer/footer.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from '../../app.component';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavbarComponent, ObjectComponent, ObjectsComponent, FooterComponent, RouterModule, CommonModule, HttpClientModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
