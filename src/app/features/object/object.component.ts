import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { FacilityService } from '../../services/facility.service';
import { ReviewService } from '../../services/review.service';

@Component({
  selector: 'app-object',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './object.component.html',
  styleUrl: './object.component.css'
})
export class ObjectComponent {
  // objectImg: string = 'assets/images/object_img.jpg';

  constructor(private reviewService:ReviewService){}

  @Input() title: string = 'Naziv objekta';
  @Input() services: string = 'Usluge objekta';
  @Input() objectImg: string = 'assets/images/object_img.jpg';
  @Input() grade: string = '5.0';
  @Input() id:number = 0;
  @Input() active:boolean = true;

  reviewsCount:number = 0;


  ngOnInit(): void {
    this.reviewService.getReviewsCountByFacilityId(this.id).subscribe((data) => {
      this.reviewsCount = data;
      // console.log(data);
    });
  }
}
