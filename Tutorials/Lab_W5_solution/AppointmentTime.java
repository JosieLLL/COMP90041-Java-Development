/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer: Prof Udaya Parampalli, Dr Thuan Pham
   Semester 1, 2021, Week 5
   Workshop Sample Solution
   Copyright The University of Melbourne 2021
*/

public class AppointmentTime {

    private int hour;
    private int minute;
    private int second;

    public AppointmentTime() {
        hour = 0;
        minute = 0;
        second = 0;
    }

    public AppointmentTime(int hr, int min, int sc) {
        hour = hr;
        minute = min;
        second = sc;

    }

    public int getHour() {return hour;}

    public int getMinute() {return minute;}

    public int getSecond() {return second;}

    public void setHour(int hr) {hour = hr;}

    public void setMinute(int min) {minute = min;}

    public void setSecond(int sc) {second = sc;}

    public void setTime(int hr, int min, int sc){
        hour = hr;
        minute = min;
        second = sc;
    }

    public String toString() {
        return (hour + ":" + minute + ":" +second);
    }


}
