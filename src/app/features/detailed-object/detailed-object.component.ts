import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';

@Component({
  selector: 'app-detailed-object',
  standalone: true,
  imports: [NavbarComponent, FooterComponent],
  templateUrl: './detailed-object.component.html',
  styleUrl: './detailed-object.component.css'
})
export class DetailedObjectComponent {

}
