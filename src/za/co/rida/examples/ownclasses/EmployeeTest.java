package za.co.rida.examples.ownclasses;

import java.text.NumberFormat;
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

        // Static fields
        // When you define a field as static, there is only one such field per class.
        // For nonstatic instance fields, each object has it's own copy of the instance field.
        // In practice static variables are quite rare.
        Employee safiya = new Employee("Safiya Abrahams", 75000, 2003, 1, 22);
        safiya.setId();
        System.out.println("Safiya's ID = " + safiya.getId());
        Employee atheera = new Employee("Atheera Abrahams", 65000, 2005, 12, 2);
        atheera.setId();
        System.out.println("Atheera's ID = " + atheera.getId()); // see how the nextId field is accessible to both objects

        // Static constants
        // In practice static constants are more prevalent than static variables
        // There is one copy of the constant per class
        System.out.println(Employee.RIGHTS);

        // Static methods
        // These methods do not operate on objects, i.e. there is no implicit "this" parameter as there is
        // with ojbects, e.g. this.getBirthDate
        // Static methods cannot access instance fields because they don't operate on objects
        // So a static method cannot access the this.birthDate instance variable for example
        // They can, however, access static variables.
        // It is recommended to use class names to invoke static methods, not objects.
        // e.g. Don't use atheera.getCompanyName(), rather use the class, Employee.getCompanyName()
        // Use static methods under 2 circumstances:
        // 1. When a method doesn’t need to access the object state because all needed parameters are supplied as explicit parameters.
        // 2. When a method only needs to access static fields of the class.
        Employee.setCompanyName("Amazon");
        System.out.println(Employee.getCompanyName());

        // Factory methods
        // Another common use for static methods can be found in classes such as LocalDate and NumberFormat
        // where they use static factory methods that construct objects.
        // The reasons for not using constructor instead are:
        // You can’t give names to constructors. The constructor name is always the same as the class name.
        // But we want two different names to get the currency instance and the percent instance.
        // When you use a constructor, you can’t vary the type of the constructed object.
        // But the factory methods actually return objects of the class DecimalFormat, a subclass that inherits from NumberFormat.
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        NumberFormat percentFormatter = NumberFormat.getPercentInstance();
        double x = 0.1;
        System.out.println(currencyFormatter.format(x)); // prints R0.10
        System.out.println(percentFormatter.format(x)); // prints 10%

        // The main method of all classes is static and doesn't need any object.
        // It executes once the program is loaded. Every class can have a main method.

        // Method parameters
        // Parameters can be passed to a method in two ways:
        // 1. By value: i.e. the method gets the value that the caller provides.
        // 2. By reference: i.e. the method gets the location of the variable that the caller provides.
        // A method can modify the value in a variable passed by reference but not one passed by value.
        // In Java, methods are always passed by value. ALWAYS!
        // i.e. the method gets a COPY of all parameter values passed to it. That means that the method
        // cannot modify the contents of any particular variables passed to it.
        // below the value of the percent variable is still 10, even after the call to tripleValue().
        // Let's see how this works for primitives. i.e. int, double, boolean, etc.
        double percent = 10;
        System.out.println("Value of percent before method call = " + percent);
        tripleValue(percent);
        System.out.println("Value of percent after  method call = " + percent);

        // Object parameters work differently.
        var harryHacker = new Employee("Harry Hacker", 55000, 1989, 10, 11);
        tripleSalary(harryHacker);



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

    public static void tripleValue(double x) {
        x = 3 * x;
        System.out.println("Value of x = " + x);
    }

    private static void tripleSalary(Employee x) {
        // The argument 'x' is not passed by reference. Instead, 'x' is initialised with a copy of the value
        // of the variable harryHacker, which is an object reference.
        // Both the harryHacker and x object reference variables point to the same Employee instance.
        // So the x.raiseSalary method call will obviously modify the value of the salary that the harryHacker object
        // reference is pointing to since it is the same object.
        // once the tripleSalary method ends, x is no longer in use, but the harryHacker object variable is still in use
        // and will continue to point to the Employee object whose salary was tripled.
        x.raiseSalary(200);
    }


}

class Employee {
    private final String name;
    private double salary;
    private LocalDate hireDay;
    private Date birthDate;
    private int id;
    // There is a single static field for all Employee objects, even if there are no Employee objects the
    // static field is present, since it belongs to the class, not to an individual object.
    private static int nextId = 1;
    public static final String RIGHTS = "You have the right to be paid on a specific date";
    private static String companyName;

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
        byPercent = 400;
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

    public int getId() {
        return id;
    }

    public static String getCompanyName() {
        return companyName;
    }

    public static void setCompanyName(String companyName) {
        Employee.companyName = companyName;
    }

    public void setId() {
        this.id = nextId;
        nextId++;
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
