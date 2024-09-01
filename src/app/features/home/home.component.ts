import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { ObjectComponent } from '../object/object.component';
import { ObjectsComponent } from '../objects/objects.component';
import { FooterComponent } from '../footer/footer.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from '../../app.component';
import { FacilityService } from '../../services/facility.service';
import { UserService } from '../../services/user.service';
import { ExerciseService } from '../../services/exercise.service';
import { forkJoin, map } from 'rxjs';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavbarComponent, ObjectComponent, ObjectsComponent, FooterComponent, RouterModule, CommonModule, HttpClientModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private facilityService:FacilityService, private userService:UserService, private exerciseService:ExerciseService){}

  nearFacilities:any[] = [];
  mostPopularFacilities:any[] = [];
  visitedFacilities:any[] = [];
  tryNewFacilities:any[] = [];
  facilities:any[] = [];
  exercises:any[] = [];

  userCity:string = '';
  userDisciplines:any[] = [];


  ngOnInit(): void {
    this.loadUserData();
    this.loadFacilities();
    this.loadExercises();
  }


  loadUserData():void{
    this.userService.getUserInfo().subscribe((data) => {
      this.userCity = data.city;
      // console.log(this.userCity);
    });
  }

  loadFacilities(): void{
    this.facilityService.getFacilities().subscribe((data: any[]) => {
      this.facilities = data;
      this.nearFacilities =  this.facilities.filter(facility => facility.city === this.userCity);
      // console.log(this.nearFacilities);
      // this.nearFacilities = this.facilities.map(facility => facility.city == );
      this.mostPopularFacilities = this.facilities.filter(facility => facility.totalRating > 0);
      this.mostPopularFacilities =  this.mostPopularFacilities.sort((a, b) => b.totalRating - a.totalRating);
      // console.log(this.mostPopularFacilities);
      this.tryNewFacilities = this.getUnvisitedFacilities(this.facilities, this.visitedFacilities);
    });
  }

  loadExercises():void{
    this.exerciseService.getExercisesForUser().subscribe((data: any[]) => {
      this.exercises = data;
    
      const facilityRequests = this.exercises.map(exercise => 
        this.facilityService.getFacilityById(exercise.facilityId)
      );
    
      forkJoin(facilityRequests).subscribe({
        next: (facilities) => {
          // Assign facilities to their respective exercises
          this.exercises.forEach((exercise, index) => {
            exercise.facility = facilities[index];
          });
    
          // Extract and remove duplicates from facilities
          this.visitedFacilities = this.exercises.map(exercise => exercise.facility);
          this.visitedFacilities = this.removeDuplicates(this.visitedFacilities);
          this.tryNewFacilities = this.getUnvisitedFacilities(this.facilities, this.visitedFacilities);
          this.userDisciplines = this.visitedFacilities.map(facility=>facility.disciplines);
          this.userDisciplines = this.userDisciplines.flat();
          this.userDisciplines = this.removeDuplicates(this.userDisciplines);
          this.tryNewFacilities = this.getTryNewFacilities(this.tryNewFacilities, this.userDisciplines);
        },
        error: (err) => {
          console.error('Failed to load facilities', err);
        }
      });
    });
    // this.exerciseService.getExercisesForUser().subscribe((data: any[]) =>{
    //   this.exercises = data;
    //   this.exercises.forEach(exercise => {
    //     this.facilityService.getFacilityById(exercise.facilityId).subscribe({
    //       next: (facility) => {
    //         // Add the facility to the exercise object
    //         exercise.facility = facility;
    //       },
    //       error: (err) => {
    //         console.error(`Failed to load facility for exercise ID ${exercise.id}`, err);
    //       }
    //     });
    //   });
    //   this.visitedFacilities = this.exercises.map(exercise=>exercise.facility);
    //   this.visitedFacilities = this.removeDuplicates(this.visitedFacilities);      // console.log(this.visitedFacilities);
    //   // console.log('Exercises:');
    //   // console.log(this.removeFacilityDuplicates(this.visitedFacilities));
    //   this.tryNewFacilities = this.getUnvisitedFacilities(this.facilities, this.visitedFacilities);
    //   this.userDisciplines = this.visitedFacilities.map(facility=>facility.disciplines);
    //   this.userDisciplines = this.userDisciplines.flat();
    //   this.userDisciplines = this.removeDuplicates(this.userDisciplines);
    //   this.tryNewFacilities = this.getTryNewFacilities(this.tryNewFacilities, this.userDisciplines);
    //   // console.log(this.userDisciplines);
    // })
  }


  removeDuplicates(arr: any[]): any[] {
    const unique = arr.reduce((acc, current) => {
      const x = acc.find((item: { id: any; }) => item.id === current.id);
      if (!x) {
        return acc.concat([current]);
      } else {
        return acc;
      }
    }, []);
    
    return unique;
  }

  getUnvisitedFacilities(facilities: any[], visitedFacilities: any[]): any[] {
    return facilities.filter(facility => 
      !visitedFacilities.some(visited => visited.id === facility.id)
    );
  }

  getTryNewFacilities(facilities: any[], userDisciplines: any[]): any[] {
    return facilities.filter(facility =>
      // Check that every discipline in the facility is not in userDisciplines
      facility.disciplines.every((discipline: { id: any; }) =>
        // Return true if no discipline in userDisciplines matches the current discipline
        !userDisciplines.some(userDiscipline => userDiscipline.id === discipline.id)
      )
    );
  }
}
