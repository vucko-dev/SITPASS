import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ExerciseService } from '../../services/exercise.service';
import { UserService } from '../../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-exercise',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './add-exercise.component.html',
  styleUrl: './add-exercise.component.css'
})
export class AddExerciseComponent {

  data:any = {};
  note:string = '';
  facilityId:string|null = '';

  constructor(
    private exerciseService:ExerciseService,
    private route: ActivatedRoute,
    public dialogRef: MatDialogRef<AddExerciseComponent>
  ){};

  ngOnInit(): void {
    this.facilityId = localStorage.getItem('currentFacility');
    console.log(this.facilityId);
  }



  addExercise(){
    if(this.data.from == undefined || this.data.until == undefined || Number.isNaN(this.data.from[0]) ||  Number.isNaN(this.data.until[0])){
      this.note = 'Unesite neophodna polja';
      return;
    }
    this.note = '';
    this.data.from = this.getFormattedDate(this.data.from);
    this.data.until = this.getFormattedDate(this.data.until);
    this.data.facilityId = Number(this.facilityId);

    console.log(this.data);
    this.exerciseService.addExercise(this.data).subscribe(response => {
      console.log('Status Code:', response.status);
      console.log('Response Body:', response.body);
      this.dialogRef.close();
      window.location.reload();
    }, error => {
      console.log('Error Status Code:', error.status);
      console.log('Error Message:', error.message);
      this.note = 'Proverite unos ponovo.' + error.error.message;
    });
    

  }

  getFormattedDate(dateString:string){
    const date = new Date(dateString);

    const year = date.getFullYear();
    const month = date.getMonth()+1;
    const day = date.getDate();
    const hours = date.getHours();
    const minutes = date.getMinutes();


    const formattedDate = [
      year,
      month,
      day,
      hours,
      minutes
    ];

    return formattedDate;
  }
}
