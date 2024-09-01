import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatIconModule } from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { EditUserComponent } from '../../dialogs/edit-user/edit-user.component';
import { EditUserPasswordComponent } from '../../dialogs/edit-user-password/edit-user-password.component';
import { EditImageDialogComponent } from '../../dialogs/edit-image-dialog/edit-image-dialog.component';
import { ExerciseService } from '../../services/exercise.service';
import { FacilityService } from '../../services/facility.service';
import { forkJoin, map, mergeMap } from 'rxjs';
import { ReviewService } from '../../services/review.service';
import { ManagesService } from '../../services/manages.service';


@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, MatTabsModule, MatIconModule, CommonModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {
  userInfo: any;
  userRoles: string[] = [];
  exercises:any;
  reviews:any;
  manages:any;
  isManages:boolean = false;

  constructor(private userService: UserService, 
    private dialog: MatDialog, 
    private exerciseService:ExerciseService, 
    private facilityService:FacilityService,
    private reviewService: ReviewService,
    private managesService: ManagesService
  ) {}

  async ngOnInit() {
    this.loadUserInfo();
    const role = await this.userService.getUserRole();
    this.loadExercises();
    this.loadReviews();
    this.isManages = role == 'MANAGER';
    if(this.isManages == true){
      this.loadManages();
    }
    // console.log(this.isManages);
    // let dialogRef = this.dialog.open(EditUserComponent, {
    //   // height: '400px',
    //   // width: '600px',
    // });
    // console.log(role);
  }

  loadExercises() {
    this.exerciseService.getExercisesForUser().pipe(
      mergeMap(exercises => {
        this.exercises = exercises;
        const facilityObservables = exercises.map((exercise: { facilityId: number; facility: any; }) => 
          this.facilityService.getFacilityById(exercise.facilityId).pipe(
            map(facility => {
              exercise.facility = facility;
              return exercise;
            })
          )
        );
        return forkJoin(facilityObservables);
      })
    ).subscribe({
      next: (updatedExercises) => {
        this.exercises = updatedExercises;
        // console.log(this.exercises);
      },
      error: (err) => {
        console.error('Failed to load user info', err);
      }
    });
  }

  loadReviews(){
    this.reviewService.getReviewsByUser().pipe(
      mergeMap(reviews => {
        this.reviews = reviews;
        const facilityObservables = reviews.map((review: { facilityId: number; facility: any; }) => 
          this.facilityService.getFacilityById(review.facilityId).pipe(
            map(facility => {
              review.facility = facility;
              return review;
            })
          )
        );
        return forkJoin(facilityObservables);
      })
    ).subscribe({
      next: (updatedReviews) => {
        this.reviews = updatedReviews;
        // console.log(this.reviews);
      },
      error: (err) => {
        console.error('Failed to load user info', err);
      }
    });
  }
  
  loadManages(){
    this.managesService.getManagesByUser().pipe(
      mergeMap(manages => {
        this.manages = manages;
        const facilityObservables = manages.map((manage: { facilityId: number; facility: any; }) => 
          this.facilityService.getFacilityById(manage.facilityId).pipe(
            map(facility => {
              manage.facility = facility;
              return manage;
            })
          )
        );
        return forkJoin(facilityObservables);
      })
    ).subscribe({
      next: (updatedManages) => {
        this.manages = updatedManages;
        console.log(this.manages);
      },
      error: (err) => {
        console.error('Failed to load user info', err);
      }
    });
  }

  loadUserInfo() {
    this.userService.getUserInfo().subscribe({
      next: (data) => {
        this.userInfo = data;
      },
      error: (err) => {
        console.error('Failed to load user info', err);
      }
    });
  }

  onEditUser(){
    this.dialog.open(EditUserComponent,{});
  }

  onEditUserPassword(){
    this.dialog.open(EditUserPasswordComponent,{});
  }

  onEditImage(){
    this.dialog.open(EditImageDialogComponent,{});
  }


}
