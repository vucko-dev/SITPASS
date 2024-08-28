import { CommonModule } from '@angular/common';
import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-multi-select-dropdown',
  templateUrl: './multi-select-dropdown.component.html',
  styleUrls: ['./multi-select-dropdown.component.css'],
  imports:[CommonModule],
  standalone:true
})
export class MultiSelectDropdownComponent {
  @Input() options: string[] = [];
  @Input() placeholder: string = 'Select...';
  @Output() selectionChange: EventEmitter<string[]> = new EventEmitter<string[]>();

  isOpen = false;
  selectedOptions: string[] = [];

  toggleDropdown() {
    this.isOpen = !this.isOpen;
  }

  toggleSelection(option: string) {
    const index = this.selectedOptions.indexOf(option);
    if (index > -1) {
      this.selectedOptions.splice(index, 1);
    } else {
      this.selectedOptions.push(option);
    }
    this.selectionChange.emit(this.selectedOptions);
  }

  isSelected(option: string): boolean {
    return this.selectedOptions.includes(option);
  }

  resetSelection() {
    this.selectedOptions = [];
    this.selectionChange.emit(this.selectedOptions);
  }
}
