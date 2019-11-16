package za.co.rida.examples;

import java.util.Date;

public class ObjectReferenceDemo {

    public static void main(String[] args) {
        // Object variables, like birthday always contain a reference to an object stored on the heap.
        // the variable birthday is not the object.
        Date birthday = new Date();
        // you can assign object variables to already created objects as indicated below.
        // both the deadline and birthday object variables contain a reference to the same Date object on the heap.
        Date deadline = birthday;
        String s1 = deadline.toString();
        System.out.println(birthday);
        System.out.println(deadline);
    }
}
