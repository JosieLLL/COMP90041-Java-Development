/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer: Prof Udaya Parampalli, Dr Thuan Pham
   Semester 1, 2021, Week 5
   Workshop Sample Solution
   Copyright The University of Melbourne 2021
*/

public class AppointmentDate {

    private int year;
    private int month;
    private int day;

    public AppointmentDate() {
        year = 2020;
        month = 1;
        day = 1;
    }

    public int getYear() {return year;}

    public int getMonth() {return month;}

    public int getDay() {return day;}

    public void setYear(int year) {this.year = year;}

    public void setMonth(int month) {this.month = month;}

    public void setDay(int day) {this.day = day;}


    public AppointmentDate(int year, int month, int day) {
        setDate(year, month, day);
    }

    public void setDate(int year, int month, int day) {
        if (isValidDate(year, month, day)){
            this.year = year;
            this.month = month;
            this.day = day;
        }
        else {
            System.out.println("The date is invalid");
            System.exit(0);
        }

    }

    private boolean isValidDate(int year, int month, int day) {
        if (year<1000 || year > 9999){
            return false;
        }

        if (month<1 || month >12){
            return false;
        }

        if (month==1 || month==3 || month == 5 || month==7||month==8||month==10||month==12){
            if(day>31){
                return false;
            }

        }
        else if (month == 4 || month == 6 || month == 9 || month == 11){
            if(day>30){
                return false;
            }
        }
        else {
            if (isLeapYear(year)){
                if (day > 29 ){
                    return false;
                }
            }
            else {
                if (day > 28) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isLeapYear(int year){
        return (year%4==0 && year%100!=0) || (year%400==0);
    }

    public String toString() {
        return (year + " " + month +" " + day);
    }


}
