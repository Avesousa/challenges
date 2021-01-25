import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationComponent } from './component/navigation.component';
import { AppRouterModule } from '../router/router-routing.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [NavigationComponent],
  imports: [
    CommonModule,
    FontAwesomeModule,
    AppRouterModule
  ],
  exports: [NavigationComponent]
})
export class NavigationModule { }
