import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InformationComponent } from '../information/component/information/information.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/information',
    pathMatch: 'full'
  },
  {
    path: 'information/:listing',
    component: InformationComponent
  },
  {
    path: 'information',
    component: InformationComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRouterModule { }
