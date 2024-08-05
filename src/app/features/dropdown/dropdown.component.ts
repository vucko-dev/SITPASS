import { CommonModule } from '@angular/common';
import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-dropdown',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dropdown.component.html',
  styleUrl: './dropdown.component.css'
})
export class DropdownComponent {
  @Input() options: string[] = [];
  @Input() placeholder: string = 'Select an option';
  @Output() selectionChange = new EventEmitter<string>();

  isOpen = false;
  selectedOption: string | null = null;

  toggleDropdown() {
    this.isOpen = !this.isOpen;
  }

  selectOption(option: string) {
    this.selectedOption = option;
    this.isOpen = false;
    this.selectionChange.emit(this.selectedOption);
  }
}
