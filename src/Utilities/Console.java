package Utilities;

import java.util.Scanner;

public class Console {

    private static Scanner sc = new Scanner(System.in);

    public static void display(String s) {
        System.out.print(s);
    }

    public static void displayLine() {
        System.out.println();
    }

    public static void displayLine(String s) {
        System.out.println(s);
    }

    public static String getString(String prompt) {
        String s = "";
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (sc.hasNext()) {
                s = sc.nextLine().trim();
                isValid = true;
            } else
                System.out.println("Error, invalid entry.");
        }
        return s;
    }

    public static int getInt(String prompt) {
        int i = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                isValid = true;
            } else
                System.out.println("Error, invalid entry.");
            sc.nextLine();
        }
        return i;
    }

    public static int getInt(String prompt, int min, int max) {
        int i = 0;
        boolean isValid = false;
        while (!isValid) {
            i = getInt(prompt);
            if (i < min)
                System.out.println("Error, number must not be less than " + min + ".");
            else if (i > max)
                System.out.println("Error, number must not be greater than " + max + ".");
            else
                isValid = true;
        }
        return i;
    }

    public static double getDouble(String prompt) {
        double d = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                d = sc.nextDouble();
                isValid = true;
            } else
                System.out.println("Error, invalid entry.");
            sc.nextLine();
        }
        return d;
    }

    public static double getDouble(String prompt, double min, double max) {
        double d = 0;
        boolean isValid = false;
        while (!isValid) {
            d = getDouble(prompt);
            if (d < min)
                System.out.println("Error, number must not be less than " + min + ".");
            else if (d > max)
                System.out.println("Error, number must not be greater than " + max + ".");
            else
                isValid = true;
        }
        return d;
    }

}
