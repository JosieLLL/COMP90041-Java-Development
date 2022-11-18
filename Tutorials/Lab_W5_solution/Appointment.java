/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer: Prof Udaya Parampalli, Dr Thuan Pham
   Semester 1, 2021, Week 5
   Workshop Sample Solution
   Copyright The University of Melbourne 2021
*/

public class Appointment {

    private AppointmentDate date;
    private AppointmentTime starttime;
    private AppointmentTime endtime;

    public Appointment(){
        date = new AppointmentDate();
        starttime = new AppointmentTime();
        endtime = new AppointmentTime();
    }

    public Appointment(int year, int month, int day, int hr1, int min1, int sc1, int hr2, int min2, int sc2) {
        date = new AppointmentDate(year, month, day);
        starttime = new AppointmentTime(hr1, min1, sc1);
        endtime = new AppointmentTime(hr2, min2, sc2);
    }

    public void setDate(int year, int month, int day) {
        date.setDate(year, month, day);
    }

    public void setStarttime(int hr, int min, int sc) {
        starttime.setTime(hr, min, sc);
    }

    public void setEndtime(int hr, int min, int sc){
        AppointmentTime stime = this.starttime;

        if ( hr * 3600 + min * 60 + sc > stime.getHour() * 3600 + stime.getMinute() * 60 + stime.getSecond()) {
            endtime.setTime(hr, min, sc);
        }
        else {
            System.out.println("U enter an invalid end time!");
            System.exit(0);
        }

    }

    public String toString() { return  (date + " " + starttime + "---" + endtime); }


}
