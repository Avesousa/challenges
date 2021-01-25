import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CostComponent } from 'src/app/cost/component/cost/cost.component';
import { Cost } from 'src/app/models/cost.model';
import { Listing } from 'src/app/models/listing.model';
import { Reserve } from 'src/app/models/reserve.model';
import { DateUtil } from 'src/app/shared/dateUtil';
import { InformationService } from '../../services/information.service';

@Component({
  selector: 'app-information',
  templateUrl: './information.component.html',
  styleUrls: ['./information.component.css']
})
export class InformationComponent implements OnInit {

  @ViewChild('infoCost') infoCost: CostComponent;

  public view: any;
  public reserve: Reserve;
  public cost: Cost;
  public listing: Listing;
  public driveAlert: any;

  constructor(private informationService: InformationService) {
    this.driveAlert = {
      isError: false,
      isSuccess: false,
      message: '',
      refresh: () => {
        setTimeout(() => {
          this.driveAlert.isError = false;
          this.driveAlert.isSuccess = false;
          this.driveAlert.message = '';
        }, 4000)
      }
    }
  }

  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.getListings();
    this.view = { is: 'review' };
    this.reserve = new Reserve();
    this.infoCost.getCost(false);
  }

  getListings() {
    this.informationService.getListings().subscribe((data) => {
      this.listing = data[0];
    }, (error) => {
      this.driveAlert.message = error.error.message;
      this.driveAlert.isError = true;
      this.driveAlert.refresh();
    })
  }

  confirm() {
    this.informationService.confirmReservation(this.listing.id, this.reserve).subscribe((data) => {
      this.reserve.updateDateNow();
      this.driveAlert.message = data.message;
      this.driveAlert.isSuccess = true;
      this.refresh();
      this.driveAlert.refresh();
    }, (error) => {
      this.driveAlert.message = error.error.message;
      this.driveAlert.isError = true;
      this.driveAlert.refresh();
    })
  }

  getDate(date: string) {
    return DateUtil.getDate(date).toDateString();
  }

  getDays() {
    return DateUtil.getDays(this.reserve.checkin, this.reserve.checkout);
  }

}
