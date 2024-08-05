import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { ObjectComponent } from '../object/object.component';
import { DropdownComponent } from '../dropdown/dropdown.component';

@Component({
  selector: 'app-allobjects',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, ObjectComponent, DropdownComponent],
  templateUrl: './allobjects.component.html',
  styleUrl: './allobjects.component.css'
})
export class AllobjectsComponent {
  filterOptions = ['Option 1', 'Option 2', 'Option 3'];
  sortOptions = ['Name', 'Date', 'Priority'];

  citiesOptions = ['Beograd', 'Novi Sad', 'Subotica', 'Valjevo'];
  disciplinesOptions = ['Teretana', 'Personalni trening', 'Borilacke vestine'];
  lowGradeOptions = ['1','2','3','4','5','6','7','8','9','10'];
  highGradeOptions = ['1','2','3','4','5','6','7','8','9','10'];
  lowTimeOptions = ['00:00','01:00'];
  highTimeOptions = ['22:00','23:00'];

  onSelectionChange(selectedOption: string) {
    console.log('Selected Option:', selectedOption);
    // Handle the selected option
  }
}
