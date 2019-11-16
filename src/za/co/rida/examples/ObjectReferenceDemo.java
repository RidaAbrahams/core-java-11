package za.co.rida.examples;

import java.util.Date;

public class Hello {

    public static void main(String[] args) {
        System.out.println(new Date());
        String s = new Date().toString();
        System.out.println(s);
        Date birthday = new Date();
        Date deadline = birthday;
        String s1 = deadline.toString();
        System.out.println(birthday);
        System.out.println(deadline);
    }
}
