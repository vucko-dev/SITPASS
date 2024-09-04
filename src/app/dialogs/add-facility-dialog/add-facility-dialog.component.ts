import { Component } from '@angular/core';
import { FacilityService } from '../../services/facility.service';
import { Router } from '@angular/router';
import { MatDialogRef } from '@angular/material/dialog';
import { DisciplineService } from '../../services/discipline.service';
import { WorkDay } from '../../models/interfaces';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-facility-dialog',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './add-facility-dialog.component.html',
  styleUrl: './add-facility-dialog.component.css'
})
export class AddFacilityDialogComponent {
  facilityData: any = {
    active:false,
    totalRating:0,
    disciplines: [],
    workDays: [
      {
        dayOfWeek:'MONDAY',
        from:"",
        until:"",
      },
      {
        dayOfWeek:'TUESDAY',
        from:"",
        until:"",
      },
      {
        dayOfWeek:'WEDNESDAY',
        from:"",
        until:"",
      },
      {
        dayOfWeek:'THURSDAY',
        from:"",
        until:"",
      },
      {
        dayOfWeek:'FRIDAY',
        from:"",
        until:"",
      },
      {
        dayOfWeek:'SATURDAY',
        from:"",
        until:"",
      },
      {
        dayOfWeek:'SUNDAY',
        from:"",
        until:"",
      },
    ],
    images: []
  };
  selectedFiles: File[] = [];

  dayOrder = [
    'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'
  ];

  allDisciplines: any;

  note:string = '';

  responseAfterSuccess:any;


  constructor(private facilityService: FacilityService, private router: Router, 
              public dialogRef: MatDialogRef<AddFacilityDialogComponent>,
              private disciplineService: DisciplineService) { }


  ngOnInit(){
    this.disciplineService.getDisciplines().subscribe(data => {
      this.allDisciplines = data;
    });
  }

  padTime(value: number): string {
    return value.toString().padStart(2, '0');
  }

  onFileSelected(event: any): void {
    this.selectedFiles = Array.from(event.target.files);
  }

  uploadImages(facilityId: number): void {
    this.facilityService.addImages(facilityId, this.selectedFiles).subscribe(
      (response) => {
        console.log('Images uploaded successfully', response);
        this.dialogRef.close();
        window.location.reload();
      },
      (error) => {
        console.error('Error uploading images', error);
        this.note = 'Greska pri dodavanju slika, teretana je kreirana ali bez slika';
      }
    );
  }

  isDisciplineSelected(disciplineId: number): boolean {
    return this.facilityData.disciplines.some((discipline: any) => discipline.id === disciplineId);
  }

  onDisciplineToggle(discipline: any) {
    const index = this.facilityData.disciplines.findIndex((d: any) => d.id === discipline.id);
    if (index > -1) {
      this.facilityData.disciplines.splice(index, 1);
    } else {
      this.facilityData.disciplines.push(discipline);
    }  }

  onAddFacility(){
    console.log(this.facilityData);
    if(this.facilityData.name=="" || this.facilityData.description =="" || this.facilityData.address==""||this.facilityData.city==""){
      this.note="Popunite sva polja";
      return;
    }
    if(this.facilityData.disciplines.length==0){
      this.note = 'Odaberite barem jednu disciplinu';
      return;
    }
    if(this.selectedFiles.length < 2){
      this.note = 'Da bi ste dodali teretanu morate dodati barem dve slike';
      return;
    }
    this.facilityData.workDays.forEach((element: any) => {
        if(element.from=="" || element.until==""){
          this.note = "Popunite sve termine za radno vreme teretane";
          return;
        }
    });
    this.facilityService.addFacility(this.facilityData).subscribe(
      (response) => {
        this.responseAfterSuccess = response.body;
        console.log("Facility created!");
        console.log(this.responseAfterSuccess);
        this.uploadImages(this.responseAfterSuccess.id);
      },
      (error) => {
        this.note = error.error.message;
        console.error('Error creating facility', error);
      }
    );

  }

  sortWorkdays():void{
    this.facilityData.workDays =  this.facilityData.workDays.sort((a:WorkDay, b:WorkDay) => {
      return this.dayOrder.indexOf(a.dayOfWeek) - this.dayOrder.indexOf(b.dayOfWeek);
    });
  }

}
