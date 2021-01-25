import { DatePipe } from "@angular/common";

export abstract class DateUtil {

    public static YYYY_MM_DD: string = 'yyyy-MM-dd';
    public static YYY_MM_DD_SLASH: string = 'yyyy/mm/dd';

    //Date whit format defined
    static getDateWithFormat(date: Date | string, format: string): string {
        let dateResponse = new DatePipe('en-US').transform(date, format);
        return dateResponse;
    }

    //Date with string
    static getDate(date: string): Date {
        let dateResponse = new Date(date)
        dateResponse.setTime(dateResponse.getTime() + 86400000);
        return dateResponse;
    }

    //Days difference
    static getDays(dateSmall: string, dateLarge: string): number {
        return (new Date(dateLarge).getTime() - new Date(dateSmall).getTime()) / 86400000;
    }

    //Add a day the date
    static addDays(date: Date): Date {
        date.setTime(date.getTime() + 86400000);
        return date;
    }

    //The date in time (long) whit string date
    static getDateWithTimeString(date: string): Date{
        let dateResponse = new Date();
        dateResponse.setTime(parseInt(date));
        return dateResponse;
    }

    //The time (long) of date
    static getTime(date: string): number {
        return this.getDate(date).getTime();
    }


}