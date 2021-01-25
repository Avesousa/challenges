import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CostComponent } from './component/cost/cost.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule } from '@angular/forms';
import { CostService } from './services/cost.service';

@NgModule({
  declarations: [CostComponent],
  imports: [
    CommonModule,
    FormsModule,
    FontAwesomeModule
  ],
  exports: [CostComponent],
  providers: [CostService]
})
export class CostModule {}
