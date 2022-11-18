/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer: Prof Udaya Parampalli, Dr Thuan Pham
   Semester 1, 2021, Week 5
   Workshop Sample Solution
   Copyright The University of Melbourne 2021
*/

public class AppointmentDemo {

    public static void main (String[] args) {
        Appointment appointment = new Appointment();

        System.out.println(appointment);

        Appointment appointment1 = new Appointment(2020, 9, 2, 2, 15, 0, 3, 15, 0);

        System.out.println(appointment1);

        appointment.setDate(2020, 9 ,2);

        appointment.setStarttime(14, 15, 0);

        appointment.setEndtime(15, 15, 0);

        System.out.println(appointment);
    }
}
