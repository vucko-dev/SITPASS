import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { ActivatedRoute } from '@angular/router';
import { FacilityService } from '../../services/facility.service';
import { CommonModule } from '@angular/common';
import { WorkDay } from '../../models/interfaces';
import { ReviewService } from '../../services/review.service';
import { ExerciseService } from '../../services/exercise.service';

@Component({
  selector: 'app-detailed-object',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, CommonModule],
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
  
  dayOrder = [
    'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'
  ];

  constructor(
    private route: ActivatedRoute,
    private facilityService: FacilityService,
    private reviewService: ReviewService,
    private exerciseService: ExerciseService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      if(params.get('id') != null){
        const id = params.get('id'); 
        this.loadFacility(Number(id));
        this.loadReviews(Number(id));
        this.loadExerciseCount(Number(id));
      }
    });
  }
  
  calculateStuff():void{
    let sum:number = 0;
    this.reviews.forEach(element => {
      sum+=element.rate.stuff;
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
}
