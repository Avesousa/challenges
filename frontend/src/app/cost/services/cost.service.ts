import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Cost } from 'src/app/models/cost.model';
import { Reserve } from 'src/app/models/reserve.model';
import { environment } from 'src/environments/environment.prod';
import { retry, catchError } from 'rxjs/operators';
import { DateUtil } from 'src/app/shared/dateUtil';

@Injectable({
  providedIn: 'root'
})
export class CostService {

  constructor(private http: HttpClient) { }
  
  private server: string = `${environment.URL_SERVER}/listings/`;


  public getCost(listing: string, reserve: Reserve): Observable<Cost>{
    reserve.checkout = DateUtil.getTime(reserve.checkout).toString();
    reserve.checkin = DateUtil.getTime(reserve.checkin).toString();
    return <Observable<Cost>>this.http.post(`${this.server}/${listing}/reservation-cost`,reserve).pipe(
      retry(0)
    );
  }


}
