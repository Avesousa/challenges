import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InformationComponent } from './component/information/information.component';
import { NavigationModule } from '../navigation/navigation.module';
import { AppRouterModule } from '../router/router-routing.module';
import { CostModule } from '../cost/cost.module';
import { FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faArrowLeft, faArrowRight, faStar } from '@fortawesome/free-solid-svg-icons';
import { InformationService } from './services/information.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [InformationComponent],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    FontAwesomeModule,
    NavigationModule,
    CostModule
  ],
  exports: [InformationComponent,AppRouterModule],
  providers: [InformationService]
})
export class InformationModule { 
  constructor(private library: FaIconLibrary){
    library.addIcons(faStar,faArrowRight,faArrowLeft);
  }
}
