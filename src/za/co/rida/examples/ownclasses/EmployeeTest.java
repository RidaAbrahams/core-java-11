package za.co.rida.examples.ownclasses;

import java.time.LocalDate;
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
    }



}

class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
