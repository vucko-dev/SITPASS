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
            user.hasManagerRight = false;  // or handle the error as needed
          }
        });
      });
      // console.log(this.users);
    }, error => {
      console.log('Error Status Code:', error.status);
      console.log('Error Message:', error.message);
      this.note = "Greska";
    });
  }
  

  loadAdmin(){
    this.userService.getUserInfo().subscribe(response => {
      // console.log('Status Code:', response.status);
      // console.log('Response Body:', response.body);
      this.adminId = response.id;
      this.loadAllUsers();
      // console.log(this.adminId);
    }, error => {
      console.log('Error Status Code:', error.status);
      console.log('Error Message:', error.message);
      this.note = "Greska";
    });
  }

  addManages(startTime:string, endTime:string, userId:string){
    console.log(startTime);
    console.log(endTime);
    console.log(userId);
    // console.log
    this.managesService.addManages({
      startTime:startTime,
      endTime:endTime,
      userId:userId,
      facilityId:Number(localStorage.getItem('currentFacility'))
    }).subscribe(response => {
      // console.log('Status Code:', response.status);
      // console.log('Response Body:', response.body);
      // this.adminId = response.id;
      // this.loadAllUsers();
      // console.log(this.adminId);
      window.location.reload();
    }, error => {
      console.log('Error Status Code:', error.status);
      console.log('Error Message:', error.message);
      this.note = error.error.message;
    });
  }

  removeManages(){
    
  }
}
