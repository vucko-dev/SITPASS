import { Component } from '@angular/core';
import { FacilityService } from '../../services/facility.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-delete-facility-dialog',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './delete-facility-dialog.component.html',
  styleUrl: './delete-facility-dialog.component.css'
})
export class DeleteFacilityDialogComponent {


  note:string = 'Brisanje je trajno.';

  constructor(
    private facilityService:FacilityService,
    public dialogRef: MatDialogRef<DeleteFacilityDialogComponent>,
    private route: ActivatedRoute,
    private router: Router

  ){
  }

  deleteFacility(){
    this.facilityService.deleteFacilityById(Number(localStorage.getItem('currentFacility'))).subscribe(response => {
      console.log('Status Code:', response.status);
      console.log('Response Body:', response.body);
      this.dialogRef.close();
      this.router.navigate(['/']);
    }, error => {
      console.log('Error Status Code:', error.status);
      console.log('Error Message:', error.message);
      this.note = error.message;
    });
  }

  onClose(){
    this.dialogRef.close();
  }
}
