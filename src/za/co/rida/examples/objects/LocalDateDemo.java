package za.co.rida.examples.objects;

import java.time.LocalDate;

public class LocalDateDemo {

    public static void main(String[] args) {
        // the now method constructs a new object that represents the date the object was created
        LocalDate now = LocalDate.now();// uses a static factory method that calls a constructor on your behalf
        System.out.println(now);

        // This is how to construct an object for a specified date
        LocalDate specificDate = LocalDate.of(2019, 11, 15);
        System.out.println(specificDate);
        System.out.println("year = " + specificDate.getYear());
        System.out.println("month = " + specificDate.getMonth() + " (int value = " + specificDate.getMonthValue() + ")");
        System.out.println("day of month = " + specificDate.getDayOfMonth());

        // the plusDays method is not a mutator method, so the object on which it operates on, does not have it's state changes.
        // the return value of the plusDays method contains a new object, with the mutated value.
        LocalDate aThousandDaysLater = specificDate.plusDays(1000);
        System.out.println("specificDate = " + specificDate);
        System.out.println("aThousandDaysLater = " + aThousandDaysLater);
    }
}
