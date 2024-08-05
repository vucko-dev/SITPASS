import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-object',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './object.component.html',
  styleUrl: './object.component.css'
})
export class ObjectComponent {
  // objectImg: string = 'assets/images/object_img.jpg';
  @Input() title: string = 'Naziv objekta';
  @Input() services: string = 'Usluge objekta';
  @Input() objectImg: string = 'assets/images/object_img.jpg';
  @Input() grade: string = '5.0';
}
