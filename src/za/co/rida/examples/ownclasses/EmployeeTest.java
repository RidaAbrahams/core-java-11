package za.co.rida.examples.ownclasses;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

// The name of the public class must be the same as the class filename.
// you can have any number of non-public classes in the same class file. e.g. EmployeeTest.java
// has a public class called EmployeeTest and a non-public class called Employee
// When compiling EmployeeTest the Employee class will automatically be compiled, if the
// Employee class has changed it will be recompile as well if you compile EmployeeTest.
public class EmployeeTest {

    public static void main(String[] args) {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        // raise everyone's salary by 5%
        for (Employee e : staff) {
            e.raiseSalary(5);
        }

        for (Employee e: staff) {
            System.out.println(e);
        }

        // Constructors
        // Constructors can only be called with the 'new' keyword
        // you cannnot call a constructor on an existing object!
        Employee james = new Employee("James Bond", 100000, 1950, 1, 1);
//        james.Employee("James Bond", 100000, 1950, 1, 1);

        // Declaring variables with 'var'
        // As of Java 10 you can declare local variables with the var keyword instead of specifying their type,
        // provided their type can be inferred from the initial value.
        // This is nice since it avoids the repetition of the type name Employee and should be used often
        // in those cases where the type is obvious from the right-hand side without any knowledge of the Java API.
        // Note that the var keyword can only be used with local variables inside methods.
        // You must always declare the types of parameters and fields.
        var harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);

        // If you apply a method to a null value, a NullPointerException occurs.
        // A program will terminate if this exception is not caught.
//        nullPointerThrown();
        // Avoid a NullPointerException by assigning a non-null value to a null argument
        nullPointerNotThrownAutoAssignment(null);
        // Reject a null argument and include a custom description for the NullPointerException.
        // The exception report pinpoints the location of the problem. Otherwise, a NullPointerException
        // would have occurred elsewhere, with no easy way of tracing it back to the faulty constructor argument.
//        nullPointerNotThrownNullRejected(null);

        // Be careful not to write accessor methods that return references to mutable objects. This violates encapsulation
        // The reference variable: koosBirthDate will have the same reference to the koos.birthDate Date object
        // so the caller of the getBirthDate method can now change the value of the birthDate for the koos object
        Employee koos = new Employee("Koos", 20, 1978, 5, 23);
        koos.setBirthDate(new Date());
        Date koosBirthDate = koos.getBirthDate();
        System.out.println("Koos birth date = " + koosBirthDate);
        // changing koos's birthDate
        koosBirthDate.setTime(koosBirthDate.getTime() - 500000000);
        System.out.println("Koos birth date after it was modified = " + koosBirthDate);
        // Rather clone the birthDate variable in the Employee object before returning it from the getter method.
        //  A clone is an exact copy of an object stored in a new location.
        // The getBirthDateSafe illustrates this
        Date birthDateSafe = koos.getBirthDateSafe();
        System.out.println("Koos birth date(unchanged) = " + koosBirthDate);

        // Class-based access privileges
        // A method can access the private data of the object on which it is invoked.
        // You might find it surprising that a method can access the private data of all objects of its class!
        // This is legal because boss is an object of type Employee, and a method of the Employee class is permitted
        // to access the private fields of any object of type Employee.
        Employee rida = new Employee("Rida", 30, 1990, 5, 3);
        Employee boss = new Employee("Boss Man", 60, 1980, 9, 25);
        rida.demoClassBasedAccessPriviliges(boss);


    }

    private static void nullPointerNotThrownNullRejected(String name) {
        String extractedName = Objects.requireNonNull(name, "The name argument may not be null");
        System.out.println(extractedName);
    }

    private static void nullPointerNotThrownAutoAssignment(String name) {
        String extractedName = Objects.requireNonNullElse(name, "unknown");
        System.out.println(extractedName);
    }

    private static void nullPointerThrown() {
        LocalDate birthday = null;
        String s = birthday.toString();// NullPointerException
    }


}

class Employee {
    private final String name;
    private double salary;
    private LocalDate hireDay;
    private Date birthDate;

    public Employee(String name, double salary, int year, int month, int day) {
        // You can define an instance field as final. Such a field must be initialized when the object is constructed.
        // That is, you must guarantee that the field value has been set after the end of every constructor. Afterwards,
        // the field may not be modified again.
        this.name = name; // this field is final.
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent/100;
        salary += raise;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getBirthDateSafe() {
        return (Date) birthDate.clone();
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void demoClassBasedAccessPriviliges(Employee other) {
        double bossesSalary = other.salary;
        System.out.println("I just saw my bosses salary and it is: " + bossesSalary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
