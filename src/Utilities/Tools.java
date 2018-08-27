package Utilities;

import java.io.*;
import java.math.RoundingMode;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Tools {

    private static NumberFormat number = NumberFormat.getNumberInstance();

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT);

    private static LocalDate currentDate = LocalDate.now();
    private static LocalTime currentTime = LocalTime.now();
    private static LocalDateTime currentDateTime = LocalDateTime.now();

    public static int genRandomInt(int max) {
        double d = Math.random() * max;
        int randomInt = (int) d;
        randomInt++;
        return randomInt;
    }

    public static int genRandomInt(int min, int max) {
        double d = Math.random() * max;
        int i = (int) d;
        i++;
        if (i < min)
            while (i < min)
                i++;
        return i;
    }

    public static double genRandomDouble(double max) {
        return new BigDecimal(Math.random() * max).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

    public static double genRandomDouble(double min, double max) {
        double d = new BigDecimal(Math.random() * max).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        if (d < min)
            while (d < min)
                d++;
        number.setMaximumFractionDigits(2);
        String s = number.format(d);
        d = Double.parseDouble(s);
        return d;
    }

    public static double calculateFutureValue(double monthlyInvestment, double yearlyInterestRate, int years) {
        // convert yearly values to monthly values
        double monthlyInterestRate = yearlyInterestRate / 12 / 100;
        int months = years * 12;

        // calculate the future value
        double futureValue = 0;
        for (int i = 1; i <= months; i++) {
            futureValue += monthlyInvestment;
            double monthlyInterestAmount = futureValue * monthlyInterestRate;
            futureValue += monthlyInterestAmount;
        }

        return futureValue;
    }

    public static String pad(String s, int length) {
        if (s.length() < length) {
            StringBuilder sb = new StringBuilder(s);
            while (sb.length() < length) {
                sb.append(" ");
            }
            return sb.toString();
        } else {
            return s.substring(0, length);
        }
    }

    public static String displayCurrentDate() {
        return dateFormatter.format(currentDate);
    }

    public static String displayCurrentTime() {
        return timeFormatter.format(currentTime);
    }

    public static String displayCurrentDateTime() {
        return dateTimeFormatter.format(currentDateTime);
    }

    /**
     *  The rightSpot() method! I put this one together helping a fellow coder with an exam question.
     *  it will return the int value stored in an array, in the event that all the previous values are less than, and
     *  all the subsequent values are greater than.
     * @param x
     * @return
     */
    public static int rightSpot(int[] x) {
        int result = -1;
        int savedValue = 0;
        try {
            savedValue = x[0];

            for (int i = 0; i < x.length; i++) {
                if (i != (x.length - 1)) {
                    if (x[i] > savedValue && x[i] < x[(i + 1)])
                        result = i;
                    savedValue = x[i];
                } else {
                    if (x[i] > savedValue)
                        result = i;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("NullPointerException occurred.");
        }
        return result;
    }

    public static void writeToTextFile(File productsFile, String s) {
        try (PrintWriter toTextFile = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(productsFile, true)))) {
            toTextFile.print(s);
        } catch (IOException printWriterException) {
            printWriterException.printStackTrace();
        }
    }
}
