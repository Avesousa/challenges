import { DateUtil } from "../shared/dateUtil";

export class Reserve {

    adults: number;
    children: number;
    checkin: string;
    checkout: string;
    pets: boolean;
    message: string;

    constructor() {
        this.updateDateNow();
        this.adults = 0;
        this.children = 0;
        this.pets = false;
        this.message = "";
    }
    
    //Update the date to today and tomorrow
    public updateDateNow(){
        this.checkin = DateUtil.getDateWithFormat(new Date(), DateUtil.YYYY_MM_DD);
        this.checkout = DateUtil.getDateWithFormat(DateUtil.addDays(new Date()), DateUtil.YYYY_MM_DD);
    }
}
