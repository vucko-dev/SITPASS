import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { ActivatedRoute } from '@angular/router';
import { FacilityService } from '../../services/facility.service';
import { CommonModule } from '@angular/common';
import { WorkDay } from '../../models/interfaces';

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

  dayTranslation = {
    "MONDAY": 'Ponedeljak',
    "TUESDAY": 'Utorak',
    "WEDNESDAY": 'Sreda',
    "THURSDAY": 'Cetvrtak',
    "FRIDAY": 'Petak',
    "SATURDAY": 'Subota',
    "SUNDAY": 'Nedelja'
  };
  
  dayOrder = [
    'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'
  ];

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
      this.description = data.description;
      this.address = data.address;
      this.city = data.city;
      this.workdays = data.workdays;
      this.disciplines = data.disciplines;
      this.images = data.images;
      this.sortedWorkdays = this.workdays.sort((a, b) => {
        return this.dayOrder.indexOf(a.dayOfWeek) - this.dayOrder.indexOf(b.dayOfWeek);
      });
      console.log(data);
    });
  }
}
