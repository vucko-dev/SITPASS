import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { ActivatedRoute } from '@angular/router';
import { FacilityService } from '../../services/facility.service';
import { CommonModule } from '@angular/common';
import { WorkDay } from '../../models/interfaces';
import { ReviewService } from '../../services/review.service';
import { ExerciseService } from '../../services/exercise.service';
import { MatDialog } from '@angular/material/dialog';
import { AddExerciseComponent } from '../../dialogs/add-exercise/add-exercise.component';
import { UserService } from '../../services/user.service';
import { DeleteFacilityDialogComponent } from '../../dialogs/delete-facility-dialog/delete-facility-dialog.component';
import { ManagesService } from '../../services/manages.service';
import { EditFacilityDialogComponent } from '../../dialogs/edit-facility-dialog/edit-facility-dialog.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-detailed-object',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, CommonModule, FormsModule],
  templateUrl: './detailed-object.component.html',
  styleUrl: './detailed-object.component.css'
})
export class DetailedObjectComponent {
  location: string = 'assets/images/placeholder.png';
  name:string = 'Naziv objekta';
  totalRating:string = 'Ocena';
  description:string = 'Opis';
  address:string = 'adresa';
  city:string = 'Grad';
  workdays:WorkDay[] = [];
  sortedWorkdays:WorkDay[]= [];
  disciplines:any[] = [];
  images:any[] = [];
  facility: any;
  reviews:any[] = [];
  exerciseCount:number = 0;
  reviewsCount:number = 0;
  stuff:number = 0;
  equipment:number = 0;
  space:number = 0;
  hygiene:number = 0;
  role:string|null = '';
  hasManagerRight:any = false;
  alreadyComment:boolean = false;
  userData:any;
  reviewData = {
    facilityId:0,
    rate: {
      staff: 0,
      equipment: 0,
      space: 0,
      hygiene: 0
    },
    commentDTO: {
      text: ''
    }
  }; 
  dayOrder = [
    'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'
  ];

  constructor(
    private route: ActivatedRoute,
    private facilityService: FacilityService,
    private reviewService: ReviewService,
    private exerciseService: ExerciseService,
    private dialog: MatDialog,
    private userService:UserService,
    private managesService:ManagesService
  ) {}

  async ngOnInit(): Promise<void> {
    this.route.paramMap.subscribe(params => {
      if(params.get('id') != null){
        const id = params.get('id'); 
        localStorage.setItem('currentFacility',id==null?'':id);
        this.loadUserData();
        this.loadFacility(Number(id));
        this.loadReviews(Number(id));
        this.loadExerciseCount(Number(id));
        this.hasRights(Number(id));
        // this.loadUserData();
      }
    });
    const role = await this.userService.getUserRole();
    this.role = role;
  }
  
  calculateStuff():void{
    let sum:number = 0;
    this.reviews.forEach(element => {
      sum+=element.rate.staff;
    });
    this.stuff =  sum/this.reviews.length;
  }

  calculateHygiene():void{
    let sum:number = 0;
    this.reviews.forEach(element => {
      sum+=element.rate.hygiene;
    });
    this.hygiene = sum/this.reviews.length;
  }

  calculateSpace():void{
    let sum:number = 0;
    this.reviews.forEach(element => {
      sum+=element.rate.space;
    });
    this.space = sum/this.reviews.length;
  }

  calculateEquipment():void{
    let sum:number = 0;
    this.reviews.forEach(element => {
      sum+=element.rate.equipment;
    });
    this.equipment = sum/this.reviews.length;
  }

  loadExerciseCount(id: number): void{
    this.exerciseService.getExercisesCountForSpecificFacility(id).subscribe(data=>{
      this.exerciseCount = data;
      // console.log('Broj poseta:');
      // console.log(data);
    })
  }

  loadReviews(id: number): void {
    this.reviewService.getReviewsByFacilityId(id).subscribe(data=>{
      this.reviews = data;
      this.reviewsCount = data.length;
      this.reviews.forEach((review, index) => {
        this.userService.getUserInfoByUserId(review.userId).subscribe(userInfo => {
          this.reviews[index].user = userInfo;
        });
        if(review.userId == this.userData.id){
          this.alreadyComment = true;
        }
      });
      // console.log(this.reviews);
      if(this.reviewsCount >0){
        this.calculateEquipment();
        this.calculateHygiene();
        this.calculateSpace();
        this.calculateStuff();
      }
      // console.log(this.reviewsCount);
      // console.log(data);
    });
  }

  loadFacility(id: number): void {
    this.facilityService.getFacilityById(id).subscribe(data => {
      this.facility = data;
      this.name = data.name;
      this.totalRating = data.totalRating;
      this.description = data.description;
      this.address = data.address;
      this.city = data.city;
      this.workdays = data.workdays;
      this.disciplines = data.disciplines;
      this.images = data.images;
      this.sortedWorkdays = this.workdays.sort((a, b) => {
        return this.dayOrder.indexOf(a.dayOfWeek) - this.dayOrder.indexOf(b.dayOfWeek);
      });
      // console.log(data);
    });
  }

  hasRights(id:number):void{
    this.managesService.hasRights(id).subscribe(data=>{
        this.hasManagerRight = data.body;
    });
  }

  loadUserData():void{
    this.userService.getUserInfo().subscribe({
      next: (data) => {
        this.userData = data;
        // console.log(data);
      },
      error: (err) => {
        console.error('Failed to load user info', err);
      }
    });
  }

  onAddExercise(){
    this.dialog.open(AddExerciseComponent, {});
  }

  onDeleteFacility(){
    this.dialog.open(DeleteFacilityDialogComponent, {});
  }

  onEditFacility(){
    this.dialog.open(EditFacilityDialogComponent,{});
  }

  onReviewPost(){
    // this.reviewData.rate.staff = this.staffInput;
    // this.reviewData.rate.equipment = this.equipmentInput;
    // this.reviewData.rate.hygiene = this.hygieneInput;
    // this.reviewData.rate.space = this.spaceInput;
    var n:number = localStorage.getItem('currentFacility')?Number(localStorage.getItem('currentFacility')):0;
    this.reviewData.facilityId = n;
    // this.reviewData.commentDTO.text = this.comment;

    console.log(this.reviewData)
    this.reviewService.addReview(this.reviewData).subscribe(response => {
      console.log('Status Code:', response.status);
      console.log('Response Body:', response.body);
      window.location.reload();
    }, error => {
      console.log('Error Status Code:', error.status);
      console.log('Error Message:', error.message);
    });

  }
}
