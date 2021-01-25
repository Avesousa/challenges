import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {  retry } from 'rxjs/operators';
import { Listing } from 'src/app/models/listing.model';
import { Reserve } from 'src/app/models/reserve.model';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class InformationService {

  constructor(private http: HttpClient) { }
  
  private server: string = `${environment.URL_SERVER}/listings`;


  //Get listings of server
  public getListings(): Observable<Listing[]>{
    return <Observable<Listing[]>>this.http.get(this.server).pipe(
      retry(0)
    );
  }

  //Get a listing of server
  public getListing(listing: string): Observable<Listing[]>{
    return <Observable<Listing[]>>this.http.get(`${this.server}/${listing}`).pipe(
      retry(0)
    );
  }

  //Confirm of reserve
  public confirmReservation(listing: string, reserve: Reserve): Observable<any>{
    return <Observable<any>>this.http.post(`${this.server}/${listing}/confirmation-reservation`,reserve).pipe(
      retry(0)
    );
  }
}
