import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ManagesService } from '../../services/manages.service';

@Component({
  selector: 'app-edit-facility-manages',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-facility-manages.component.html',
  styleUrl: './edit-facility-manages.component.css'
})
export class EditFacilityManagesComponent {

  constructor(
    private userService:UserService,
    private managesService:ManagesService
  ){}

  users:any;
  adminId:number = 0;
  note:string = '';

  ngOnInit(){
    this.loadAdmin();
  }

  loadAllUsers() {
    this.userService.getAllUsers().subscribe(response => {
      this.users = response;
      this.users = this.users.filter((user: { id: any; }) => user.id !== this.adminId);
  
      this.users.forEach((user: { id: number; hasManagerRight: boolean; }) => {
        this.managesService.hasRightsByUserIdAndFacilityId(user.id, Number(localStorage.getItem('currentFacility'))).subscribe({
          next: (res) => {
            console.log(res);
            user.hasManagerRight = res.body === true;
          },
          error: (err) => {
            console.error(`Failed to check manager rights for user ID ${user.id}`, err);
            this.note = "Greska";
            user.hasManagerRight = false;
          }
        });
      });
    }, error => {
      console.log('Error Status Code:', error.status);
      console.log('Error Message:', error.message);
      this.note = "Greska";
    });
  }
  

  loadAdmin(){
    this.userService.getUserInfo().subscribe(response => {
      this.adminId = response.id;
      this.loadAllUsers();
    }, error => {
      console.log('Error Status Code:', error.status);
      console.log('Error Message:', error.message);
      this.note = "Greska";
    });
  }

  addManages(startTime:string, endTime:string, userId:string){
    this.managesService.addManages({
      startTime:startTime,
      endTime:endTime,
      userId:userId,
      facilityId:Number(localStorage.getItem('currentFacility'))
    }).subscribe(response => {
      window.location.reload();
    }, error => {
      console.log('Error Status Code:', error.status);
      console.log('Error Message:', error.message);
      this.note = error.error.message;
    });
  }

  removeManages(userId:number){
    var facilityId:number = localStorage.getItem('currentFacility')?Number(localStorage.getItem('currentFacility')):0;
    this.managesService.deleteManagesByUserIdAndFacilityId(userId,facilityId).subscribe(response => {
      window.location.reload();
    }, error => {
      console.log('Error Status Code:', error.status);
      console.log('Error Message:', error.message);
      this.note = error.error.message;
    });
  }
}
