import { Component, Input, OnInit } from '@angular/core';
import { Cost } from 'src/app/models/cost.model';
import { Listing } from 'src/app/models/listing.model';
import { Reserve } from 'src/app/models/reserve.model';
import { DateUtil } from 'src/app/shared/dateUtil';
import { CostService } from '../../services/cost.service';

@Component({
  selector: 'app-cost',
  templateUrl: './cost.component.html',
  styleUrls: ['./cost.component.css']
})
export class CostComponent implements OnInit {

  //the information of the cost of the reservation
  @Input() public cost: Cost;

  //Data of reserve
  @Input() public reserve: Reserve;
  
  //Listing defined
  @Input() public listing: Listing;

  //Defines the view that will display review | confirmation
  @Input() public view: any;

  //Plugin to activate alerts and display a message
  @Input() public driveAlert: any;

  constructor(private costService: CostService) { }

  ngOnInit(): void {
    this.getCost(true);
  }

  //method to add or remove guests
  addOrSubtract(type: string, action: string) {
    if (action == 'add') {
      if (type == 'guest') {
        this.reserve.adults < this.listing.adults ? this.reserve.adults++ : null;
      } else {
        this.reserve.adults > 0 && this.reserve.children < this.listing.children ?
          this.reserve.children++ : null;
      }
    } else {
      if (type == 'guest') {
        this.reserve.adults == 1 && this.reserve.children > 0 ? null : this.reserve.adults--;
      } else {
        this.reserve.children--;
      }
    }
  }

  //Get cost of server whit info of reserve
  getCost(isInit: boolean): void {
    this.cost = isInit ? new Cost() : this.cost;
    this.costService.getCost(this.listing.id, this.reserve).subscribe((data) => {
      isInit ? this.transformDate() : null;
      this.cost = data;
    }, (error) => {
      this.driveAlert.message = error.error.message;
      this.driveAlert.isError = true;
      this.driveAlert.refresh();
      this.transformDate();
    });
  }

  //Do the date readable
  transformDate() {
    this.reserve.checkin = DateUtil.getDateWithFormat(DateUtil.getDateWithTimeString(this.reserve.checkin), DateUtil.YYYY_MM_DD);
    this.reserve.checkout = DateUtil.getDateWithFormat(DateUtil.getDateWithTimeString(this.reserve.checkout), DateUtil.YYYY_MM_DD);
  }

  //Returns an array the valuation that each listing has
  fillRating(value) {
    return Array(value).fill(value, 0, 5);
  }

  //Returns the date in string
  getDate(date) {
    return DateUtil.getDate(date).toDateString();
  }

}
