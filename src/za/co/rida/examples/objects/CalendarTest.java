package za.co.rida.examples.objects;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalendarTest {

    public static void main(String[] args) {
        // get the current date
        LocalDate date = LocalDate.now();
        // store the current day and month
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();

        date = date.minusDays(today -1); // set to start of month by subtracting x number of days from today
        DayOfWeek weekDay = date.getDayOfWeek(); // get the weekDay of the 1st of the month
        int value = weekDay.getValue(); // 1=Monday, ... 7=Sunday
        System.out.println(date.getMonth() + " " + date.getYear());
        // print the header for the 1st line of the calendar
        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 1; i < value; i++) {
            System.out.println("    ");
            // print the body of the calendar
            // enter a loop where the date variable traverses the days of the month
            // while the date is in the current month
            while (date.getMonthValue() == month) {
                System.out.printf("%3d", date.getDayOfMonth());
                // print an asterisk next to the current date
                if (date.getDayOfMonth() == today) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
                date = date.plusDays(1);
                // print the beginning of each week on a new line
                if (date.getDayOfWeek().getValue() == 1) System.out.println();
            }
            if (date.getDayOfWeek().getValue() != 1) System.out.println();
        }
    }
}
