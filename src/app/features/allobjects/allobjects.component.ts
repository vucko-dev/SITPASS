import { Component, QueryList, ViewChildren } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { ObjectComponent } from '../object/object.component';
import { DropdownComponent } from '../dropdown/dropdown.component';
import { FacilityService } from '../../services/facility.service';
import { CommonModule } from '@angular/common';
import { DisciplineService } from '../../services/discipline.service';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MultiSelectDropdownComponent } from '../multi-select-dropdown/multi-select-dropdown.component';
import { Discipline, WorkDay } from '../../models/interfaces';

@Component({
  selector: 'app-allobjects',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, ObjectComponent, DropdownComponent, CommonModule, RouterModule, FormsModule, MultiSelectDropdownComponent],
  templateUrl: './allobjects.component.html',
  styleUrl: './allobjects.component.css'
})
export class AllobjectsComponent {
  
  searchTerm: string = '';
  selectedCities: string[] = [];
  selectedDisciplines: string[] = [];
  selectedLowGrade: string = '';
  selectedHighGrade: string = '';
  selectedLowTime: string = '';
  selectedHighTime: string = '';

  citiesOptions = ['Beograd', 'Novi Sad', 'Subotica', 'Valjevo'];
  disciplinesOptions = ['Teretana', 'Personalni trening', 'Borilacke vestine'];
  lowGradeOptions = ['1','2','3','4','5','6','7','8','9','10'];
  highGradeOptions = ['1','2','3','4','5','6','7','8','9','10'];
  lowTimeOptions = ['00:00','01:00','02:00','03:00','04:00','05:00','06:00','07:00','08:00','09:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00'];
  highTimeOptions = ['00:00','01:00','02:00','03:00','04:00','05:00','06:00','07:00','08:00','09:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00'];

  facilities: any[] = [];
  filteredFacilities: any[] = [];
  disciplines: any[] =[];
  constructor(private facilityService: FacilityService, private disciplineService: DisciplineService) {}

  ngOnInit(): void {
    this.facilityService.getFacilities().subscribe((data: any[]) => {
      this.facilities = data;
      this.filteredFacilities = data;
      this.citiesOptions = Array.from(new Set(this.facilities.map(facility => facility.city)));
    });
    this.disciplineService.getDisciplines().subscribe((data: any[]) => {
      this.disciplines = data;
      this.disciplinesOptions = this.disciplines.map(discipline => discipline.name);
    });
  }

  @ViewChildren('citiesDropdown') citiesDropdowns!: QueryList<MultiSelectDropdownComponent>;
  @ViewChildren('disciplinesDropdown') disciplinesDropdowns!: QueryList<MultiSelectDropdownComponent>;


  onCitiesSelectionChange(selectedCities: string[]) {
    this.selectedCities = selectedCities;
    this.filterFacilities();
  }

  onDisciplinesSelectionChange(selectedDisciplines: string[]) {
    this.selectedDisciplines = selectedDisciplines;
    this.filterFacilities();
  }

  transformDisciplinesToString(disciplines: any[]): string {
    return disciplines.map(d => d.name).join(", ");
  }

  onLowGradeChange(lowGrade: string) {
    this.selectedLowGrade = lowGrade;
    this.filterFacilities();
  }

  onHighGradeChange(highGrade: string) {
    this.selectedHighGrade = highGrade;
    this.filterFacilities();
  }

  onLowTimeChange(lowTime: string) {
    this.selectedLowTime = lowTime;
    this.filterFacilities();
  }

  onHighTimeChange(highTime: string) {
    this.selectedHighTime = highTime;
    this.filterFacilities();
  }

  resetFilter(){
    this.searchTerm = '';
    this.selectedCities = [];
    this.selectedDisciplines = [];
    this.citiesDropdowns.forEach(dropdown => dropdown.resetSelection());
    this.disciplinesDropdowns.forEach(dropdown => dropdown.resetSelection());
    this.selectedLowTime = "";
    this.selectedLowGrade = '';
    this.selectedHighGrade = '';
    this.selectedHighTime = "";
    this.filterFacilities();
  }

  convertToDate(time: string): Date {
    const [hours, minutes] = time.split(':').map(Number);
    const date = new Date();
    date.setHours(hours, minutes, 0, 0);
    return date;
  }
  

  filterFacilities() {
    console.log(this.selectedDisciplines);
    console.log(this.selectedCities);
  
    const lowTimeDate = this.selectedLowTime ? this.convertToDate(this.selectedLowTime) : null;
    const highTimeDate = this.selectedHighTime ? this.convertToDate(this.selectedHighTime) : null;
  
    this.filteredFacilities = this.facilities.filter(facility => {
      const matchesCity = this.selectedCities.length === 0 || this.selectedCities.includes(facility.city);
  
      const matchesDisciplines = this.selectedDisciplines.length === 0 || facility.disciplines.some((d: Discipline) => this.selectedDisciplines.includes(d.name));
  
      const matchesLowGrade = this.selectedLowGrade === '' || facility.totalRating >= +this.selectedLowGrade;
      const matchesHighGrade = this.selectedHighGrade === '' || facility.totalRating <= +this.selectedHighGrade;
  
      const matchesTime = facility.workdays.some((workday:WorkDay) => {
        const fromTime = this.convertToDate(`${workday.from[0]}:${workday.from[1]}`);
        const untilTime = this.convertToDate(`${workday.until[0]}:${workday.until[1]}`);

        // console.log(fromTime);
        // console.log(untilTime);
        // console.log(lowTimeDate);
        // console.log(highTimeDate);
  
        const isWithinLowTime = !lowTimeDate  || (fromTime <= lowTimeDate && lowTimeDate<=untilTime);
        const isWithinHighTime =  !highTimeDate || (untilTime >= highTimeDate && highTimeDate>=fromTime);
  
        if(lowTimeDate && highTimeDate){
          return lowTimeDate>=fromTime && highTimeDate<=untilTime && lowTimeDate<=highTimeDate;
        }

        return isWithinLowTime && isWithinHighTime;
      });
  
      const matchesSearchTerm = !this.searchTerm || facility.name.toLowerCase().includes(this.searchTerm.toLowerCase());
  
      return matchesCity && matchesDisciplines && matchesLowGrade && matchesHighGrade && matchesTime && matchesSearchTerm;
    });
  }
  
  
}
