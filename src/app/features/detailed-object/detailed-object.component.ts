import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { ActivatedRoute, Router } from '@angular/router';
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
import { RateService } from '../../services/rate.service';
import { AppComponent } from '../../app.component';
import { EditFacilityManagesComponent } from '../../dialogs/edit-facility-manages/edit-facility-manages.component';
import { CommentComponent } from '../comment/comment.component';

@Component({
  selector: 'app-detailed-object',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, CommonModule, FormsModule, CommentComponent],
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
  commentResponse:string = '';
  
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
    private managesService:ManagesService,
    private rateService:RateService,
    private appComponent:AppComponent,
    private router:Router
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
        this.calculateStuff(Number(id));
        this.calculateEquipment(Number(id));
        this.calculateHygiene(Number(id));
        this.calculateSpace(Number(id));
      }
    });
    const role = await this.userService.getUserRole();
    this.role = role;
  }
  
  calculateStuff(id:number):void{
    this.rateService.getTotalStaff(id).subscribe(response => {
      this.stuff = response;
    }, error => {
      console.log('Error Status Code:', error.status);
      console.log('Error Message:', error.message);
      this.stuff = 0;
    });
  }

  calculateHygiene(id:number):void{
    this.rateService.getTotalHygiene(id).subscribe(response => {
      this.hygiene = response;
    }, error => {
      console.log('Error Status Code:', error.status);
      console.log('Error Message:', error.message);
      this.hygiene = 0;
    });
  }

  calculateSpace(id:number):void{
    this.rateService.getTotalSpace(id).subscribe(response => {
      this.space = response;
    }, error => {
      console.log('Error Status Code:', error.status);
      console.log('Error Message:', error.message);
      this.space = 0;
    });
  }

  calculateEquipment(id:number):void{
    this.rateService.getTotalEquipment(id).subscribe(response => {
      this.equipment = response;
    }, error => {
      console.log('Error Status Code:', error.status);
      console.log('Error Message:', error.message);
      this.equipment = 0;
    });
  }

  loadExerciseCount(id: number): void{
    this.exerciseService.getExercisesCountForSpecificFacility(id).subscribe(data=>{
      this.exerciseCount = data;
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
        review.rate.total = (Number(review.rate.staff) + Number(review.rate.equipment) + Number(review.rate.space) + Number(review.rate.hygiene))/4;
      });
    });
  }

  loadFacility(id: number): void {
    this.facilityService.getFacilityById(id).subscribe({
    next: (data) => {
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
    },
    error: (err) => {
      console.error('Failed to facility info', err);
      this.appComponent.showMessage('Pogresan url.', 'red');
      this.router.navigate(['/home']); 
    }});
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

  onOpenManagers(){
    this.dialog.open(EditFacilityManagesComponent, {});
  }

  onReviewPost(){
    var n:number = localStorage.getItem('currentFacility')?Number(localStorage.getItem('currentFacility')):0;
    this.reviewData.facilityId = n;

    console.log(this.reviewData)
    this.reviewService.addReview(this.reviewData).subscribe(response => {
      console.log('Status Code:', response.status);
      console.log('Response Body:', response.body);
      window.location.reload();
    }, error => {
      console.log('Error Status Code:', error.status);
      console.log('Error Message:', error.message);
      this.commentResponse = error.error.message;
    });

  }

  sortByCreatedAt(reviews: any[], ascending: boolean = true): any[] {
    return reviews.sort((a, b) => {
        const dateA = new Date(a.createdAt[0], a.createdAt[1] - 1, a.createdAt[2], a.createdAt[3], a.createdAt[4]);
        const dateB = new Date(b.createdAt[0], b.createdAt[1] - 1, b.createdAt[2], b.createdAt[3], b.createdAt[4]);

        return ascending ? dateA.getTime() - dateB.getTime() : dateB.getTime() - dateA.getTime();
    });
  }

  sortByRateTotal(reviews: any[], ascending: boolean = true): any[] {
    return reviews.sort((a, b) => {
        return ascending ? a.rate.total - b.rate.total : b.rate.total - a.rate.total;
    });
}
onSortChange(event: Event) {
  const target = event.target as HTMLSelectElement;
  const sortOption = target.value;

  switch (sortOption) {
    case 'createdAtAsc':
      this.reviews = this.sortByCreatedAt(this.reviews, true);
      break;
    case 'createdAtDesc':
      this.reviews = this.sortByCreatedAt(this.reviews, false);
      break;
    case 'rateAsc':
      this.reviews = this.sortByRateTotal(this.reviews, true);
      break;
    case 'rateDesc':
      this.reviews = this.sortByRateTotal(this.reviews, false);
      break;
    default:
      break;
  }
}

showReview(id:number){
  this.reviewService.showReview(id).subscribe(response => {
    window.location.reload();
  }, error => {
    console.log('Error Status Code:', error.status);
    console.log('Error Message:', error.message);
    this.commentResponse = error.error.message;
  });
}

hideReview(id:number){
  this.reviewService.hideReview(id).subscribe(response => {
    window.location.reload();
  }, error => {
    console.log('Error Status Code:', error.status);
    console.log('Error Message:', error.message);
    this.commentResponse = error.error.message;
  });
}

deleteReview(id:number){
  this.reviewService.deleteReview(id).subscribe(response => {
    window.location.reload();
  }, error => {
    console.log('Error Status Code:', error.status);
    console.log('Error Message:', error.message);
    this.commentResponse = error.error.message;
  });
}


}
