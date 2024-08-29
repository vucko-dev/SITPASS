import { Component, Input } from '@angular/core';
import { ObjectComponent } from '../object/object.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-objects',
  standalone: true,
  imports: [ObjectComponent, RouterModule, CommonModule],
  templateUrl: './objects.component.html',
  styleUrl: './objects.component.css'
})
export class ObjectsComponent {
  @Input() title: string = 'Naziv sekcije';
  @Input() data:any[] = [];

  // ngOnInit():void{
  //   console.log(this.data);
  // }

  transformDisciplinesToString(disciplines: any[]): string {
    return disciplines.map(d => d.name).join(", ");
  }
}
