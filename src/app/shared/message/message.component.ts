import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-message',
  standalone: true,
  imports:[CommonModule],
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css'],
})
export class MessageComponent {
  @Input() message: string = '';
  @Input() color: 'red' | 'green' | 'gray' = 'gray';
  isVisible: boolean = true;
  colorClass: string = 'gray';

  ngOnInit() {
    this.colorClass = this.color;
    // setTimeout(() => {
    //   this.closeMessage();
    // }, 3000); // Message will disappear after 5 seconds
  }

  closeMessage() {
    this.isVisible = false;
  }
}
