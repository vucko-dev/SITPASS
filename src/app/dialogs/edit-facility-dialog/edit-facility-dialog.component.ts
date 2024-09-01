import { Component, OnInit } from '@angular/core';
import { FacilityService } from '../../services/facility.service';
import { Router } from '@angular/router';
import { MatDialogRef } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DisciplineService } from '../../services/discipline.service';
import { WorkDay } from '../../models/interfaces';
@Component({
  selector: 'app-edit-facility-dialog',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-facility-dialog.component.html',
  styleUrls: ['./edit-facility-dialog.component.css'],
})
export class EditFacilityDialogComponent implements OnInit {
  facilityData: any = {
    disciplines: [],
    workDays: [],
    images: []
  };
  selectedFiles: File[] = [];

  dayOrder = [
    'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'
  ];

  allDisciplines: any;

  note:string = '';

  constructor(private facilityService: FacilityService, private router: Router, 
              public dialogRef: MatDialogRef<EditFacilityDialogComponent>,
              private disciplineService: DisciplineService) { }

  ngOnInit() {
    this.facilityService.getFacilityById(Number(localStorage.getItem('currentFacility'))).subscribe(data => {
      this.facilityData = data;
      this.facilityData.workdays = this.facilityData.workdays.map((workday: { id: number; validFrom: number[]; dayOfWeek: string; from: number[]; until: number[] }) => {
        return {
          ...workday,
          from: `${this.padTime(workday.from[0])}:${this.padTime(workday.from[1])}`,
          until: `${this.padTime(workday.until[0])}:${this.padTime(workday.until[1])}`
        };
      });
      // console.log(data);
      this.sortWorkdays();
    });

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
    if(this.selectedFiles.length == 1){
      this.note = 'Izmene teretane su uspesne osim izmene slika. Da bi slike uspele da se sacuvaju, moraju biti prilozene najmanje dve slike';
      return;
    }
    if(this.selectedFiles.length == 0){
      this.dialogRef.close();
      window.location.reload();
      return;
    }
    this.facilityService.addImages(facilityId, this.selectedFiles).subscribe(
      (response) => {
        console.log('Images uploaded successfully', response);
        this.dialogRef.close();
        window.location.reload();
      },
      (error) => {
        console.error('Error uploading images', error);
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
    }
    // console.log(this.facilityData.disciplines);
  }

  onUpdateFacility() {
    console.log(this.facilityData);
    this.facilityData.workDays = this.facilityData.workdays;
    this.facilityService.updateFacility(Number(localStorage.getItem('currentFacility')), this.facilityData).subscribe({
      next: (response) => {
        console.log('Facility updated successfully', response);
        this.uploadImages(Number(localStorage.getItem('currentFacility')));
      },
      error: (err) => {
        console.error('Failed to update facility', err);
      }
    });
  }

  sortWorkdays():void{
    this.facilityData.workdays =  this.facilityData.workdays.sort((a:WorkDay, b:WorkDay) => {
      return this.dayOrder.indexOf(a.dayOfWeek) - this.dayOrder.indexOf(b.dayOfWeek);
    });
  }
}
