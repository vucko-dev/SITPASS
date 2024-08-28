import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { ActivatedRoute } from '@angular/router';
import { FacilityService } from '../../services/facility.service';

@Component({
  selector: 'app-detailed-object',
  standalone: true,
  imports: [NavbarComponent, FooterComponent],
  templateUrl: './detailed-object.component.html',
  styleUrl: './detailed-object.component.css'
})
export class DetailedObjectComponent {
  location: string = 'assets/images/placeholder.png';
  name:string = 'Naziv objekta';
  totalRating:string = 'Ocena';
  facility: any;


  constructor(
    private route: ActivatedRoute,
    private facilityService: FacilityService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      if(params.get('id') != null){
        const id = params.get('id'); 
        this.loadFacility(Number(id));
      }
    });
  }

  loadFacility(id: number): void {
    this.facilityService.getFacilityById(id).subscribe(data => {
      this.facility = data;
      this.name = data.name;
      this.totalRating = data.totalRating;
      console.log(data);
    });
  }
}
