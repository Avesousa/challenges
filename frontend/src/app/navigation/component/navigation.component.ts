import { Component, Input } from '@angular/core';
import { Listing } from 'src/app/models/listing.model';
import { Reserve } from 'src/app/models/reserve.model';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {

  //Data of reserve
  @Input() public reserve: Reserve;

  //Defines the view that will display review | confirmation
  @Input() public view: any;

  constructor() {}

}
