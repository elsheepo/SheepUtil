package Utilities;

import java.io.*;
import java.math.RoundingMode;
import java.math.BigDecimal;
import java.nio.file.Path;
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

    /**
     *  The genRandomInt(int max) method, designed to generate a random integer, given an inclusive maximum integer value as an argument.
     * @param max Designates the inclusive maximum integer value possible for the return.
     * @return Returns a random integer greater than or equal to 0, and less than or equal to the argument supplied by the user.
     */
    public static int genRandomInt(int max) {
        return (int)Math.round(Math.random() * max);
    }

    /**
     *  The genRandomInt(int min, int max), designed to generate a random integer, given minimum and maximum integer values as arguments.
     *  This method will return an integer value within the specified range.
     * @param min designates the inclusive minimum integer value possible for the return.
     * @param max designates the inclusive maximum integer value possible for the return.
     * @return Returns a random integer between the inclusive minimum and inclusive maximum arguments supplied by the user.
     */
    public static int genRandomInt(int min, int max) {
        double minDouble = Math.random() * min;
        double maxDouble = Math.random() * max;
        int i = (int) ((minDouble * maxDouble) / 2);
        i++;
        if (i < min)
            while (i < min)
                i++;
        return i;
    }

    /**
     *  The genRandomDouble(int max) method, designed to generate a random double, given an inclusive maximum double value as an argument.
     * @param max Designates the inclusive maximum double value possible for the return.
     * @return Returns a random double greater than or equal to 0, and less than or equal to the argument supplied by the user.
     */
    public static double genRandomDouble(double max) {
        return new BigDecimal(Math.random() * max).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

    /**
     *  The genRandomDouble(int min, int max), designed to generate a random double, given minimum and maximum double values as arguments.
     *  This method will return a double value within the specified range.
     * @param min designates the inclusive minimum double value possible for the return.
     * @param max designates the inclusive maximum double value possible for the return.
     * @return Returns a random double between the inclusive minimum and inclusive maximum arguments supplied by the user.
     */
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

    /**
     *  the calculateFutureValue(double monthlyInvestment, double yearlyInterestRate, int years) method.
     *  Designed to calculate and return the future value of a monthly investment incurring compound interested.
     * @param monthlyInvestment The monthly amount deposited into the investment.
     * @param yearlyInterestRate The annual interest rate.
     * @param years The span of years over which the investment is allowed to grow.
     * @return Returns a double value of the calculated future value of the investment.
     */
    public static double calculateFutureValue(double monthlyInvestment, double yearlyInterestRate, int years) {
        double monthlyInterestRate = yearlyInterestRate / 12 / 100;
        int months = years * 12;
        double futureValue = 0;
        for (int i = 1; i <= months; i++) {
            futureValue += monthlyInvestment;
            double monthlyInterestAmount = futureValue * monthlyInterestRate;
            futureValue += monthlyInterestAmount;
        }
        return futureValue;
    }

    /**
     * Pads a string to the specified length supplied by the user.
     * @param s the string to be padded.
     * @param length the length to pad the string to.
     * @return Returns a string padded to the specified length.
     */
    public static String padString(String s, int length) {
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

    public static String getCurrentDate() {
        return dateFormatter.format(currentDate);
    }

    public static String getCurrentTime() {
        return timeFormatter.format(currentTime);
    }

    public static String getCurrentDateTime() {
        return dateTimeFormatter.format(currentDateTime);
    }

    /**
     *  The rightSpot() method! I put this one together helping a fellow coder with an exam question.
     *  it will return the int value stored in an array, in the event that all the previous values are less than, and
     *  all the subsequent values are greater than.
     * @param x integer array supplied by the user.
     * @return Returns the array index at which the previous values are less than, and all the
     * subsequent values are greater than.
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

    /**
     * The writeToTextFile(Path filePath, String s) method. Designed to establish a connection to a text file, and print
     * the supplied String argument to said file.
     * @param filePath Either the Fully Qualified Path Name (FQPN), or relative path from the project directory to the
     * text file.
     * @param s The string to which the text fill will be appended.
     */
    public static void writeToTextFile(Path filePath, String s) {
        File textFile = filePath.toFile();
        try (PrintWriter toTextFile = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(textFile, true)))) {
            toTextFile.print(s);
        } catch (IOException printWriterException) {
            printWriterException.printStackTrace();
        }
    }
}
